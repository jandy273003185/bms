<template>
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm1">
          <el-form-item label="七分钱订单号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="交广科技订单号" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="付方客户手机号" prop="name4">
            <el-input v-model="examine.name4"></el-input>
          </el-form-item>
          <el-form-item label="订单开始日期" prop="name3">
            <el-date-picker v-model="examine.name3" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>

        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm2">
          <el-form-item label="付方客户名称" prop="name5">
            <el-input v-model="examine.name5"></el-input>
          </el-form-item>
          <el-form-item label="收方客户手机号" prop="name6">
            <el-input v-model="examine.name6"></el-input>
          </el-form-item>
          <el-form-item label="收方客户名称	" prop="name7">
            <el-input v-model="examine.name7"></el-input>
          </el-form-item>
          <el-form-item label="账期" prop="name9">
            <el-date-picker v-model="examine.name9" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>

        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm3" class="el-form-diy">
          <el-form-item label="订单状态" prop="name8">
            <el-select v-model="examine.name8" placeholder="请选择">
              <el-option label="注册验证" value="1"></el-option>
              <el-option label="登录密码找回验证" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="订单类型" prop="name10">
            <el-select v-model="examine.name10" placeholder="请选择">
              <el-option label="注册验证" value="1"></el-option>
              <el-option label="登录密码找回验证" value="0"></el-option>
            </el-select>
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
          <el-table-column prop='name1' label='七分钱订单号' min-width="130"></el-table-column>
          <el-table-column prop='name2' label='交广科技订单号' min-width="180"></el-table-column>
          <el-table-column prop='name3' label='订单名称' min-width="100"></el-table-column>
          <el-table-column prop='name4' label='订单描述' min-width="80"></el-table-column>
          <el-table-column prop='name5' label='付方客户手机号' min-width="120"></el-table-column>
          <el-table-column prop='name6' label='付方客户名称' min-width="100"></el-table-column>
          <el-table-column prop='name7' label='收方客户手机号' min-width="120"></el-table-column>
          <el-table-column prop='name8' label='收方客户名称' min-width="100"></el-table-column>
          <el-table-column prop='name9' label='订单开始时间' min-width="100"></el-table-column>
          <el-table-column prop='name10' label='订单金额' min-width="90"></el-table-column>
          <el-table-column prop='name11' label='订单状态' min-width="80"></el-table-column>
          <el-table-column prop='name12' label='账期' min-width="90"></el-table-column>

          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editorClick(scope.row)">查看详情</el-button>
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
    <alert-model v-show="display"  @on-submit="editorModelSubmit" @on-cancel="editorModelCancel" title="测试">
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
  name1: '2018011711314100300000002',
  name2: 'TBTRANSFER0072018011711314225200012331',
  name3: '转帐到七分钱',
  name4: '转帐',
  name5: '15989791023',
  name6: '邱测试',
  name7: '13691836913',
  name8: '测2',
  name9: '2018-01-18 01:31:42',
  name10: '5.00',
  name11: '成功',
  name12: '20180117'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      display: false,
      editorModelData:{},
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