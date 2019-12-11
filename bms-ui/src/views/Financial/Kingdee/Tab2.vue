<template>
  <!-- 金蝶 > 金蝶待付数据信息 -->
  <div class="finacial-kingdee-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="70px" :inline="true" ref="controlQueryForm1">
          <el-form-item label="编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="科目编号" prop="name3">
            <el-input v-model="examine.name3"></el-input>
          </el-form-item>
          <el-form-item label="账户名称" prop="name4">
            <el-input v-model="examine.name4"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="name5">
            <el-select v-model="examine.name5" placeholder="请选择">
              <el-option label="生效" value="1"></el-option>
              <el-option label="无效" value="0"></el-option>
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
          <el-table-column prop='name1' label='清算编号'></el-table-column>
          <el-table-column prop='name2' label='业务类型'></el-table-column>
          <el-table-column prop='name3' label='业务编号'></el-table-column>
          <el-table-column prop='name4' label='业务日期'></el-table-column>
          <el-table-column prop='name5' label='是否期初单据'></el-table-column>
          <el-table-column prop='name6' label='写入日期'></el-table-column>
          <el-table-column prop='name7' label='交易提交状态'></el-table-column>
          <el-table-column prop='name8' label='返回信息'></el-table-column>
          <el-table-column prop='name9' label='对账状态'></el-table-column>
          <el-table-column prop='name10' label='银行处理状态'></el-table-column>
          <el-table-column fixed="right" label="操作" width="60">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="editorClick(scope.row)">修改</el-button>
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
    <el-dialog title="新增任务" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="130px" :show-message="false">
        <el-form-item prop="name1" label="账号" required>
          <el-input v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="科目编号" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="账户名称" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="币种">
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="所属银行(总行)">
          <el-input v-model="addModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="所属开户行联行号">
          <el-input v-model="addModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name7" label="状态">
          <el-select v-model="addModelData.name7" placeholder="请选择">
            <el-option label="无效" value="1"></el-option>
            <el-option label="生效" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改model -->
    <el-dialog title="任务修改" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" class="alert-model-form" label-width="130px" :show-message="false">
        <el-form-item prop="name1" label="账号" required>
          <el-input v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="科目编号" required>
          <el-input v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="账户名称" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="币种">
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="所属银行(总行)">
          <el-input v-model="editorModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="所属开户行联行号">
          <el-input v-model="editorModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name7" label="状态">
          <el-select v-model="editorModelData.name7" placeholder="请选择">
            <el-option label="无效" value="1"></el-option>
            <el-option label="生效" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editorModelCancel">取 消</el-button>
        <el-button type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: '2042018091917254333900076681',
  name2: '商户结算',
  name3: '2018091910183906124400000390',
  name4: '2018-09-19',
  name5: 'false',
  name6: '2018-09-19',
  name7: '',
  name8: '',
  name9: '初始化',
  name10: '银行交易成功'
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
    insertItem() {
      // 新增
      this.addDisplay = true;
    },
    addModelSubmit() {
      this.$refs['alertAddModelForm'].validate((files, object) => {
        if (files) {
          // 验证通过 发送请求添加数据到数据库
          this.addDisplay = false;
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
    editorModelSubmit() {
      console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.editorModelData = row;
      console.log(row);
    },
    goToSearch() {
      //查询
      console.log(this.examine, '查询');
    }
  }
};
</script>

<style lang='scss'>
.finacial-kingdee-page {
  .look-query-form {
    .el-form-item {
      width: 50%;
      margin-right: 0;
    }
  }

  .perform-btn {
    margin-left: 0;
  }
}
</style>