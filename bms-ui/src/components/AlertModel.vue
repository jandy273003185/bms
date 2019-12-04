<template>
  <div class='alert-model' @click.stop.prevent="closeModel">
    <div class="alert-model-inner" @click.stop.prevent>
      <div v-show="title" class="alert-model-title">{{title}}</div>
      <!-- 表单内容 -->
      <slot></slot>
      <div class="btns">
        <el-button size="small" @click="closeModel">取消</el-button>
        <el-button size="small" type="warning" @click="goToSubmit">确定</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['title'],
  data() {
    return {};
  },
  watch: {},
  created() {},
  methods: {
    closeModel() {
      this.$emit('update:display', false);
    },
    goToSubmit() {
      // 提交更改后的数据
      this.$emit('put');
      // 隐藏模态框
      this.$emit('update:display', false);
    }
  }
};
</script>

<style lang='scss' scoped>
.alert-model {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1000;
}
.alert-model-inner {
  width: 600px;
  // height: 100px;
  background-color: #fff;
  position: absolute;
  top: 20%;
  left: 50%;
  max-height: 100%;
  overflow: auto;
  padding: 0 0 20px 0;
  transform: translateX(-50%);
}

.alert-model-title {
  height: 56px;
  line-height: 56px;
  font-size: 18px;
  border-bottom: 1px solid #eee;
  padding-left: 20px;
}

.alert-model-form {
  padding: 20px;
}

.btns {
  width: 100%;
  text-align: center;
}
</style>