<template>
  <!-- 系统管理 => 菜单管理 -->
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="菜单代码" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="菜单名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="URL" prop="name3">
            <el-input v-model="examine.name3"></el-input>
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
          <el-table-column prop='name1' label='菜单编号' min-width="80"></el-table-column>
          <el-table-column prop='name2' label='菜单代码' min-width="80"></el-table-column>
          <el-table-column prop='name3' label='菜单名称' min-width="140"></el-table-column>
          <el-table-column prop='name4' label='上级编号' min-width="80"></el-table-column>
          <el-table-column prop='name5' label='功能级别' min-width="80"></el-table-column>
          <el-table-column prop='name6' label='URL' min-width="140"></el-table-column>
          <el-table-column prop='name7' label='是否是菜单' min-width="100"></el-table-column>
          <el-table-column prop='name8' label='是否有效' min-width="100"></el-table-column>
          <el-table-column prop='name9' label='是否要授权' min-width="100"></el-table-column>
          <el-table-column prop='name10' label='创建人' min-width="120"></el-table-column>
          <el-table-column prop='name10' min-width="140" label='创建时间'></el-table-column>

          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
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
    <alert-model v-show="editorDisplay" title="角色修改" @on-submit="editorModelSubmit" @on-cancel="editorModelCancel">
      <el-form ref="alertEditorModelForm" :model="editorModelData" class="alert-model-form" label-width="80px" :show-message="false">
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

      </el-form>
    </alert-model>
  </div>

</template>

<script>
const testData = {
  name1: '1',
  name2: '1001',
  name3: '文件上传',
  name4: '2',
  name5: '2',
  name6: '/demo/fileupload',
  name7: 'Y',
  name8: 'Y',
  name9: 'N',
  name10: '创建人',
  name11: '2015-12-22 6:08:54'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
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
    searchText(v) {
      console.log(v);
    }
  },
  created() {},
  methods: {
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit(c) {
      console.log(this.editorModelData);
      c();
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

<style lang='scss' scoped></style>