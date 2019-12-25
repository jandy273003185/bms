<template>
  <!-- 聚合支付 => 订单信息 => 退款列表 -->
  <div class="aggregate-order-refund-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm">
          <el-form-item label="退款编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="商户编号" prop="name2">
            <el-select v-model="examine.name2" placeholder="请选择">
              <el-option label="消费" value="0"></el-option>
              <el-option label="退款" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="退款发起时间" prop="name9">
            <el-date-picker v-model="examine.name9" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="退款状态" prop="name3">
            <el-select v-model="examine.name3" placeholder="请选择">
              <el-option label="申请退款" value="0"></el-option>
              <el-option label="退款成功" value="1"></el-option>
              <el-option label="退款失败" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="原订单渠道" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="微信" value="0"></el-option>
              <el-option label="七分钱" value="1"></el-option>
              <el-option label="支付宝" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="原订单号" prop="name5">
            <el-input v-model="examine.name5"></el-input>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="restFormFiles">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="download">导出报表<i class="el-icon-download"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border class="nofix">
          <el-table-column prop='name1' label='退款编号' width="100"></el-table-column>
          <el-table-column prop='name3' label='原订单编号'></el-table-column>
          <el-table-column prop='name4' label='商户编号'></el-table-column>
          <el-table-column prop='name5' label='原订单总金额'></el-table-column>
          <el-table-column prop='name6' label='退款金额'></el-table-column>
          <el-table-column prop='name7' label='手续费'></el-table-column>
          <el-table-column prop='name8' label='退款时间'></el-table-column>
          <el-table-column prop='name9' label='退款状态'></el-table-column>
          <el-table-column prop='name10' label='退款渠道'></el-table-column>
          <el-table-column prop='name11' label='产品名称'></el-table-column>
          <el-table-column prop='name12' label='原订单渠道'></el-table-column>
          <el-table-column prop='name13' label='退款发起时间'></el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>
  </div>
</template>

<script>
// const testData = {};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      tableData: [],
      paginationOps: {
        pageSizes: [5, 10, 15, 20],
        total: 100
      }
    };
  },
  watch: {
    // 监听search传来的数据
    searchText(v) {
      window.console.log(v);
    }
  },
  created() {},
  methods: {
    restFormFiles() {
      this.$refs['controlQueryForm'].resetFields();
    },
    goToSearch() {
      //查询
      window.console.log(this.examine, '查询');
    },
    lookClick(row) {
      this.lookDisplay = true;
      window.console.log(row);
    },
    download() {
      this.$message('导出报表');
    }
  }
};
</script>

<style lang='scss'>
.aggregate-order-refund-page .el-input .el-input__inner {
  width: 193px;
}
</style>