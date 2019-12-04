<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="86px" :inline="true" ref="controlQueryForm">
          <el-form-item label="任务名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="执行类路径" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="执行主机" prop="name3">
            <el-input v-model="examine.name3"></el-input>
          </el-form-item>

          <el-form-item label="是否开启" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="开启" value="1"></el-option>
              <el-option label="停止" value="0"></el-option>
            </el-select>
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
          <el-table-column prop='name1' label='执行主机' width="80"></el-table-column>
          <el-table-column prop='name2' label='任务名称' width="140"></el-table-column>
          <el-table-column prop='name3' label='执行类路径' width="220"></el-table-column>
          <el-table-column prop='name4' label='执行CRON时间' width="120"></el-table-column>
          <el-table-column prop='name5' label='是否启用' width="80"></el-table-column>
          <el-table-column prop='name6' label='任务状态' width="90"></el-table-column>
          <el-table-column prop='name7' label='业务参数' min-width="250"></el-table-column>
          <el-table-column prop='name8' label='创建人' width="80"></el-table-column>
          <el-table-column prop='name9' label='创建时间' width="160"></el-table-column>
          <el-table-column prop='name10' label='备注' width="180"></el-table-column>

          <el-table-column fixed="right" label="操作" width="60">
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
  name1: 'app1',
  name2: '七分钱商户端app统计历史时报',
  name3: 'com.sevenpay.scheduler.jobs.platform.MasterJob',
  name4: '20 13 15 * * *',
  name5: '开启',
  name6: 'exception',
  name7: 'http://192.168.1.71:8080/servlet/countMerchantTradeHistoryHours',
  name8: '无灵通',
  name9: '2019-02-27 02:35:26',
  name10: '只执行一次，执行完之后状态改为关闭状态'
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