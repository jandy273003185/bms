<template>
  <!-- 商户管理 > 商户渠道列表 -->
  <div class="merchants-channel-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="100px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="商户名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="商户渠道编号" prop="name3">
            <el-input v-model="examine.name3"></el-input>
          </el-form-item>
          <el-form-item label="通道名" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="迅联" value="1"></el-option>
              <el-option label="广东华兴银行" value="2"></el-option>
              <el-option label="翼行付" value="3"></el-option>
              <el-option label="随行付" value="4"></el-option>
              <el-option label="商盟支付" value="5"></el-option>
              <el-option label="平安付-壹钱包" value="6"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border class="tableInner-content">
          <!-- 商户名 商户号 通道名称 商户通道编号 商户通道KEY 状态 注释 创建时间 修改时间 -->
          <el-table-column prop='name1' label='商户名' width="100"></el-table-column>
          <el-table-column prop='name2' label='商户号' width="80"></el-table-column>
          <el-table-column prop='name3' label='通道名称' width="90"></el-table-column>
          <el-table-column prop='name4' label='商户通道编号' min-width="100"></el-table-column>
          <el-table-column prop='name5' label='商户通道KEY' min-width="100"></el-table-column>
          <el-table-column prop='name6' label='状态' width="60"></el-table-column>
          <el-table-column prop='name7' label='注释' min-width="80"></el-table-column>
          <el-table-column prop='name8' label='创建时间' min-width="80"></el-table-column>
          <el-table-column prop='name9' label='修改时间' min-width="80"></el-table-column>
          <!-- 详情 -->
          <el-table-column type="expand" label="查看详情" width="80">
            <template slot-scope="props">
              <!-- 支付类型 支付产品 微信AppID 微信Appsecret 状态 注释 -->
              <el-table :data="props.row.content" border class="tableInner-expand">
                <el-table-column prop='prop1' label='支付类型' min-width="100"></el-table-column>
                <el-table-column prop='prop2' label='支付产品' min-width="100"></el-table-column>
                <el-table-column prop='prop3' label='微信AppID' min-width="100"></el-table-column>
                <el-table-column prop='prop4' label='微信Appsecret' min-width="100"></el-table-column>
                <el-table-column prop='prop5' label='状态' min-width="100"></el-table-column>
                <el-table-column prop='prop6' label='注释' min-width="100"></el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button type="text" style="color:#DD5A43;" size="small" @click="deleteClick(scope.row)">商户渠道下线</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 添加门店 -->
    <el-dialog title="添加门店" :visible.sync="addDisplay" width="1200px" class="add-dialog-channel-form">
      <div class="alert-model-form-item-control">
        <el-form ref="alertAddModelForm" :inline="true" :model="addModelData" class="alert-model-form" :show-message="false">
          <el-form-item prop="name1" label="商户编号（七分钱）">
            <el-input v-model="addModelData.name1"></el-input>
          </el-form-item>
          <el-form-item prop="name2" label="渠道">
            <el-select v-model="addModelData.name2" placeholder="请选择">
              <el-option label="迅联" value="0"></el-option>
              <el-option label="广东华兴银行" value="1"></el-option>
              <el-option label="翼支付" value="2"></el-option>
              <el-option label="随行付" value="3"></el-option>
              <el-option label="商盟支付" value="4"></el-option>
              <el-option label="平安付-壹钱包" value="5"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="name3" label="商户编号（渠道）">
            <el-input v-model="addModelData.name3"></el-input>
          </el-form-item>
          <el-form-item prop="name4" label="商户KEY（渠道）">
            <el-input v-model="addModelData.name4"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="info" round @click="addOption">增加选项</el-button>
      </div>

      <!-- el-form 绑定model逻辑待修改 -->
      <div class="alert-model-form-item-ops" v-for="item in addModelDataSum" :key="item">
        <el-form ref="alertAddModelForm" :inline="true" :model="addModelData" class="alert-model-form" label-width="95px" :show-message="false">
          <el-form-item prop="name1" label="通道">
            <el-select v-model="addModelData.name1" placeholder="请选择">
              <el-option label="微信" value="0"></el-option>
              <el-option label="翼支付" value="1"></el-option>
              <el-option label="支付宝" value="2"></el-option>
              <el-option label="云闪付" value="3"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="name3" label="产品">
            <el-select v-model="addModelData.name2" placeholder="请选择">
              <el-option label="扫码" value="0"></el-option>
              <el-option label="刷卡" value="1"></el-option>
              <el-option label="公共号（线下）" value="2"></el-option>
              <el-option label="公共号（线上）" value="3"></el-option>
              <el-option label="H5" value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="name3" label="AppId">
            <el-input v-model="addModelData.name3"></el-input>
          </el-form-item>
          <el-form-item prop="name4" label="AppSecret">
            <el-input v-model="addModelData.name4"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="text" class="el-icon-circle-close" @click="removeOption"></el-button>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addModelSubmit">提交</el-button>
      </div>
    </el-dialog>

    <!-- 商户渠道下线 -->
    <el-dialog title="商户渠道下线" :visible.sync="deleteDisplay" width="600px">
      <div class="dialog-deleted-content">您确定要下线该商户号为<span>[钱记到家4- SUIXING_PAY(SUIXING_PAY) ]</span>的商户信息吗？</div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="deleteModelCancel">取 消</el-button>
        <el-button type="primary" @click="deleteModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData1 = {
  name1: '钱记到家4',
  name2: '701790',
  name3: '随行付',
  name4: '836603073720000',
  name5: '836603073720000',
  name6: '正常',
  name7: '正常',
  name8: '2019-12-18 03:47',
  name9: '2019-12-18 03:47',
  content: [
    {
      prop1: '微信',
      prop2: '公众号',
      prop3: 'wx1fc84beff3d0eeb8',
      prop4: '055e6b98ac3b4b6d7b704a6c3e884d64',
      prop5: '正常',
      prop6: ''
    }
  ]
};
import cashier from '@/tools/cashier';
export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {},
      addModelDataSum: 0,
      restaurants: [],
      deleteDisplay: false,
      // tableData: new Array(5).fill(testData),
      tableData: [testData1],
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
    // 商户候选操作
    merchantQuerySearch(queryString, cb) {
      var restaurants = cashier;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      cb(results);
    },
    createFilter(queryString) {
      return restaurant =>
        restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0;
    },
    // 选中候选中的项
    handleSelect(item) {
      window.console.log(item, 'item');
    },
    addModelSubmit() {
      this.addDisplay = false;
      window.console.log(this.addModelData);
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    addOption() {
      this.addModelDataSum = this.addModelDataSum + 1;
    },
    removeOption() {
      this.addModelDataSum = this.addModelDataSum - 1;
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.editorModelData = row;
      window.console.log(row);
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit() {
      window.console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    deleteModelSubmit() {
      this.deleteDisplay = false;
    },
    deleteModelCancel(row) {
      this.deleteDisplay = false;
      window.console.log(row);
    },
    deleteClick(row) {
      this.deleteDisplay = true;
      window.console.log(row);
    },
    goToSearch() {
      //查询
      window.console.log(this.examine, '查询');
    },
    insertItem() {
      // 新增
      this.addDisplay = true;
    }
  }
};
</script>

<style lang='scss'>
.merchants-channel-page {
  .el-table__expand-column {
    color: #69aa46;
  }
  .el-table__expanded-cell {
    padding: 5px !important;
  }
  .el-table__row {
    height: 33px;
  }

  .alert-model-form {
    .el-form-item {
      width: 25%;
      margin: 0;
    }
    .el-input__inner {
      width: 130px;
    }
  }

  .alert-model-form-item-control {
    background-color: #fefefe;
    margin-bottom: 10px;
    text-align: center;
    border: 1px solid #ddd;
    padding: 10px;
  }

  .alert-model-form-item-ops {
    .el-form-item {
      margin-bottom: 0 !important;
    }

    .el-input__inner {
      width: 170px;
    }
    .el-button {
      font-size: 28px;
      padding: 0;
    }
    display: flex;
    justify-content: space-between;
    border: 1px dashed #ccc;
    box-sizing: border-box;
    padding: 10px;
    background-color: #fefefe;
    margin-bottom: 10px;
  }

  .add-dialog-channel-form {
    .dialog-footer {
      text-align: center;
    }
  }
}
</style>
