<template>
  <!-- 商户管理>商户设备管理 -->
  <div class="merchants-product-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="100px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>

          <el-form-item label="设备编号" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border>
          <el-table-column prop='name1' label='编号' width="60"></el-table-column>
          <el-table-column prop='name2' label='商户名称' min-width="100"></el-table-column>
          <el-table-column prop='name3' label='商户编号' min-width="160"></el-table-column>
          <el-table-column prop='name4' label='设备编号' min-width="110"></el-table-column>
          <el-table-column prop='name5' label='初始时间' width="160"></el-table-column>
          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <!-- 案件显示还需要判断 -->
              <el-button type="text" size="small" @click="editorClick(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="deleteClick(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 商户产品新增 -->
    <el-dialog title="商户产品新增" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="120px" :show-message="false">
        <el-form-item prop="name1" label="商户名称" required>
          <el-autocomplete v-model="addModelData.name1" :fetch-suggestions="merchantQuerySearch" placeholder="请输入商户名" @select="handleSelect">
          </el-autocomplete>
        </el-form-item>
        <el-form-item prop="name2" label="设备编号" required>
          <el-autocomplete v-model="addModelData.name2" :fetch-suggestions="equipmentQuerySearch" placeholder="请输入商户名" @select="handleSelect">
          </el-autocomplete>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 商户产品修改 -->
    <el-dialog title="商户产品修改" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" :disabled="isAudit" class="alert-model-form" label-width="120px" :show-message="false">
        <el-form-item prop="name1" label="编号" required>
          <el-input disabled v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="商户名称" required>
          <el-input disabled v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="设备编号" required>
          <el-autocomplete v-model="editorModelData.name3" :fetch-suggestions="equipmentQuerySearch" placeholder="请输入商户名" @select="handleSelect">
          </el-autocomplete>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editorModelCancel">取 消</el-button>
        <template v-if="isAudit">
          <el-button type="primary" @click="auditModelSubmit">审核通过</el-button>
        </template>
        <el-button v-else type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 任务删除 -->
    <el-dialog title="删除任务" :visible.sync="deleteDisplay" width="600px">
      <div class="dialog-deleted-content">您确定要删除该物料<span>[47]</span>么？</div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="deleteModelCancel">取 消</el-button>
        <el-button type="primary" @click="deleteModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: '47',
  name2: '钱记到家2',
  name3: 'c2bbcde3040e4d3285768388793afb25',
  name4: 'QCF420191020177791',
  name5: '2019-12-26 02:57:06'
};

import merchants2 from '@/tools/merchants2'; // 商户候选数据
import EquipmentNumber from '@/tools/data3'; //设备
export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {},
      editorDisplay: false,
      editorModelData: {},
      deleteDisplay: false, //任务删除
      deleteModelData: {},
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
    addModelSubmit() {
      this.$refs['alertAddModelForm'].validate((files, object) => {
        if (files) {
          // 验证通过 发送请求添加数据到数据库
          this.addDisplay = false;
        } else {
          const keys = Object.keys(object);
          this.$message.error(`${keys[0]}不可为空`);
        }
      });
      console.log(this.addModelData);
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    // 商户候选操作
    merchantQuerySearch(queryString, cb) {
      var restaurants = merchants2;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      cb(results);
    },
    // 设备候选
    equipmentQuerySearch(queryString, cb) {
      var restaurants = EquipmentNumber;
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
      console.log(item, 'item');
    },
    editorModelSubmit() {
      console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.editorModelData = row;
      this.isAudit = false;
      console.log(row);
    },
    auditModelSubmit() {
      console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    deleteClick(row) {
      this.deleteDisplay = true;
      this.deleteModelData = row;
      console.log(row);
    },
    deleteModelSubmit() {
      this.deleteDisplay = false;
    },
    deleteModelCancel(row) {
      this.deleteDisplay = false;
      console.log(row);
    },
    goToSearch() {
      //查询
      console.log(this.examine, '查询');
    },
    insertItem() {
      // 新增
      this.addDisplay = true;
    }
  }
};
</script>

<style lang='scss'>
.merchants-product-page {
  .alert-model-form {
    .el-input__inner {
      width: 440px;
    }
  }
}
</style>