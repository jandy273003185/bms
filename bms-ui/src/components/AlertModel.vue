<template>
  <div class='alert-model' @click.stop.prevent="closeModel">
    <div class="alert-model-inner" @click.stop.prevent>
      <div v-show="title" class="alert-model-title">{{title}} <span @click="closeModel" class="el-icon-close"></span> </div>
      <!-- 表单内容 -->
      <slot></slot>
      <div class="btns">
        <el-button size="small" @click="closeModel">取消</el-button>
        <el-button size="small" type="primary" @click="goToSubmit">提交</el-button>
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
      this.$emit('on-cancel');
    },
    goToSubmit() {
      this.$emit('on-submit', this.closeModel);
    }
  }
};
</script>

<style lang='scss'>
.alert-model {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1000;
  .el-select.el-select--mini {
    width: 100%;
  }
}
.alert-model-inner {
  width: 600px;
  // height: 100px;
  background-color: #fff;
  position: absolute;
  top: 50%;
  left: 50%;
  max-height: 100%;
  overflow: auto;
  padding: 0 0 20px 0;
  transform: translateX(-50%) translateY(-50%);
}

.alert-model-title {
  height: 56px;
  line-height: 56px;
  font-size: 18px;
  border-bottom: 1px solid #eee;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  span {
    color: #dddddd;
    cursor: pointer;
  }
}

.alert-model-form {
  padding: 20px;
}

.btns {
  width: 100%;
  text-align: center;
}
</style>