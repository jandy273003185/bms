<template>
  <div class='user-page'>
    用户管理
  </div>
</template>

<script>
export default {
  props: ['searchText'],
  data() {
    return {};
  },
  watch: {
    // 监听search传来的数据
    searchText(v, o) {
      if (!v || v === o) return;
      console.log(v);
    }
  },
  created() {},
  methods: {}
};
</script>

<style lang='scss' scoped>
</style>