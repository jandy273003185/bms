<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm1">
          <el-form-item label="七分钱退款编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="原始交易时间" prop="name2">
            <el-date-picker v-model="examine.name2" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="渠道订单号" prop="name3">
            <el-select v-model="examine.name3" placeholder="请选择">
              <el-option label="短信" value="1"></el-option>
              <el-option label="邮件" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="退款类型" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="注册验证" value="1"></el-option>
              <el-option label="登录密码找回验证" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>

        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm2">
          <el-form-item label="客户账号" prop="name5">
            <el-input v-model="examine.name5"></el-input>
          </el-form-item>
          <el-form-item label="退款申请时间" prop="name6">
            <el-date-picker v-model="examine.name6" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="商户编号" prop="name7">
            <el-select v-model="examine.name7" placeholder="请选择">
              <el-option label="短信" value="1"></el-option>
              <el-option label="邮件" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="退款状态" prop="name8">
            <el-select v-model="examine.name8" placeholder="请选择">
              <el-option label="注册验证" value="1"></el-option>
              <el-option label="登录密码找回验证" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>

        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm3" class="el-form-diy">
          <el-form-item label="原七分钱订单号" prop="name9">
            <el-input v-model="examine.name9"></el-input>
          </el-form-item>
          <el-form-item label="原始交易时间" prop="name10">
            <el-date-picker v-model="examine.name10" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="resetFormFileds">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border>
          <el-table-column prop='name1' label='七分钱退款编号' min-width="100"></el-table-column>
          <el-table-column prop='name2' label='渠道订单号' min-width="130"></el-table-column>
          <el-table-column prop='name3' label='原七分钱订单号' min-width="100"></el-table-column>
          <el-table-column prop='name4' label='退款类型' min-width="110"></el-table-column>
          <el-table-column prop='name5' label='原始交易金额' min-width="100"></el-table-column>
          <el-table-column prop='name6' label='原始交易时间' min-width="120"></el-table-column>
          <el-table-column prop='name7' label='客户账号' min-width="110"></el-table-column>
          <el-table-column prop='name8' label='商户编号' min-width="110"></el-table-column>
          <el-table-column prop='name9' label='退款金额' min-width="90"></el-table-column>
          <el-table-column prop='name10' label='退款申请时间' min-width="100"></el-table-column>
          <el-table-column prop='name11' label='账期' min-width="90"></el-table-column>
          <el-table-column prop='name12' label='退款状态' min-width="90"></el-table-column>
          <el-table-column prop='name13' label='审核状态' min-width="100"></el-table-column>
          <el-table-column prop='name14' label='核销状态' min-width="90"></el-table-column>
          <el-table-column prop='name15' label='备注' min-width="130"></el-table-column>

          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editorClick(scope.row)">查看详情</el-button>
              <el-button type="text" size="small" @click="editorClick(scope.row)">确认核销</el-button>
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
  name1: '2018011911193059300700000001',
  name2: 'TBREFUND0072018011911193114000012683',
  name3: '2018011611174300300000001',
  name4: '余额支付退款',
  name5: '0.02',
  name6: 'Wed Jan 17 01:17:44 CST 2018',
  name7: '13263000004',
  name8: 'M9144030035873982X0',
  name9: '0.01',
  name10: '2018-01-19 11:19:30',
  name11: '20180119',
  name12: '处理中',
  name13: '审核通过',
  name14: '未核销',
  name15: 'errCode:800002,errMsg:无效交易'
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
    },
    resetFormFileds() {
      this.$refs['controlQueryForm1'].resetFields();
      this.$refs['controlQueryForm2'].resetFields();
      this.$refs['controlQueryForm3'].resetFields();
    }
  }
};
</script>

<style lang='scss' scoped></style>