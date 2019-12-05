<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="86px" :inline="true" ref="controlQueryForm">
          <el-form-item label="手机号码" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="客户名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="交易编号" prop="name3">
            <el-input v-model="examine.name3"></el-input>
          </el-form-item>
          <el-form-item label="证件号码" prop="name4">
            <el-input v-model="examine.name4"></el-input>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      </template>
      <template slot="tableInner">
        <el-table :data="tableData" border class="nofix">
          <el-table-column prop='name1' label='发送交广科技流水号' min-width="200"></el-table-column>
          <el-table-column prop='name2' label='交易编号' min-width="100"></el-table-column>
          <el-table-column prop='name3' label='交易类型' min-width="80"></el-table-column>
          <el-table-column prop='name4' label='交广科技卡号' min-width="120"></el-table-column>
          <el-table-column prop='name5' label='金额' min-width="100"></el-table-column>
          <el-table-column prop='name6' label='会计日期' min-width="140"></el-table-column>
          <el-table-column prop='name7' label='发送时间' min-width="140"></el-table-column>
          <el-table-column prop='name8' label='返回流水号' min-width="120"></el-table-column>
          <el-table-column prop='name9' label='返回时间' min-width="140"></el-table-column>
          <el-table-column prop='name10' label='返回码' min-width="100"></el-table-column>
          <el-table-column prop='name11' label='返回信息' min-width="120"></el-table-column>
          <el-table-column prop='name12' label='对账状态' min-width="120"></el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>
  </div>
</template>

<script>
const testData = {
  name1: '发送交广科技流水号',
  name2: '交易编号',
  name3: '交易类型',
  name4: '交广科技卡号',
  name5: '金额',
  name6: '会计日期',
  name7: '发送时间',
  name8: '返回流水号',
  name9: '返回时间',
  name10: '返回码',
  name11: '返回信息',
  name12: '对账状态'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
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