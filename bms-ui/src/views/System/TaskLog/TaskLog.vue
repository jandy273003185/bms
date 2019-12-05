<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="部门代码" prop="departcode">
            <el-input v-model="examine.departcode"></el-input>
          </el-form-item>
          <el-form-item label="部门名称" prop="departname">
            <el-input v-model="examine.departname"></el-input>
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
          <el-table-column prop='serial' label='部门编号' min-width="50"></el-table-column>
          <el-table-column prop='departname' label='部门名称' min-width="120"></el-table-column>
          <el-table-column prop='departcode' label='部门代码' min-width="100"></el-table-column>
          <el-table-column prop='level' label='部门级别' min-width="80"></el-table-column>
          <el-table-column prop='preserial' label='上级部门' min-width="80"></el-table-column>
          <el-table-column prop='valid' label='是否有效' min-width="80"></el-table-column>
          <el-table-column prop='creator' label='创建人' min-width="100"></el-table-column>
          <el-table-column prop='date' label='创建时间' min-width="140"></el-table-column>
          <el-table-column prop='regenerator' label='更新人' min-width="100"></el-table-column>
          <el-table-column prop='turnovertime' label='更新时间' min-width="140"></el-table-column>

          <el-table-column fixed="right" label="操作" width="140">
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
  departname: '国银证宝',
  departcode: 'GZYB',
  level: '2',
  preserial: '0',
  valid: 'Y',
  creator: '创建人',
  date: '2015-12-22 6:08:54',
  regenerator: '管理员',
  turnovertime: '2015-12-22 6:08:54'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {
        departcode: '',
        departname: ''
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
    searchText(v) {
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
      this.addDisplay = true;
    }
  }
};
</script>

<style lang='scss' scoped>