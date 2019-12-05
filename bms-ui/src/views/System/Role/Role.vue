<template>
  <!-- 系统管理 => 角色管理 -->
  <div>
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
              <el-button type="text" size="small" @click="editorClick(scope.row)">查看</el-button>
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
    <alert-model v-show="addDisplay" title="用户新增" @on-submit="addModelSubmit" @on-cancel="addModelCancel">
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
            <el-checkbox label="美食/餐厅线上活动"></el-checkbox>
            <el-checkbox label="地推活动"></el-checkbox>
            <el-checkbox label="线下主题活动"></el-checkbox>
            <el-checkbox label="单纯品牌曝光"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
    </alert-model>

    <!-- 修改model -->
    <alert-model v-show="editorDisplay" title="用户修改" @on-submit="editorModelSubmit" @on-cancel="editorModelCancel">
      <el-form ref="alertModelForm" :model="editorModelData" class="alert-model-form" label-width="80px" :show-message="false">
        <el-form-item prop="name1" label="编号" required>
          <el-input disabled v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="员工编号" required>
          <el-input disabled v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="用户名" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="密码" required>
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="真是姓名" required>
          <el-input v-model="editorModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="昵称">
          <el-input v-model="editorModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name8" label="状态" required>
          <el-select v-model="editorModelData.name8" placeholder="请选择用户状态">
            <el-option label="(VALID)-生效" value="VALID"></el-option>
            <el-option label="(FREEZE)-冻结" value="FREEZE"></el-option>
            <el-option label="(LEAVE)-离职" value="LEAVE"></el-option>
            <el-option label="(LOGIN)-已登陆" value="LOGIN"></el-option>
          </el-select>
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

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {
        name5: []
      },
      editorDisplay: false,
      editorModelData: {},
      tableData: new Array(5).fill(testData),
      paginationOps: {
        pageSizes: [5, 10, 15, 20],
        total: 100
      }
    };
  },
  watch: {
    // 监听search传来的数据
  },
  created() {},
  methods: {
    addModelSubmit(c) {
      c();
    },
    addModelCancel() {
      this.addDisplay = false;
    },
    editorModelSubmit(c) {
      console.log(this.editorModelData);
      c();
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorClick(row) {
      this.editorDisplay = true;
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

<style lang='scss' scoped>