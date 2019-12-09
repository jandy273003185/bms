<template>
  <!-- 聚合支付 => 产品渠道 => 支付渠道管理 -->
  <div class="aggregate-exception-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="86px" :inline="true" ref="controlQueryForm">
          <el-form-item label="订单类型" prop="name1">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="全部" value="1"></el-option>
              <el-option label="全付订单" value="0"></el-option>
              <el-option label="退款订单" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间" prop="name2">
            <el-date-picker v-model="examine.name2" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border>
          <el-table-column prop='name1' min-width="140" label='订单号'></el-table-column>
          <el-table-column prop='name2' min-width="100" label='商户名称'></el-table-column>
          <el-table-column prop='name3' min-width="150" label='商户号'></el-table-column>
          <el-table-column prop='name4' width="80" label='付款金额'></el-table-column>
          <el-table-column prop='name5' width="80" label='订单状态'></el-table-column>
          <el-table-column prop='name6' min-width="100" label='订单创建时间'></el-table-column>
          <el-table-column prop='name7' min-width="100" label='核心流水号'></el-table-column>
          <el-table-column prop='name8' width="100" label='核心返回码'></el-table-column>
          <el-table-column prop='name9' label='核心返回信息'></el-table-column>

          <el-table-column fixed="right" label="操作" width="60">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="performClick(scope.row)">处理</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 处理 -->
    <el-dialog title="订单异常处理" :visible.sync="performDisplay" width="1000px">
      <div class="alert-model-form-header">订单信息</div>
      <el-form :model="performModelData" :disabled="true" :inline="true" class="alert-model-form" label-width="120px">
        <el-form-item prop="name1" label="退款编号">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="商户退款流水号">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="商户编号">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="原订单编号">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="原订单金额">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="退款金额">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="中信交易号">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="中信退款ID">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="退款渠道">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="退款时间">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="退款状态">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
      </el-form>
      <div class="alert-model-form-header">核心信息</div>
      <el-form :model="performModelData" :inline="true" :disabled="true" class="alert-model-form" label-width="120px">
        <el-form-item prop="name1" label="核心状态">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="核心流水号">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="核心返回码">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="核心返回信息">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="核心返回时间">
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
      </el-form>
      <div class="next-wrap">
        <button @click="goToNext">继续下一步</button>
      </div>

      <el-dialog title="执行确认" class="next-dialog" append-to-body :visible.sync="nextDisplay" width="600px">
        <el-form :model="nextData" disabled label-width="80px">
          <el-form-item label="退款编号">
            <el-input v-model="nextData.name1"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="nextSubmit">确 定</el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: 'QR20191206171900602EoilqHvTCs',
  name2: '深圳积微贸易有限公司',
  name3: 'c035a381a19346db93d813ac1f564bb3',
  name4: '0.01',
  name5: '退款成功',
  name6: '2019-12-06 17:30:01',
  name7: '20191206173001646',
  name8: '04',
  name9: '商户可用余额不足'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      performDisplay: false, //执行
      performModelData: {},
      nextDisplay: false,
      nextData: { name1: 'RF20191206172950783ViKRxGhisl' },
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
      console.log(v);
    }
  },
  created() {},
  methods: {
    performModelCancel() {
      this.performDisplay = false;
    },
    performModelSubmit() {
      console.log(this.performModelData);
      this.performDisplay = false;
    },
    performClick(row) {
      this.performDisplay = true;
      this.performModelData = row;
      console.log(row);
    },
    goToNext() {
      this.nextDisplay = true;
    },
    nextSubmit() {
      this.nextDisplay = false;
      this.performDisplay = false;
    },
    goToSearch() {
      //查询
      console.log(this.examine, '查询');
    },
    lookClick(row) {
      this.lookDisplay = true;
      console.log(row);
    }
  }
};
</script>

<style lang='scss'>
.aggregate-exception-page {
  .alert-model-form-header {
    padding-top: 20px;
    font-size: 20px;
    text-align: center;
    border-top: 1px solid #eee;
  }
  .next-wrap {
    text-align: right;
    button {
      display: inline-block;
      padding: 10px 20px;
      background-color: #ccc;
    }
  }
  .el-dialog__body {
    padding-top: 0;
    padding-bottom: 10px;
  }
  .alert-model-form {
    .el-form-item {
      width: 33%;
      margin-right: 0;
    }
  }
}
</style>