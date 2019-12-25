<template>
  <!-- 聚合支付 => 商户交易管理 => 交易汇总 -->
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="140px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户名称" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="商户编号" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="交易类型" prop="name3">
            <el-select v-model="examine.name3" placeholder="请选择">
              <el-option label="消费" value="0"></el-option>
              <el-option label="退款" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="开始账期" prop="name4">
            <el-date-picker v-model="examine.name4" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束账期" prop="name5">
            <el-date-picker v-model="examine.name5" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="download">导出报表<i class="el-icon-download"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border class="nofix">
          <el-table-column prop='name1' label='开始账期' width="100"></el-table-column>
          <el-table-column prop='name2' label='结束账期' width="100"></el-table-column>
          <el-table-column prop='name3' label='商户编号' min-width="180"></el-table-column>
          <el-table-column prop='name4' label='商户名称' min-width="180"></el-table-column>
          <el-table-column prop='name5' label='交易渠道' width="80"></el-table-column>
          <el-table-column prop='name6' label='交易笔数' width="80"></el-table-column>
          <el-table-column prop='name7' label='交易金额' width="100"></el-table-column>
          <el-table-column prop='name8' label='交易类型' width="90"></el-table-column>
          <el-table-column prop='name9' label='商户结算金额' width="100"></el-table-column>
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
const testData = {
  name1: '2019-12-07',
  name2: '2019-12-07',
  name3: 'P2019111917062300033',
  name4: '深圳积微贸易有限公司',
  name5: 'alipay',
  name6: '1',
  name7: '0.1',
  name8: '消费',
  name9: '0.00'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      tableData: new Array(5).fill(testData),
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

<style lang='scss'></style>