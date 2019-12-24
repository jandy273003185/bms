<template>
  <!-- 商户管理 > 费率管理 -->
  <div class="merchants-rate-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="130px" :inline="true" ref="controlQueryForm">
          <el-form-item label="费率编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>

          <el-form-item label="费率类型" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="固定费率" value="1"></el-option>
              <el-option label="套餐费率" value="2"></el-option>
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
        <!-- 费率编号 费率名称 费率类型 费率详情 费率值 费率描述 创建人 状态 -->
        <el-table :data="tableData" border class="tableInner-content">
          <el-table-column prop='name1' label='费率编号' width="80"></el-table-column>
          <el-table-column prop='name2' label='费率名称' width="120"></el-table-column>
          <el-table-column prop='name3' label='费率类型' width="90"></el-table-column>
          <el-table-column prop='name4' label='费率详情' width="100"></el-table-column>
          <el-table-column prop='name5' label='费率值' width="70"></el-table-column>
          <el-table-column prop='name6' label='费率描述' min-width="180"></el-table-column>
          <el-table-column prop='name7' label='创建人' width="70"></el-table-column>
          <el-table-column prop='name8' label='状态' width="60"></el-table-column>

          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookClick(scope.row)">查看</el-button>
              <el-button type="text" size="small" @click="editorClick(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="deleteClick(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 费率新增 -->
    <el-dialog title="费率新增" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="150px" :show-message="false">
        <el-form-item prop="name1" label="费率名称">
          <el-input v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="费率类型">
          <el-select v-model="examine.name3" placeholder="请选择">
            <el-option label="套餐费率" value="1"></el-option>
            <el-option label="固定费率" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name4" label="费率区间1" class="add-form-rate">
          <el-input v-model="addModelData.name4.name1"></el-input>-
          <el-input v-model="addModelData.name4.name2"></el-input><i>费率值：</i>
          <el-input v-model="addModelData.name4.name3"></el-input>%
        </el-form-item>

        <el-form-item prop="name5" label="费率区间2" class="add-form-rate">
          <el-input v-model="addModelData.name5.name1"></el-input>-
          <el-input v-model="addModelData.name5.name2"></el-input><i>费率值：</i>
          <el-input v-model="addModelData.name5.name3"></el-input>%
        </el-form-item>

        <el-form-item prop="name6" label="费率区间3" class="add-form-rate">
          <el-input v-model="addModelData.name6.name1"></el-input>-
          <el-input v-model="addModelData.name6.name2"></el-input><i>费率值：</i>
          <el-input v-model="addModelData.name6.name3"></el-input>%
        </el-form-item>

        <el-form-item prop="name7" label="费率区间4" class="add-form-rate">
          <el-input v-model="addModelData.name7.name1"></el-input>-
          <el-input v-model="addModelData.name7.name2"></el-input><i>费率值：</i>
          <el-input v-model="addModelData.name7.name3"></el-input>%
        </el-form-item>

        <el-form-item prop="name8" label="费率描述">
          <el-input type="textarea" v-model="addModelData.name8"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 协议修改 -->
    <el-dialog title="协议修改" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" :disabled="isLook" class="editor-model-form" label-width="100px" :show-message="false">
        <el-form-item prop="name1" label="费率编号">
          <el-input disabled v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="费率名称">
          <el-input disabled v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item label="费率类型" prop="name4">
          <el-select disabled v-model="editorModelData.name4" placeholder="请选择">
            <el-option label="固定费率" value="1"></el-option>
            <el-option label="套餐费率" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name5" label="费率值" class="add-form-rate">
          <el-input v-model="editorModelData.name5"></el-input>%
        </el-form-item>
        <el-form-item prop="name6" label="费率描述">
          <el-input type="textarea" v-model="editorModelData.name6"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer" v-show="!isLook">
        <el-button @click="editorModelCancel">取 消</el-button>
        <el-button type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 任务删除 -->
    <el-dialog title="删除任务" :visible.sync="deleteDisplay" width="640px">
      <div class="dialog-deleted-content">您确定要删除该费率<span>[F0001]</span>么？</div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="deleteModelCancel">取 消</el-button>
        <el-button type="primary" @click="deleteModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: 'F0002',
  name2: '七分钱固定费率',
  name3: '固定费率',
  name4: 'Fixed Rule',
  name5: '0.6',
  name6: '七分钱固定费率适用于一般商户（未细分行业）',
  name7: '周红',
  name8: '有效'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {
        name4: {},
        name5: {},
        name6: {},
        name7: {}
      },
      restaurants: [],
      editorDisplay: false,
      editorModelData: {},
      isLook: false,
      deleteDisplay: false,
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
    editorClick(row) {
      this.editorDisplay = true;
      this.isLook = false;
      // this.editorModelData = row;
      console.log(row);
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit() {
      console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    deleteModelSubmit() {
      this.deleteDisplay = false;
    },
    lookClick() {
      this.editorDisplay = true;
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

<style lang='scss'>
.merchants-rate-page {
  .add-form-rate {
    .el-form-item__content {
      .el-input {
        margin: 0 4px;
        &:first-child {
          margin-left: 0;
        }
      }

      display: flex;
      i {
        display: inline-block;
        width: 230px;
      }
    }
  }
}
</style>