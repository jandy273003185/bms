<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="角色代码" prop="usercode">
            <el-input v-model="examine.usercode"></el-input>
          </el-form-item>
          <el-form-item label="角色名称" prop="username">
            <el-input v-model="examine.username"></el-input>
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

    <!-- 修改model -->
    <alert-model v-show="display" :display.sync="display" @put="modelSubmit" title="测试">
      <el-form :model="modelData" class="alert-model-form" label-width="80px">
        <el-form-item :label="modelData.label">
          <el-input v-model="modelData.value" :placeholder="`请输入${modelData.label}`" />
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
      examine: {
        username: '',
        usercode: ''
      },
      display: false,
      modelData: {
        label: '账户名', //label
        value: '', //输入值(默认值)
        type: '', //表单类型 text select ...
        options: [{ label: '', value: '' }], //type为selec时的选项
        rules: {}, //校验规则
        disable: false, //禁止修改
        reuqire: false //是否必填
      },
      tableData: new Array(5).fill(testData),
      paginationOps: {
        pageSizes: [5, 10, 15, 20],
        total: 100
      }
    };
  },
  watch: {
    // 监听search传来的数据
    searchText(v, o) {
      if (!v || v === o) return;
      console.log(v);
    }
  },
  created() {},
  methods: {
    toggle() {
      this.display = true;
    },
    modelSubmit() {
      console.log(this.modelData);
    },
    editorClick(row) {
      this.display = true;
      console.log(row);
    },
    goToSearch() {
      //查询
      console.log(this.examine, '查询');
    },
    insertItem() {
      // 新增
      console.log('新增');
    }
  }
};
</script>

<style lang='scss' scoped>