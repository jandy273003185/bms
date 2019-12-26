<template>
  <!-- 商户管理 > 服务商列表 -->
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="86px" :inline="true" ref="controlQueryForm">
          <el-form-item label="服务商编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="服务商名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>

          <el-form-item label="时间" prop="name7">
            <el-date-picker v-model="examine.name7" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
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
          <el-table-column prop='name1' label='服务商编号' min-width="100"></el-table-column>
          <el-table-column prop='name2' label='服务商名称' min-width="90"></el-table-column>
          <el-table-column prop='name3' label='服务商类型' width="100"></el-table-column>
          <el-table-column prop='name4' label='联系人姓名' width="90"></el-table-column>
          <el-table-column prop='name5' label='联系人电话' width="120"></el-table-column>
          <el-table-column prop='name6' label='推荐人' width="80"></el-table-column>
          <el-table-column prop='name7' label='服务商级别' width="90"></el-table-column>
          <el-table-column prop='name8' label='状态' width="90"></el-table-column>
          <el-table-column prop='name9' label='创建时间' width="160"></el-table-column>

          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookClick(scope.row)">预览</el-button>
              <el-button type="text" size="small" @click="editorClick(scope.row)">修改</el-button>
              <el-button type="text" size="small" @click="performClick(scope.row)">审核</el-button>
            </template>
          </el-table-column>
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
  name1: 'M918733120623011',
  name2: '海俊集团20',
  name3: '个体户',
  name4: '',
  name5: '12345678911',
  name6: '',
  name7: '战略服务商',
  name8: '待审核',
  name9: '2019-12-19 16:05:08'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      performDisplay: false, //执行
      performModelData: {},
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
      window.console.log(v);
    }
  },
  created() {},
  methods: {
    goToSearch() {
      //查询
      window.console.log(this.examine, '查询');
    },
    // 新增1 + 预览0 + 商户更新2 + 商户审核3
    // 新增
    insertItem() {
      this.$router.push('/merchants/service/operation/1');
    },
    // 审核
    performClick(row) {
      this.$router.push('/merchants/service/operation/3');
      window.console.log(row);
    },
    // 查看
    lookClick(row) {
      window.console.log(row);
      this.$router.push('/merchants/service/operation/0');
    },
    // 更新
    editorClick(row) {
      window.console.log(row);
      this.$router.push('/merchants/service/operation/2');
    }
  }
};
</script>

<style lang='scss'></style>