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
          <el-table-column prop="serial" min-width="50" label="编号"></el-table-column>
          <el-table-column prop='usercode' min-width="100" label="角色代码"></el-table-column>
          <el-table-column prop='username' min-width="100" label="角色名称"></el-table-column>
          <el-table-column prop='regenerator' min-width="100" label="角色说明"></el-table-column>
          <el-table-column prop='valid' min-width="80" label="是否有效"></el-table-column>
          <el-table-column prop='creator' min-width="80" label="创建人"></el-table-column>
          <el-table-column prop='date' min-width="200" label="创建时间"></el-table-column>
          <el-table-column prop='lastupdate' min-width="100" label="最后更改人"></el-table-column>
          <el-table-column prop='turnovertime' min-width="200" label="最后更改时间"></el-table-column>
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

    <!-- 用户新增 -->
    <alert-model v-show="addDisplay" title="角色新增" @on-submit="addModelSubmit" @on-cancel="addModelCancel">
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
    </alert-model>

    <!-- 修改 查看 model -->
    <alert-model v-show="editorDisplay" title="角色修改" @on-submit="editorModelSubmit" @on-cancel="editorModelCancel" :hideBtn="hideBtn">
      <el-form :disabled="look_editor" ref="alertAddModelForm" :model="editorModelData" class="alert-model-form" label-width="80px" :show-message="false">
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
    </alert-model>
  </div>
</template>

<script>
const testData = {
  serial: '1',
  usercode: 'clear',
  username: '王小虎',
  regenerator: '所有权限',
  valid: '有效',
  creator: '创建人',
  date: '2015-12-22 6:08:54',
  lastupdate: '石理',
  turnovertime: '2019-12-04 03:53:03'
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
        name5: modelDataCheckboxList
      },
      editorDisplay: false,
      modelDataCheckboxList: modelDataCheckboxList,
      editorModelData: {
        name5: modelDataCheckboxList
      },
      look_editor: false, //编辑还是查看
      hideBtn: false, //查看时，隐藏按钮组
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
    addModelSubmit(c) {
      console.log(this.addModelData);
      c();
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    editorModelSubmit(c) {
      console.log(this.editorModelData);
      c();
    },
    editorModelCancel() {
      this.editorDisplay = false;
      this.look_editor = false;
      this.hideBtn = false;
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.look_editor = false;
      console.log(row);
    },
    lookClick(row) {
      this.editorDisplay = true;
      this.look_editor = true;
      this.hideBtn = true;
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