<template>
  <!-- 商户管理 > 收银员 -->
  <div class="merchants-rate-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="130px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户" prop="name1">
            <el-col>
              <el-autocomplete v-model="examine.name1" :fetch-suggestions="merchantQuerySearch" placeholder="请输入内容" @select="handleSelect">
              </el-autocomplete>
            </el-col>
          </el-form-item>
          <el-form-item label="收银员手机号" prop="name2">
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
        <!-- 商户 门店名称 收银员手机号 收银员姓名 是否有退款权限 是否有全门店权限 是否有效 创建时间 -->
        <el-table :data="tableData" border class="tableInner-content">
          <el-table-column prop='name1' label='商户' min-width="100"></el-table-column>
          <el-table-column prop='name2' label='门店名称' min-width="100"></el-table-column>
          <el-table-column prop='name3' label='收银员手机号' width="120"></el-table-column>
          <el-table-column prop='name4' label='收银员姓名' width="100"></el-table-column>
          <el-table-column prop='name5' label='是否有退款权限' width="120"></el-table-column>
          <el-table-column prop='name6' label='是否有全门店权限' width="140"></el-table-column>
          <el-table-column prop='name7' label='是否有效' width="100"></el-table-column>
          <el-table-column prop='name8' label='创建时间' width="160"></el-table-column>

          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
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

    <!-- 添加收银员 -->
    <el-dialog title="费率新增" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="150px" :show-message="false">
        <el-form-item label="商户" prop="name1" required>
          <el-col>
            <el-autocomplete v-model="addModelData.name1" :fetch-suggestions="merchantQuerySearch" placeholder="请输入内容" @select="handleSelect">
            </el-autocomplete>
          </el-col>
        </el-form-item>
        <el-form-item label="门店" prop="name2">
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="name3" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name4">
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="是否有退款权限">
          <el-select v-model="examine.name5" placeholder="请选择">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name6" label="是否有全门店权限">
          <el-select v-model="examine.name6" placeholder="请选择">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="登录密码" required>
          <el-input v-model="addModelData.name7"></el-input>
        </el-form-item>
        <el-form-item prop="name8" label="退款密码" required>
          <el-input v-model="addModelData.name8"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 协议修改 -->
    <el-dialog title="协议修改" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" class="editor-model-form" label-width="130px" :show-message="false">
        <el-form-item prop="name1" label="商户">
          <el-input disabled v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="门店">
          <el-input disabled v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="手机号" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="姓名">
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="是否有退款权限">
          <el-select v-model="examine.name5" placeholder="请选择">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name6" label="是否有全门店权限">
          <el-select v-model="examine.name6" placeholder="请选择">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="是否生效">
          <el-select v-model="examine.name7" placeholder="请选择">
            <el-option label="生效" value="1"></el-option>
            <el-option label="失效" value="0"></el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editorModelCancel">取 消</el-button>
        <el-button type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 任务删除 -->
    <el-dialog title="删除任务" :visible.sync="deleteDisplay" width="640px">
      <div class="dialog-deleted-content">您确定要删除该收银员么？</div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="deleteModelCancel">取 消</el-button>
        <el-button type="primary" @click="deleteModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: '钱记到家2',
  name2: '钱记到家宝安西乡店',
  name3: '13888888888',
  name4: 'Lucy',
  name5: '否',
  name6: '否',
  name7: '有效',
  name8: '2019-12-21 18:33:38'
};

import cashier from '@/tools/cashier';
export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {},
      restaurants: [],
      editorDisplay: false,
      editorModelData: {},
      deleteDisplay: false,
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
      this.$refs['alertAddModelForm'].validate((files, object) => {
        if (files) {
          // 验证通过 发送请求添加数据到数据库
          this.addDisplay = false;
        } else {
          const keys = Object.keys(object);
          this.$message.error(`${keys[0]}不可为空`);
        }
      });
      window.console.log(this.addModelData);
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
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
    lookClick() {
      this.editorDisplay = true;
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
.merchants-rate-page {
  .add-form-rate {
    .el-form-item__content {
      .el-input {
        margin: 0 4px;
        &:first-child {
          margin-left: 0;
        }
      }

      display: flex;
      i {
        display: inline-block;
        width: 230px;
      }
    }
  }
}
</style>