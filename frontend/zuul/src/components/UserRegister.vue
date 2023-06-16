<template>
<el-container>
  <el-header>
    <!--  注册-->
    <el-row>
      <el-col :span="4" :push="5" >
        <div>注册</div>
      </el-col>
    </el-row>
    <div class="el-space"></div>
  </el-header>
  <el-main>
    <el-form label-width="12%" :model="form" :rules="rules"  ref="registerForm">
      <el-form-item prop="userName">
        <template v-slot:label>
          <span class="hidden-xs-only">用户名</span>
        </template>
        <el-input v-model="form.userName" placeholder="用户名" :style="{width: input_width+'%'}"></el-input>
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
      <el-form-item prop="email">
        <template v-slot:label>
          <span class="hidden-xs-only">邮箱</span>
        </template>
        <el-input type="email" v-model="form.email" placeholder="邮箱" :style="{width: input_width+'%'}"></el-input>
      </el-form-item>

      <el-form-item>
      <el-button type="success" :style="{width: input_width+'%'}" @click="handleRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </el-main>
  <el-footer>
    <div>
      <el-button type="info" :style="{marginLeft:btn_left_margin+'%',width:back_btn_width+'%'}" @click="backToLogin">返回登录界面</el-button>
    </div>
  </el-footer>
</el-container>
</template>

<script>
import {encrypt} from "../../encryptor";
export default {
  name: "UserRegister",
  data(){
    var checkLen = (rule,value,callback)=>{
      if(value.length<6 || value.length>13){
        return callback(new Error('密码长度需6-13位'));
      }else{
        return callback();
      }
    };
    return{
    form:{
      userName:'',
      passwd:'',
      email:''
    },
      rules:{
      userName: [{required:true, message:'请输入用户名', trigger:'blur',}],
      passwd:[{required:true, message:'请输入密码', trigger:'blur',},{validator: checkLen,trigger: 'blur'}],
      email: [{required: true, message: '请输入邮箱', trigger: ['blur']},
                  {type: 'email', message: '请输入正确的邮箱', trigger: ['blur', 'change']}]

      },
      input_width:40,
      back_btn_width:35,
      btn_left_margin:12,
    }
  },
  methods:{
    backToLogin(){
      this.$router.push('/login')
    },
    handleRegister(){
      this.$refs.registerForm.validate((valid)=>{
        if(!valid){
          this.$message.warning('表单数据不合法');
          return
        }else{
          let formData = {};
          formData.userName = this.form.userName;
          formData.passwd = encrypt(this.form.passwd);
          formData.email = this.form.email;
          this.$http.post('http://localhost:80/user/register',formData).then((res)=>{
            if(res.data.flag){
              this.$message.success('注册成功')
              this.$router.push('/login')
            }else{
              this.$message.error(res.data.message)
            }
          },()=>{
            this.$message.error('系统错误，注册失败')
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
.el-container{
  margin-top: 10%;
  margin-left: 15%;
}
</style>