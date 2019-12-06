<template>
  <!-- 系统管理 => 任务调度配置 -->
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

          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editorClick(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="deleteClick(scope.row)">删除</el-button>
              <el-button type="text" size="small" @click="performClick(scope.row)">执行</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 任务新增 -->
    <alert-model v-show="addDisplay" title="任务新增" @on-submit="addModelSubmit" @on-cancel="addModelCancel">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="120px">
        <el-form-item prop="name1" label="任务名称" required>
          <el-input v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="执行类路径" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="执行CRON时间" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="执行主机" required>
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="是否开启" required>
          <el-select v-model="addModelData.name5" placeholder="请选择">
            <el-option label="开启" value="1"></el-option>
            <el-option label="停止" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="业务参数">
          <el-input v-model="addModelData.name7"></el-input>
        </el-form-item>
        <el-form-item prop="name8" label="备注">
          <el-input type="textarea" v-model="addModelData.name8"></el-input>
        </el-form-item>
      </el-form>
    </alert-model>

    <!-- 修改model -->
    <alert-model v-show="editorDisplay" @on-submit="editorModelSubmit" @on-cancel="editorModelCancel" title="任务修改">
      <el-form :model="editorModelData" class="alert-model-form" label-width="120px" :show-message="false">
        <el-form-item prop="name1" label="任务名称" required>
          <el-input v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="执行类路径" required>
          <el-input v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="执行CRON时间" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="执行主机" required>
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="是否开启" required>
          <el-select v-model="editorModelData.name5" placeholder="请选择">
            <el-option label="开启" value="1"></el-option>
            <el-option label="停止" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="业务参数">
          <el-input v-model="editorModelData.name7"></el-input>
        </el-form-item>
        <el-form-item prop="name8" label="备注">
          <el-input type="textarea" v-model="editorModelData.name8"></el-input>
        </el-form-item>
      </el-form>
    </alert-model>

    <!-- 任务删除 -->
    <alert-model v-show="deleteDisplay" @on-submit="deleteModelSubmit" @on-cancel="deleteModelCancel" title="任务删除">
      <div class="deleted-model-content">您确定要删除该任务调度配置[8338]吗？</div>
    </alert-model>

    <!-- 任务执行 -->
    <alert-model v-show="performDisplay" @on-submit="performModelSubmit" @on-cancel="performModelCancel" title="任务执行">
      <el-form :model="performModelData" :disabled="true" class="alert-model-form" label-width="80px">
        <el-form-item prop="name1" label="任务名称" required>
          <el-input v-model="performModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="执行类路径" required>
          <el-input v-model="performModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="执行主机" required>
          <el-input v-model="performModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="执行参数" required>
          <el-input v-model="performModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="业务参数" required>
          <el-input v-model="performModelData.name5"></el-input>
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
      addDisplay: false,
      addModelData: {},
      editorDisplay: false,
      editorModelData: {},
      performDisplay: false, //执行
      performModelData: {},
      deleteDisplay: false, //任务删除
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
    addModelSubmit(c) {
      this.$refs['alertAddModelForm'].validate((files, object) => {
        if (files) {
          // 验证通过 发送请求添加数据到数据库
          c(); //执行回调
        } else {
          const keys = Object.keys(object);
          this.$message.error(`${keys[0]}不可为空`);
        }
      });
      console.log(this.addModelData);
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit(c) {
      console.log(this.editorModelData);
      c();
    },
    editorClick(row) {
      this.editorDisplay = true;
      console.log(row);
    },
    performModelCancel() {
      this.performDisplay = false;
    },
    performModelSubmit(c) {
      console.log(this.performModelData);
      c();
    },
    performClick(row) {
      this.performDisplay = true;
      console.log(row);
    },
    deleteModelSubmit(c) {
      this.deleteDisplay = true;
      c();
    },
    deleteModelCancel(row) {
      this.deleteDisplay = false;
      console.log(row);
    },
    deleteClick(row) {
      this.deleteDisplay = true;
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
.deleted-model-content {
  height: 60px;
  line-height: 60px;
  font-size: 13px;
  color: #ff0000;
  font-weight: 700;
  text-align: center;
}
</style>