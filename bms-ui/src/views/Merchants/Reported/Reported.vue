<template>
  <!-- 商户管理>商户报备列表 -->
  <div class="merchants-reported-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="86px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="商户名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="邮箱账号" prop="name3">
            <el-input v-model="examine.name3"></el-input>
          </el-form-item>

          <el-form-item label="报备渠道" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="随行付" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="报备状态" prop="name5">
            <el-select v-model="examine.name5" placeholder="请选择">
              <el-option label="报备成功" value="1"></el-option>
              <el-option label="报备失败" value="2"></el-option>
              <el-option label="提交审核中" value="3"></el-option>
              <el-option label="审核通过" value="4"></el-option>
              <el-option label="审核不通过" value="5"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="报备时间" prop="name6">
            <el-date-picker v-model="examine.name6" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
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
          <el-table-column prop='name1' label='商户名称' min-width="100"></el-table-column>
          <el-table-column prop='name2' label='商户简称' min-width="80"></el-table-column>
          <el-table-column prop='name3' label='商户编号' min-width="110"></el-table-column>
          <el-table-column prop='name4' label='邮箱账号' width="80"></el-table-column>
          <el-table-column prop='name5' label='报备渠道' width="80"></el-table-column>
          <el-table-column prop='name6' label='渠道商户号' width="100"></el-table-column>
          <el-table-column prop='name7' label='商户报备状态' width="100"></el-table-column>
          <el-table-column prop='name8' label='返回信息' width="100"></el-table-column>
          <el-table-column prop='name9' label='报备时间' width="160"></el-table-column>

          <el-table-column fixed="right" label="操作" width="80">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookClick(scope.row)">查看资料</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 商户报备详细 -->
    <el-dialog title="商户报备详细" :visible.sync="lookDisplay" class="alert-model-wrap" width="1000px">
      <h3>商户信息</h3>
      <el-form :model="lookModelData" :disabled="true" class="alert-model-form" :inline="true" label-width="140px" :show-message="false">
        <el-form-item prop="name4" label="商户编号">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="商户简称">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="商户名称">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
      </el-form>

      <h3>基本信息</h3>
      <el-form :model="lookModelData" :disabled="true" class="alert-model-form" :inline="true" label-width="140px" :show-message="false">
        <el-form-item prop="name4" label="商户类型">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="注册地址">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="营业执照编号">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <br />
        <el-form-item prop="name5" label="营业执照编号">
          <div class="preview-img"></div>
        </el-form-item>
      </el-form>

      <h3>法人信息</h3>
      <el-form :model="lookModelData" :disabled="true" class="alert-model-form" :inline="true" label-width="140px" :show-message="false">
        <el-form-item prop="name4" label="法人真实姓名">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="法人身份证号码">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="联系人姓名">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="联系人手机号">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="联系人邮箱">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <br />
        <el-form-item prop="name5" label="法人身份证正面">
          <div class="preview-img"></div>
        </el-form-item>
        <el-form-item prop="name5" label="法人身份证背面">
          <div class="preview-img"></div>
        </el-form-item>
      </el-form>

      <h3>结算信息</h3>
      <el-form :model="lookModelData" :disabled="true" class="alert-model-form" :inline="true" label-width="140px" :show-message="false">
        <el-form-item prop="name4" label="结算账户名称">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="银行卡号">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="结算类型">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="结算费率">
          <el-input v-model="lookModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="银行卡正面">
          <div class="preview-img"></div>
        </el-form-item>
        <el-form-item prop="name5" label="银行卡反面">
          <div class="preview-img"></div>
        </el-form-item>
        <el-form-item prop="name5" label="手持身份证正面">
          <div class="preview-img"></div>
        </el-form-item>
        <el-form-item prop="name5" label="微信联系人信息确认二维码">
          <div class="preview-img"></div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: '深圳市福田区又一碗餐饮店',
  name2: '金角馍王',
  name3: '7263355692839056155',
  name4: '',
  name5: '随行付',
  name6: '',
  name7: '',
  name8: '',
  name9: '2019-11-29 17:14:48'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      lookDisplay: false,
      lookModelData: {},
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
    lookModelCancel() {
      this.lookDisplay = false;
    },
    lookModelSubmit() {
      window.console.log(this.lookModelData);
      this.lookDisplay = false;
    },
    // 查看资料
    lookClick(row) {
      this.lookDisplay = true;
      this.lookModelData = row;
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
.merchants-reported-page {
  .preview-img {
    width: 120px;
    height: 100px;
    background-size: cover;
    background-repeat: no-repeat;
    background-color: #efefef;
  }

  .alert-model-wrap {
    h3 {
      height: 30px;
      line-height: 30px;
      padding-left: 10px;
      background-color: #eee;
    }
    .el-form-item {
      margin: 4px 0;
      width: 45%;
    }
  }
}
</style>