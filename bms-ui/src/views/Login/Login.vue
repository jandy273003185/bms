<template>
  <div class='login-page'>
    <div class="login-form-wrap">
      <div class="login-logo"></div>
      <h2>七分钱后台管理系统</h2>
      <el-form ref="loginForm" :model="user" label-width="60px" :rules="rules">
        <el-form-item prop="username">
          <i slot="label" class="login-username-icon"></i>
          <el-input v-model="user.username" placeholder="请输入账户"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <i slot="label" class="login-password-icon"></i>
          <el-input v-model="user.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <button class="submit" @click.prevent="goToSubmit">登录</button>
      </el-form>
      <div class="copyright">版权所有 © 2016 深圳市国银证保信息技术有限公司</div>
    </div>
  </div>
</template>

<script>
import { checkNull } from '@/tools/validate';
import { mapMutations } from 'vuex';
export default {
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ validator: checkNull.bind(this, '账户') }],
        password: [{ validator: checkNull.bind(this, '密码') }]
      }
    };
  },
  created() {},
  methods: {
    ...mapMutations(['setToken']),
    goToSubmit() {
      // 校验
      const form = this.$refs['loginForm'];
      form.validate(valid => {
        // console.log(valid);
        if (valid) {
          // 校验成功
          // this.setToken('6789');
          this.$store.commit('setToken', '6897');
        } else {
          // this.$message('请输入账号密码');
        }
      });
      // 发送请求
      // this.$loading(); loading
    }
  }
};
</script>

<style lang='scss'>
.login-page {
  width: 100%;
  min-height: 100vh;
  background-image: url(../../assets/images/login/bg.jpg);
}

.login-form-wrap {
  width: 370px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
  text-align: center;

  .login-logo {
    width: 82px;
    height: 82px;
    background-image: url(../../assets/images/login/logo_bg.png);
    background-size: 270px 114px;
    background-position: 214px 0px;
    display: inline-block;
  }

  h2 {
    height: 36px;
    line-height: 36px;
    font-size: 24px;
    color: #af2f23;
    font-weight: 700;
    margin: 17px 0 70px 0;
  }

  .login-password-icon,
  .login-username-icon {
    width: 30px;
    height: 40px;
    display: block;
    background-image: url(../../assets/images/login/logo_bg.png);
    background-size: 270px 114px;
    margin-left: 13px;
  }

  .el-form-item {
    background-color: #fff;
    padding: 3px;
    border-radius: 5px;
  }

  .el-form-item__label {
    text-align: center;
  }

  .el-input__inner {
    border: none;
    border-radius: 0;
    background-color: #e8f0fe;
    font-size: 18px;
    color: #999;
  }

  .el-input__inner::-webkit-input-placeholder {
    color: #757575;
  }
  .el-input__inner::-moz-input-placeholder {
    color: #757575;
  }
  .el-input__inner::-ms-input-placeholder {
    color: #757575;
  }
  .login-password-icon {
    background-position: 70px 4px;
  }
  .login-username-icon {
    background-position: 33px 4px;
  }

  .submit {
    width: 100%;
    height: 44px;
    line-height: 44px;
    border-radius: 5px;
    background-color: #8f005c;
    color: #fff;
    font-size: 20px;
    border: 0;
    cursor: pointer;
  }

  .copyright {
    margin-top: 100px;
    color: #fff;
  }
}
</style>