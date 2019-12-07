<template>
  <!-- 系统管理 => 部门管理 -->
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="部门代码" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="部门名称" prop="name2">
            <el-input v-model="examine.name2"></el-input>
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
          <el-table-column prop='name1' label='部门编号' min-width="50"></el-table-column>
          <el-table-column prop='name2' label='部门名称' min-width="120"></el-table-column>
          <el-table-column prop='name3' label='部门代码' min-width="100"></el-table-column>
          <el-table-column prop='name4' label='部门级别' min-width="80"></el-table-column>
          <el-table-column prop='name5' label='上级部门' min-width="80"></el-table-column>
          <el-table-column prop='name6' label='是否有效' min-width="80"></el-table-column>
          <el-table-column prop='name7' label='创建人' min-width="100"></el-table-column>
          <el-table-column prop='name8' label='创建时间' min-width="140"></el-table-column>
          <el-table-column prop='name9' label='更新人' min-width="100"></el-table-column>
          <el-table-column prop='name10' min-width="140" label='更新时间'></el-table-column>

          <el-table-column fixed="right" label="操作" width="140">
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

    <!-- 部门新增 -->
    <el-dialog title="角色新增" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="100px" :show-message="false">
        <el-form-item prop="name1" label="部门名称" required>
          <el-input v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="部门代码" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="部门级别" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="上级部门" required>
          <el-select v-model="addModelData.name5" placeholder="请选择部门">
            <el-option label="(GYZB)-国银证保" value="1"></el-option>
            <el-option label="(TECH)-信息技术部" value="2"></el-option>
            <el-option label="(OPER)-运营管理部" value="4"></el-option>
            <el-option label="(FINA)-财务部" value="5"></el-option>
            <el-option label="(CPB)-产品部" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name6" label="是否有效" required>
          <el-select v-model="addModelData.name6" placeholder="是否有效">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="部门地址">
          <el-input v-model="addModelData.name7"></el-input>
        </el-form-item>
        <el-form-item prop="name8" label="备注">
          <el-input type="textarea" v-model="addModelData.name8"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改model -->
    <el-dialog title="部门修改" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" class="alert-model-form" label-width="80px">
        <el-form-item prop="name9" label="部门编号" required>
          <el-input v-model="editorModelData.name9"></el-input>
        </el-form-item>
        <el-form-item prop="name1" label="部门名称" required>
          <el-input v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="部门代码" required>
          <el-input v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="部门级别" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="上级部门" required>
          <el-select v-model="editorModelData.name5" placeholder="请选择部门">
            <el-option label="(GYZB)-国银证保" value="1"></el-option>
            <el-option label="(TECH)-信息技术部" value="2"></el-option>
            <el-option label="(OPER)-运营管理部" value="4"></el-option>
            <el-option label="(FINA)-财务部" value="5"></el-option>
            <el-option label="(CPB)-产品部" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name6" label="是否有效" required>
          <el-select v-model="editorModelData.name6" placeholder="是否有效">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="部门地址">
          <el-input v-model="editorModelData.name7"></el-input>
        </el-form-item>
        <el-form-item prop="name8" label="备注">
          <el-input type="textarea" v-model="editorModelData.name8"></el-input>
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
  name1: '1',
  name2: '国银证宝',
  name3: 'GZYB',
  name4: '2',
  name5: '0',
  name6: 'Y',
  name7: '创建人',
  name8: '2015-12-22 6:08:54',
  name9: '管理员',
  name10: '2015-12-22 6:08:54'
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
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit() {
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
    },
    insertItem() {
      // 新增
      this.addDisplay = true;
    }
  }
};
</script>

<style lang='scss' scoped>