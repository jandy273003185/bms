<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="100px" :inline="true" ref="controlQueryForm">
          <el-form-item label="撤销流水号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="原交易订单号" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="撤销申请时间" prop="name3">
            <el-date-picker v-model="examine.name3" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="客户账号" prop="name4">
            <el-input v-model="examine.name4"></el-input>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">新增申请<i class="el-icon-circle-plus-outline"></i></el-button>
      </template>
      <template slot="tableInner">
        <el-table :data="tableData" border>
          <el-table-column prop='name1' label='订单号' min-width="140"></el-table-column>
          <el-table-column prop='name2' label='原交易订单号' min-width="130"></el-table-column>
          <el-table-column prop='name3' label='原交易时间' min-width="100"></el-table-column>
          <el-table-column prop='name4' label='充值金额' min-width="80"></el-table-column>
          <el-table-column prop='name5' label='客户账号' min-width="110"></el-table-column>
          <el-table-column prop='name6' label='申请人' min-width="85"></el-table-column>
          <el-table-column prop='name7' label='申请时间' min-width="100"></el-table-column>
          <el-table-column prop='name8' label='状态' min-width="110"></el-table-column>
          <el-table-column prop='name9' label='撤销描述信息' min-width="140"></el-table-column>
          <el-table-column prop='name10' label='审核人' min-width="90"></el-table-column>
          <el-table-column prop='name11' label='审核状态' min-width="100"></el-table-column>
          <el-table-column prop='name12' label='审核时间' min-width="100"></el-table-column>
          <el-table-column prop='name13' label='备注' min-width="120"></el-table-column>

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
  name1: '2016082311214426300700000006',
  name2: '2016082310341700100000002',
  name3: '2016-08-23 23:34:38',
  name4: '550.00',
  name5: '18826582622',
  name6: '谢小良',
  name7: '2016-08-23 11:21:44',
  name8: '核心处理失败',
  name9: '清算测试充值撤销',
  name10: '谢小良',
  name11: '审核通过',
  name12: '2016-08-23 11:37:05',
  name13: '请求报文流水号重复'
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

<style lang='scss' scoped></style>