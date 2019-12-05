<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="120px" :inline="true" ref="controlQueryForm1" class="message-form">
          <el-form-item label="订单编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="当前状态" prop="name2">
            <el-select v-model="examine.name2" placeholder="请选择">
              <el-option label="短信" value="1"></el-option>
              <el-option label="邮件" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间" prop="name3">
            <el-date-picker v-model="examine.name3" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>
        <el-form :model="examine" label-width="120px" :inline="true" ref="controlQueryForm2">
          <el-form-item label="模块" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="注册验证" value="1"></el-option>
              <el-option label="登录密码找回验证" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="业务类型" prop="name5">
            <el-select v-model="examine.name5" placeholder="请选择">
              <el-option label="注册验证" value="1"></el-option>
              <el-option label="登录密码找回验证" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="最后创建时间" prop="name6">
            <el-date-picker v-model="examine.name6" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
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
          <el-table-column prop='name1' label='订单编号' min-width="170"></el-table-column>
          <el-table-column prop='name2' label='异常类型' min-width="80"></el-table-column>
          <el-table-column prop='name3' label='级别' min-width="80"></el-table-column>
          <el-table-column prop='name4' label='模块' min-width="120"></el-table-column>
          <el-table-column prop='name5' label='业务类型' min-width="80"></el-table-column>
          <el-table-column prop='name6' label='异常码' min-width="150"></el-table-column>
          <el-table-column prop='name7' label='当前状态' min-width="60"></el-table-column>
          <el-table-column prop='name8' label='创建时间' min-width="120"></el-table-column>
          <el-table-column prop='name9' label='最后更新时间' min-width="120"></el-table-column>
          <el-table-column prop='name10' label='备注' min-width="120"></el-table-column>

          <el-table-column fixed="right" label="异常信息描述" width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editorClick(scope.row)">查看内容</el-button>
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
  name1: 'COREMSGID0152019112720201360800001020',
  name2: '系统',
  name3: '紧急',
  name4: '七分钱核心系统',
  name5: '其它业务',
  name6: 'LOG_HEAD_DB_EXCEPTION',
  name7: '创建',
  name8: '2019-12-02 21:52:22',
  name9: '2019-12-02 21:52:22',
  name10: 'ssssssssss'
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

<style lang='scss'>
.message-form {
  .el-input {
    input {
      width: 193px;
    }
  }
}
</style>