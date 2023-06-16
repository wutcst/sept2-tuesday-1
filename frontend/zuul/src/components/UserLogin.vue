<template>
  <el-container>
    <el-header>
      <!--  登录-->
      <el-row>
        <el-col :span="4" :push="8">
          <div style="font-size: 30px">登录</div>
        </el-col>
      </el-row>
      <div class="el-space"></div>
    </el-header>
    <el-main>
      <el-row style="margin-left: 20%">
<!--        <el-col :span="5" :xs="16">-->
<!--          &lt;!&ndash;  左侧按钮&ndash;&gt;-->
<!--          <div>-->
<!--            <div >-->
<!--              <el-button type="primary" class="leftBtn" @click="processThirdPartyLogin">QQ登录</el-button>-->
<!--            </div>-->
<!--            <div class="el-space"></div>-->
<!--            <div>-->
<!--              <el-button type="success" class="leftBtn" @click="processThirdPartyLogin">微信登录</el-button>-->
<!--            </div>-->
<!--            <div class="el-space"></div>-->
<!--            <div >-->
<!--              <el-button class="leftBtn" @click="processThirdPartyLogin">支付宝登录</el-button>-->
<!--            </div>-->
<!--          </div>-->
<!--        </el-col>-->

<!--        <el-col :span="2" :push="1" :xs="8">-->
<!--          &lt;!&ndash;  分割线&ndash;&gt;-->
<!--          <div style="height: 138.21px; border-left: 1px solid; width: 10%;"  class="hidden-xs-only"></div>-->
<!--        </el-col>-->

        <el-col :xs="24" :span="0">
          <div class="el-space"></div>
        </el-col>
        <el-col :span="9" :xs="16">
          <!--  登录表单-->
          <el-form :model="form" :rules="rules" ref="loginForm">
            <el-form-item prop="userName">
              <el-input v-model="form.userName" placeholder="用户名"/>
            </el-form-item>
            <el-form-item prop="passwd">
              <el-input
                  v-model="form.passwd"
                  type="password"
                  placeholder="密码"
                  show-password
              />
            </el-form-item>
            <el-form-item>
              <el-button type="success" class="leftBtn" @click="handleLogin">登录</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-main>
    <el-footer>
    <el-row style="margin-left: 20%">
      <el-col :span="2" :xs="6">
       <router-link to="/register">注册</router-link>
      </el-col>
      <el-col :span="5" :xs="4"></el-col>
      <el-col :span="3" :xs="8">
        <router-link to="/forgetpwd">忘记密码?</router-link>
      </el-col>
    </el-row>
    </el-footer>
  </el-container>
</template>
<script>
import {encrypt} from "../../encryptor";
export default {
  name: "UserLogin",
  data(){
    var checkLen = (rule,value,callback)=>{
      if(value.length<6 || value.length>13){
        return callback(new Error('密码长度需6-13位'));
      }else{
        return callback();
      }
    };
    return {
      form:{
        userName:"",
        passwd:"",
      },

      rules:{
        userName:[{required:true,message:'请输入用户名',trigger:'blur'}],
        passwd:[{required:true,message: '请输入密码',trigger:'blur'},{validator:checkLen,trigger:['blur','change']}]
      }
    }
  },
  methods:{
    processThirdPartyLogin(){
      this.$message.info('该功能尚未开放，敬请期待')
    },
    handleLogin(){
      this.$refs.loginForm.validate((valid)=>{

        if(!valid){
          this.$message.warning('表单数据不合法')
          return
        }else{
          let formData = {};
          formData.userName = this.form.userName;
          formData.passwd = encrypt(this.form.passwd)

          this.$http.post('http://localhost:80/user/login',formData).then((res)=>{
            if(!res.data.flag){
              this.$message.error(res.data.message)
            }else{
              this.$message.success("登陆成功")
              localStorage.setItem('token',res.data.data.token)
              localStorage.setItem('name',this.form.userName)
              this.$router.push('/home')
            }
          },()=>{
            this.$message.error('系统错误,登陆失败')
          })
        }

      })
    }
  }
}
</script>

<style scoped>
.leftBtn{
width: 100%;
}
.el-container{
  margin-top: 15%;
  margin-left: 15%;
}
</style>