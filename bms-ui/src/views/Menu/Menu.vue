<template>
  <div class="menu-page">
    <el-menu @select="selectHandle" :unique-opened="true" :collapse="toggle" :default-active="defaultActive">
      <MenuItem v-for="(item,index) in list" :key="index" :itemIndex="index" :data="item" icon="el-icon-menu">
      </MenuItem>
    </el-menu>
  </div>
</template>

<script>
import MenuItem from './MenuItem';
import { sstorage } from '@/tools/utils';
import { menuRouter } from '@/router';
export default {
  props: ['toggle'],
  components: { MenuItem },
  data() {
    return {
      defaultActive: '',
      list: menuRouter
    };
  },
  created() {
    const index = sessionStorage.getItem('defaultActive') || '';
    this.defaultActive = index;
  },
  methods: {
    selectHandle(index) {
      const arr = index.split('-');
      let route = [];
      arr.reduce((pre, cur) => {
        route.push(pre[cur].name);
        return pre[cur].children;
      }, menuRouter);
      this.$emit('input', route);
      sstorage.set('currentRouterArr', route);
      sstorage.set('defaultActive', index);
    }
  }
};
</script>

<style>
</style>