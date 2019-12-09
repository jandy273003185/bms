<template>
  <!-- 聚合支付 => 订单信息 => 订单列表 -->
  <div class="aggregate-order-list-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm1">
          <el-form-item label="支付产品" prop="name1">
            <el-select v-model="examine.name1" placeholder="请选择">
              <!-- <el-option label="消费" value="0"></el-option>
            <el-option label="退款" value="1"></el-option> -->
            </el-select>
          </el-form-item>
          <el-form-item label="支付渠道" prop="name2">
            <el-select v-model="examine.name2" placeholder="请选择">
              <el-option label="消费" value="0"></el-option>
              <el-option label="退款" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="订单状态" prop="name3">
            <el-select v-model="examine.name3" placeholder="请选择">
              <el-option label="等待支付" value="0"></el-option>
              <el-option label="支付处理中" value="1"></el-option>
              <el-option label="支付成功" value="2"></el-option>
              <el-option label="支付失败" value="3"></el-option>
              <el-option label="取消" value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="渠道" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="微信" value="0"></el-option>
              <el-option label="七分钱" value="1"></el-option>
              <el-option label="支付宝" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm2">
          <el-form-item label="商户编号" prop="name5">
            <el-input v-model="examine.name5"></el-input>
          </el-form-item>
          <el-form-item label="订单号" prop="name6">
            <el-input v-model="examine.name6"></el-input>
          </el-form-item>
          <el-form-item label="中信订单号" prop="name7">
            <el-input v-model="examine.name7"></el-input>
          </el-form-item>
          <el-form-item label="渠道订单号" prop="name8">
            <el-input v-model="examine.name8"></el-input>
          </el-form-item>
        </el-form>
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm3">
          <el-form-item label="创建日期" prop="name9">
            <el-date-picker v-model="examine.name9" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="账期时间" prop="name10">
            <el-date-picker v-model="examine.name10" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
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
          <el-table-column prop='name1' label='订单号' width="100"></el-table-column>
          <el-table-column prop='name3' label='商户编号'></el-table-column>
          <el-table-column prop='name4' label='商户名称'></el-table-column>
          <el-table-column prop='name5' label='渠道编号'></el-table-column>
          <el-table-column prop='name6' label='中信订单号'></el-table-column>
          <el-table-column prop='name7' label='渠道订单号'></el-table-column>
          <el-table-column prop='name8' label='订单金额'></el-table-column>
          <el-table-column prop='name9' label='付款金额'></el-table-column>
          <el-table-column prop='name10' label='结算金额'></el-table-column>
          <el-table-column prop='name11' label='代金券'></el-table-column>
          <el-table-column prop='name12' label='商品描述'></el-table-column>
          <el-table-column prop='name13' label='支付产品'></el-table-column>
          <el-table-column prop='name14' label='订单状态'></el-table-column>
          <el-table-column prop='name15' label='创建时间'></el-table-column>
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
      console.log(v);
    }
  },
  created() {},
  methods: {
    restFormFiles() {
      this.$refs['controlQueryForm1'].resetFields();
      this.$refs['controlQueryForm2'].resetFields();
      this.$refs['controlQueryForm3'].resetFields();
    },
    goToSearch() {
      //查询
      console.log(this.examine, '查询');
    },
    lookClick(row) {
      this.lookDisplay = true;
      console.log(row);
    },
    download() {
      this.$message('导出报表');
    }
  }
};
</script>

<style lang='scss'>
.aggregate-order-list-page .el-input .el-input__inner {
  width: 193px;
}
</style>