<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="86px" :inline="true" ref="controlQueryForm">
          <el-form-item label="用户组名称" prop="name1">
            <el-input v-model="examine.name1"></el-input>
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
          <el-table-column prop='name1' label='用户组编号'></el-table-column>
          <el-table-column prop='name2' label='用户组名称'></el-table-column>
          <el-table-column prop='name3' label='用户组类型'></el-table-column>

          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="editorClick(scope.row)">删除</el-button>
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
  name1: '7580',
  name2: '【七分钱支付】商户审核已通过',
  name3: '短信',
  name4: '13268253637',
  name5: '2019-12-02 21:52:22',
  name6: 'ERROR',
  name7: '0151912022152222990150',
  name8: '注册验证',
  name9: 'ssssssssss'
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