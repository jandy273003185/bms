<template>
  <!-- 商户管理 > 商户列表 -->
  <div>
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
          <el-form-item label="手机号" prop="name4">
            <el-input v-model="examine.name4"></el-input>
          </el-form-item>

          <el-form-item label="商户状态" prop="name5">
            <el-select v-model="examine.name5" placeholder="请选择">
              <el-option label="有效" value="1"></el-option>
              <el-option label="待审核" value="2"></el-option>
              <el-option label="注销" value="3"></el-option>
              <el-option label="冻结" value="4"></el-option>
              <el-option label="审核不通过" value="5"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="审核状态" prop="name6">
            <el-select v-model="examine.name6" placeholder="请选择">
              <el-option label="审核通过" value="1"></el-option>
              <el-option label="待审核" value="2"></el-option>
              <el-option label="审核不通过" value="3"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="录入时间" prop="name7">
            <el-date-picker v-model="examine.name7" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">新增<i class="el-icon-circle-plus-outline"></i></el-button>
        <el-button disabled type="info" @click="batchAuditData">批量审核资料<i class="el-icon-document-checked"></i></el-button>
        <el-button disabled type="info" @click="batchEntry">批量录入<i class="el-icon-upload2"></i></el-button>
        <el-button type="info" @click="exportFile">导出<i class="el-icon-download"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border>
          <el-table-column prop='name1' label='商户名称' min-width="100"></el-table-column>
          <el-table-column prop='name2' label='商户简称' width="90"></el-table-column>
          <el-table-column prop='name3' label='商户编号' min-width="100"></el-table-column>
          <el-table-column prop='name4' label='账号' width="110"></el-table-column>
          <el-table-column prop='name5' label='录入时间' min-width="160"></el-table-column>
          <el-table-column prop='name6' label='商户状态' width="80"></el-table-column>
          <el-table-column prop='name7' label='审核状态' width="90"></el-table-column>
          <el-table-column prop='name8' label='审核人' width="90"></el-table-column>

          <el-table-column fixed="right" label="操作" width="220">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookClick(scope.row)">预览</el-button>
              <el-button type="text" size="small" @click="editorClick(scope.row)">商户更新</el-button>
              <el-button type="text" size="small" @click="performClick(scope.row)">商户审核</el-button>
              <el-button type="text" size="small" @click="ReportedClick(scope.row)">报备</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 报备渠道选择 -->
    <el-dialog title="报备渠道选择" :visible.sync="performDisplay" width="600px">
      <el-form :model="performModelData" class="alert-model-form" label-width="100px">
        <el-form-item prop="name1" label="报备渠道" required>
          <el-select v-model="performModelData.name1" placeholder="请选择">
            <el-option label="随行付" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="performModelCancel">取 消</el-button>
        <el-button type="primary" @click="performModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: '葡萄酒庄',
  name2: 'ptjz',
  name3: 'M051537134435794',
  name4: '13778878879',
  name5: '2019-12-23 10:18:59',
  name6: '有效',
  name7: '审核通过',
  name8: '黄金华'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      performDisplay: false, //执行
      performModelData: {},
      tableData: new Array(10).fill(testData),
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
    batchAuditData() {
      window.console.log('批量审核资料');
    },
    batchEntry() {
      window.console.log('批量录入');
    },
    exportFile() {
      window.console.log('导出');
      this.$message('download');
    },
    performModelCancel() {
      this.performDisplay = false;
    },
    performModelSubmit() {
      window.console.log(this.performModelData);
      this.performDisplay = false;
    },
    goToSearch() {
      //查询
      window.console.log(this.examine, '查询');
    },
    // 新增1 + 预览0 + 商户更新2 + 商户审核3
    // 新增
    insertItem() {
      this.$router.push('/merchants/list/operation/1');
    },
    // 审核
    performClick(row) {
      this.$router.push('/merchants/list/operation/3');
      window.console.log(row);
    },
    // 查看
    lookClick(row) {
      window.console.log(row);
      this.$router.push('/merchants/list/operation/0');
    },
    // 更新
    editorClick(row) {
      window.console.log(row);
      this.$router.push('/merchants/list/operation/2');
    },
    // 报备
    ReportedClick() {
      this.performDisplay = true;
    }
  }
};
</script>

<style lang='scss'></style>