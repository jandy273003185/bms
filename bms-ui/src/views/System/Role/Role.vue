<template>
  <!-- 系统管理 => 角色管理 -->
  <div class="system-role-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="角色代码" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="角色名称" prop="name2">
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
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="name1" min-width="50" label="编号"></el-table-column>
          <el-table-column prop='name2' min-width="100" label="角色代码"></el-table-column>
          <el-table-column prop='name3' min-width="100" label="角色名称"></el-table-column>
          <el-table-column prop='name4' min-width="100" label="角色说明"></el-table-column>
          <el-table-column prop='name5' min-width="80" label="是否有效"></el-table-column>
          <el-table-column prop='name6' min-width="80" label="创建人"></el-table-column>
          <el-table-column prop='name7' min-width="200" label="创建时间"></el-table-column>
          <el-table-column prop='name8' min-width="100" label="最后更改人"></el-table-column>
          <el-table-column prop='name9' min-width="200" label="最后更改时间"></el-table-column>
          <el-table-column fixed="right" label="操作" width="140">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookClick(scope.row)">查看</el-button>
              <el-button type="text" size="small" @click="editorClick(scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 角色新增 -->
    <el-dialog title="角色新增" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="80px" :show-message="false">
        <el-form-item prop="name1" label="角色名称" required>
          <el-input v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="角色代码" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="角色说明" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="是否有效" required>
          <el-select v-model="addModelData.name4" placeholder="选择角色状态">
            <el-option label="有效" value="1"></el-option>
            <el-option label="无效" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动性质">
          <el-checkbox-group v-model="addModelData.name5">
            <el-checkbox v-for="(item,index) in modelDataCheckboxList" :key="index" :label="item"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改 查看 model -->
    <el-dialog title="角色修改" :visible.sync="editorDisplay" width="600px">
      <el-form ref="alertEditorModelForm" disabled :model="editorModelData" class="alert-model-form" label-width="80px" :show-message="false">
        <el-form-item prop="name1" label="角色名称" required>
          <el-input v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="角色代码" required>
          <el-input v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="角色说明" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="是否有效" required>
          <el-select v-model="editorModelData.name4" placeholder="选择角色状态">
            <el-option label="有效" value="1"></el-option>
            <el-option label="无效" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动性质">
          <el-checkbox-group v-model="editorModelData.name5">
            <el-checkbox v-for="(item,index) in modelDataCheckboxList" :key="index" :label="item"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div v-show="islook" slot="footer" class="dialog-footer">
        <el-button @click="editorModelCancel">取 消</el-button>
        <el-button type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: '1',
  name2: 'clear',
  name3: '王小虎',
  name4: '所有权限',
  name5: '有效',
  name6: '创建人',
  name7: '2015-12-22 6:08:54',
  name8: '石理',
  name9: '2019-12-04 03:53:03'
};

// 活动性质选项
const modelDataCheckboxList = [
  '系统管理',
  '码表维护',
  '商户管理',
  '客户管理',
  '交易管理',
  '我的工作空间',
  '科目管理',
  '调账管理',
  '账务管理',
  '交广科技信息查询',
  '银联交易查询',
  '异常列表',
  '工作流管理',
  '短信管理',
  '社交',
  '代理商管理',
  '实名认证',
  '聚合支付对账',
  '聚合支付',
  '学生管理',
  '代付管理',
  'APP管理',
  '物料管理'
];

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {
        //新增model数据
        name5: []
      },
      editorDisplay: false,
      modelDataCheckboxList: modelDataCheckboxList,
      editorModelData: {
        //修改model数据
        name5: []
      },
      islook: false, //编辑还是查看
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
      console.log(this.addModelData);
      this.addDisplay = false;
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    editorModelSubmit() {
      // http提交数据
      console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.islook = true;
      this.editorModelData = row;
      console.log(row);
    },
    lookClick(row) {
      this.editorDisplay = true;
      this.islook = false;
      this.editorModelData = row;
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
.system-role-page {
  .el-checkbox {
    width: 25%;
  }
}
</style>