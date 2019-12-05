<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="菜单代码" prop="menucode">
            <el-input v-model="examine.menucode"></el-input>
          </el-form-item>
          <el-form-item label="菜单名称" prop="menuname">
            <el-input v-model="examine.menuname"></el-input>
          </el-form-item>
          <el-form-item label="URL" prop="menurl">
            <el-input v-model="examine.menurl"></el-input>
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
          <el-table-column prop='serial' label='菜单编号' min-width="80"></el-table-column>
          <el-table-column prop='menucode' label='菜单代码' min-width="80"></el-table-column>
          <el-table-column prop='menuname' label='菜单名称' min-width="140"></el-table-column>
          <el-table-column prop='preserial' label='上级编号' min-width="80"></el-table-column>
          <el-table-column prop='level' label='功能级别' min-width="80"></el-table-column>
          <el-table-column prop='url' label='URL' min-width="140"></el-table-column>
          <el-table-column prop='isMenu' label='是否是菜单' min-width="100"></el-table-column>
          <el-table-column prop='valid' label='是否有效' min-width="100"></el-table-column>
          <el-table-column prop='auth' label='是否要授权' min-width="100"></el-table-column>
          <el-table-column prop='creator' label='创建人' min-width="120"></el-table-column>
          <el-table-column prop='date' min-width="140" label='创建时间'></el-table-column>

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
  menucode: '1001',
  menuname: '文件上传',
  preserial: '2',
  level: '2',
  url: '/demo/fileupload',
  isMenu: 'Y',
  valid: 'Y',
  auth: 'N',
  creator: '创建人',
  date: '2015-12-22 6:08:54'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {
        menuname: '',
        menucode: '',
        menurl: ''
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
      this.addDisplay = true;
    }
  }
};
</script>

<style lang='scss' scoped>