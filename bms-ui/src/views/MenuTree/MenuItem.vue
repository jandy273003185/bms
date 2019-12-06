<template>
  <el-submenu v-if="data.children && data.children.length" :index="`${itemIndex}`">
    <template slot="title"><i v-if="icon" :class="icon"></i>{{data.name}}</template>
    <menu-item v-for="(item,index) in data.children" :key="index" :itemIndex="`${itemIndex}-${index}`" :index="`${itemIndex}-${index}`" :data="item">
      <router-link tag="div" :to="item.path">{{item.name}}</router-link>
    </menu-item>
  </el-submenu>
  <el-menu-item :index="`${itemIndex}`" v-else>
    <router-link tag="div" :to="data.path">
      {{data.name}}
    </router-link>
  </el-menu-item>
</template>

<script>
export default {
  name: 'menu-item',
  props: ['data', 'itemIndex', 'icon']
};
</script>

<style>
</style>