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
          <el-table-column prop='name1' label='发送交广科技流水号' min-width="180"></el-table-column>
          <el-table-column prop='name2' label='交易编号' min-width="180"></el-table-column>
          <el-table-column prop='name3' label='手机号码' min-width="120"></el-table-column>
          <el-table-column prop='name4' label='交易码' min-width="80"></el-table-column>
          <el-table-column prop='name5' label='客户名称' min-width="120"></el-table-column>
          <el-table-column prop='name6' label='证件号码' min-width="160"></el-table-column>
          <el-table-column prop='name7' label='发送日期' min-width="100"></el-table-column>
          <el-table-column prop='name8' label='返回交易流水号' min-width="120"></el-table-column>
          <el-table-column prop='name9' label='返回时间' min-width="120"></el-table-column>
          <el-table-column prop='name10' label='返回码' min-width="100"></el-table-column>
          <el-table-column prop='name11' label='返回信息' min-width="120"></el-table-column>
          <el-table-column prop='name12' label='对账状态' min-width="100"></el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 修改model -->
    <alert-model v-show="display" :display.sync="display" @on-submit="editorModelSubmit" @on-cancel="editorModelCancel" title="测试">
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
  name1: 'TBREGISTER0072018083110094857500001414',
  name2: 'TRANSFLOW0072018083110094854700001413',
  name3: '15392708118',
  name4: '1011',
  name5: 'assassa',
  name6: '140181200005272154',
  name7: '20180831',
  name8: '注册验证',
  name9: '20180831',
  name10: '100019',
  name11: '实名信息重复注册',
  name12: 'ssssssss'
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
      tableData: new Array(10).fill(testData),
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