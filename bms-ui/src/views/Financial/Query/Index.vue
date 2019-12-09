<template>
  <div class="financial-query-page">
    <el-tabs v-model="tabsIndex" type="card" @tab-click="handleClick">
      <el-tab-pane v-for="(item,index) in tabs" :key="index" :label="item.label" :name="`${item.cpt}`"></el-tab-pane>
    </el-tabs>
    <components :is="tabsIndex" />
  </div>
</template>

<script>
import { sstorage } from '@/tools/utils';
import Total from './Total';
import Variation from './Variation';
export default {
  components: { Total, Variation },
  data() {
    return {
      tabs: [
        { label: '汇总余额', cpt: 'Total' },
        { label: '余额变动', cpt: 'Variation' },
        { label: '商户余额', cpt: 'Total3' },
        { label: '用户余额', cpt: 'Total4' },
        { label: '客戶提现结算', cpt: 'Total5' },
        { label: '商户提现结算', cpt: 'Total6' },
        { label: '客户提现撤销列表', cpt: 'Total7' }
      ],
      tabsIndex: 'Total'
    };
  },
  created() {
    const index = sessionStorage.getItem('queryCurrentIndex') || 'Total';
    this.tabsIndex = index;
  },
  beforeDestroy() {
    sstorage.set('queryCurrentIndex', '');
  },
  methods: {
    handleClick() {
      sstorage.set('queryCurrentIndex', this.tabsIndex);
    }
  }
};
</script>

<style lang="scss">
.financial-query-page {
  overflow: auto;
}
</style>