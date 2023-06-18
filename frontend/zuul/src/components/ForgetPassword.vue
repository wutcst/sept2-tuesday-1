<template>
  <div :style="bgurl">
<el-container>
  <el-header>
    <el-row>
      <el-col :span="4" :push="6" :xs="8">
        <div class="change">修改密码</div>
      </el-col>
    </el-row>
    <div class="el-space"></div>
  </el-header>
  <el-main>
    <el-form label-width="12%" :model="form" :rules="rules" ref="forgetPasswdForm">
      <el-form-item prop="userName">
        <template v-slot:label>
          <span class="hidden-xs-only">用户名</span>
        </template>
        <el-input v-model="form.userName" placeholder="用户名" :style="{width: input_width+'%'}"></el-input>
      </el-form-item>
      <el-form-item prop="email">
        <template v-slot:label>
          <span class="hidden-xs-only">邮箱</span>
        </template>
        <el-input type="email" v-model="form.email" placeholder="邮箱" :style="{width: input_width+'%'}"></el-input>
      </el-form-item>
      <el-form-item prop="passwd">
        <template v-slot:label>
          <span class="hidden-xs-only">密码</span>
        </template>
        <el-input type="password"
                  v-model="form.passwd"
                  show-password
                  placeholder="密码"
                  :style="{width: input_width+'%'}"
        />
      </el-form-item>
      <el-form-item prop="newPasswd">
        <template v-slot:label>
          <span class="hidden-xs-only">确认密码</span>
        </template>
        <el-input type="password"
                  v-model="form.newPasswd"
                  show-password
                  placeholder="密码"
                  :style="{width: input_width+'%'}"
        />
      </el-form-item>
      <el-form-item>
      <el-button type="success" :style="{width: input_width+'%'}" @click="handleForgetPass">确认</el-button>
      </el-form-item>
    </el-form>
  </el-main>
  <el-footer>
    <div>
      <el-button type="info" :style="{marginLeft: 12+'%' , width: back_btn_width+'%'}" @click="backToLogin">返回登录界面</el-button>
    </div>
  </el-footer>
</el-container>
</div>
</template>

<script>
import {encrypt} from "../../encryptor";
export default {
  name: "ForgetPassword",
  data(){
    var checkLen = (rule,value,callback)=>{
      if(value.length<6 || value.length>13){
        return callback(new Error('密码长度需6-13位'));
      }else{
        return callback();
      }
    };
    var checkpass = (rule,value,callback)=>{
      if(value!=this.form.passwd){
        return callback(new Error('两次输入的密码不一致'));
      }else{
        return callback();
      }
    };
    return {
      bgurl: {
            backgroundImage: "url(" + require("../assets/img/bg3.png") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize: "100% 100%",
            minHeight: "100vh",
            position: "relative",
            backgroundPosition: "center center",
          },
      form:{
        userName:'',
        passwd:'',
        newPasswd:'',
        email:'',
      },
      rules:{
        userName: [{required:true,message:'请输入用户名',trigger: 'blur'}],
        email: [{required: true, message: '请输入邮箱', trigger: ['blur']},
          {type: 'email', message: '请输入正确的邮箱', trigger: ['blur', 'change']}],
        passwd: [{required:true,message:'请输入密码',trigger: 'blur'},
          {validator:checkLen,trigger: 'blur'}],
        newPasswd: [{required:true,message:'请再次输入密码',trigger:'blur'},
          {validator:checkpass,trigger: 'blur'}]
      },
      input_width:40,
      back_btn_width:35
    }
  },
  methods:{
    backToLogin(){
      this.$router.push('/login')
    },
    handleForgetPass(){
      this.$refs.forgetPasswdForm.validate((valid)=>{
        if(!valid){
          this.$message.warning('表单数据不合法')
          return
        }else{
          let formData = {};
          formData.userName = this.form.userName;
          formData.passwd = encrypt(this.form.passwd);
          formData.newPasswd =formData.passwd;
          formData.email = this.form.email;
          this.$http.put('http://localhost:80/user/pwd',formData).then((res)=>{
            if(res.data.flag){
              this.$message.success('密码修改成功')
              this.$router.push('/login')
            }else{
              this.$message.error(res.data.message)
            }
          },()=>{
            this.$message.error('系统错误,修改密码失败')
          })
        }
      })
    }
  },
  props:['scn_width'],
  watch:{
    scn_width:{
      handler(newVal){
        if(newVal<650){
         this.input_width=80
         this.back_btn_width=70
        }else{
          this.input_width=40
          this.back_btn_width=35
        }
      },
      immediate:true
    }
  }
}
</script>

<style scoped>
.hidden-xs-only{
  color: azure;
}
.change{
  color: azure;
  font-size: 30px;
}
.el-container{
  padding-top: 10%;
  margin-left: 15%;
}
</style>