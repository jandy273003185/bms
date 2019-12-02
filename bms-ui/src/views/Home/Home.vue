<template>
  <el-container class="home-page">
    <el-header>
      <router-link tag="h1" to="/">
        <site-icon iconClass="leaf" size="16" />七分钱后台管理系统
      </router-link>
      <div class="header-info">
        <router-link to="/">七分钱后台管理系统</router-link>&nbsp;|&nbsp;管理员&nbsp;|&nbsp;员工编号：1&nbsp;|&nbsp;国银证宝&nbsp;|
        <router-link to="/">
          <site-icon iconClass="xiugai" size="12" />修改密码
        </router-link>
        |{{$store.state.accessToken}}
        <el-button size="mini" @click="exit">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <!-- 侧边菜单栏 -->
      <div class="site-aside clear-scroll-bar">
        <!-- 
          <el-button class="menu-btn" @click="goToToggleMenu">
            <site-icon :iconClass="toggleMenu ?'right': 'left'" size="12" />
          </el-button> 
        -->
        <Menu :toggle="toggleMenu" v-model="path" />
      </div>
      <!-- 右边内容 -->
      <el-main class="el-main-content">
        <!-- 面包屑导航 -->
        <div class="breadcrumb-main">
          <el-breadcrumb separator=">">
            <el-breadcrumb-item>
              <site-icon iconClass="" />
            </el-breadcrumb-item>
            <el-breadcrumb-item v-for="(item,index) in path" :key="index">{{item}}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="home-search">
            <el-input prefix-icon="el-icon-search" @keyup.enter.native="goToSearch" placeholder="Search..." v-model="searchText"></el-input>
          </div>
        </div>
        <!-- 主体内容 -->
        <router-view class="el-main-view" :searchText="sendToSearchText"></router-view>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
// import { storage } from '@/tools/utils';
import Menu from '@/views/Menu/Menu';
export default {
  name: 'App',
  components: { Menu },
  data() {
    return {
      toggleMenu: false,
      path: [],
      searchText: '',
      sendToSearchText: ''
    };
  },
  watch: {},
  methods: {
    exit() {},
    goToToggleMenu() {
      this.toggleMenu = !this.toggleMenu;
    },
    goToSearch() {
      if (!this.searchText) {
        return;
      }
      this.sendToSearchText = this.searchText;
      this.searchText = '';
    }
  },
  created() {
    // const token = storage.get('accessToken') || '';
    // if (token) {
    //   this.$router.push('/home');
    // } else {
    //   this.$router.push('/login');
    // }
  },
  updated() {}
};
</script>

<style lang="scss">
body,
html {
  height: 100%;
  background-color: #efefef;
  padding: 0;
  margin: 0;
}
.home-page {
  height: 100%;
}
.el-container {
  height: 100%;
  .el-footer,
  .site-aside,
  .el-main {
    background-color: #fff;
  }
  & > .el-container {
    margin: 10px 0;
    .site-aside {
      margin-right: 10px;
      overflow: auto;
    }
  }
}
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #438eb9;
  color: #fff;
  h1 {
    font-size: 20px;
  }
  .site-icon {
    margin-right: 10px;
  }

  .header-info {
    font-size: 13px;
  }
}
.el-main {
  height: 100%;
}
th,
td {
  text-align: center;
}

.el-menu-toggle:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.menu-btn {
  width: 100%;
}

.el-main-content.el-main {
  box-sizing: border-box;
  position: relative;
  padding-top: 40px;
  overflow: hidden;
  .breadcrumb-main {
    width: 100%;
    position: absolute;
    top: 0;
    left: 20px;
    border-bottom: 1px solid #eee;
    display: flex;
    justify-content: space-between;
    .home-search {
      margin-right: 50px;
      .el-input {
        input {
          height: 30px;
        }
      }
      .el-input__icon.el-icon-search {
        line-height: 30px;
      }
    }
  }
}
</style>
