const mixins = {
  methods: {
    // 清空表单
    resetFormFields(ele) {
      this.$refs[ele].resetFields();
    }
  }
};

export default mixins;