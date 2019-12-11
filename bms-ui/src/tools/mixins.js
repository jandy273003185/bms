const mixins = {
  methods: {
    // 清空表单
    resetFormFields(ele) {
      this.$refs[ele].resetFields();
    },
    // 针对页面有tab选项卡的页面，初始化tanIndex
    initTabIndex() {
      let index = '';
      const queryCurrentIndex = sessionStorage.getItem('queryCurrentIndex');
      const flag = this.tabs.every(ele => ele.cpt === queryCurrentIndex);
      if (flag) {
        index = queryCurrentIndex;
      } else {
        index = 'Tab1';
      }
      this.tabsIndex = index;
    }
  }
};

export default mixins;