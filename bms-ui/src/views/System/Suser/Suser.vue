<template>
  <!-- 系统管理 => 用户管理 -->
  <div>
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="80px" :inline="true" ref="controlQueryForm">
          <el-form-item label="真实姓名" prop="name1">
            <el-input v-model="examine.name1" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="部门名称" prop="name2">
            <el-select v-model="examine.name2" placeholder="请选择部门">
              <el-option value='1' label='(GYZB)-国银证保'></el-option>
              <el-option value='2' label='(TECH)-信息技术部'></el-option>
              <el-option value='3' label='(OPERATION)-运营管理部'></el-option>
              <el-option value='4' label='(BJQFQ)-北京七分钱'></el-option>
              <el-option value='5' label='(FIN)-财务部'></el-option>
              <el-option value='6' label='(RM)-风险管理部'></el-option>
              <el-option value='7' label='(KEFU)-客服'></el-option>
              <el-option value='8' label='(PROD)-产品'></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="name3">
            <el-select v-model="examine.name3" placeholder="请选择用户状态">
              <el-option label="(VALID)-生效" value="VALID"></el-option>
              <el-option label="(FREEZE)-冻结" value="FREEZE"></el-option>
              <el-option label="(LEAVE)-离职" value="LEAVE"></el-option>
              <el-option label="(LOGIN)-已登陆" value="LOGIN"></el-option>
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
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="name1" min-width="50" label="编号"></el-table-column>
          <el-table-column prop="name2" min-width="120" label="员工编号"></el-table-column>
          <el-table-column prop="name3" label="姓名" min-width="120"></el-table-column>
          <el-table-column prop="name4" min-width="150" label="部门名称"></el-table-column>
          <el-table-column prop="name5" min-width="50" label="性别"></el-table-column>
          <el-table-column prop="name6" min-width="220" label="个人电话"></el-table-column>
          <el-table-column prop="name7" min-width="100" label="状态"></el-table-column>
          <el-table-column prop="name8" min-width="120" label="创建人"></el-table-column>
          <el-table-column prop="name9" label="创建时间" min-width="220"></el-table-column>
          <el-table-column prop="name10" label="更新人" min-width="150"></el-table-column>
          <el-table-column prop="name11" label="更新时间" min-width="160"></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
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

    <!-- 用户新增 -->
    <el-dialog title="用户新增" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="80px" :show-message="false">
        <el-form-item prop="name1" label="员工编号" required>
          <el-input v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="密码" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="用户名" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="真实姓名" required>
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="昵称">
          <el-input v-model="addModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="性别" required>
          <el-select v-model="addModelData.name6" placeholder="选择">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name7" label="所属部门" required>
          <el-select v-model="addModelData.name7" placeholder="请选择部门">
            <el-option value='1' label='(GYZB)-国银证保'></el-option>
            <el-option value='2' label='(TECH)-信息技术部'></el-option>
            <el-option value='3' label='(OPERATION)-运营管理部'></el-option>
            <el-option value='4' label='(BJQFQ)-北京七分钱'></el-option>
            <el-option value='5' label='(FIN)-财务部'></el-option>
            <el-option value='6' label='(RM)-风险管理部'></el-option>
            <el-option value='7' label='(KEFU)-客服'></el-option>
            <el-option value='8' label='(PROD)-产品'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name8" label="角色" required>
          <el-select v-model="addModelData.name8" multiple placeholder="请选择">
            <el-option v-for="item in roleModelData" :key="item.value" :label="item.name2" :value="item.name1">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name9" label="办公电话">
          <el-input v-model="addModelData.name9"></el-input>
        </el-form-item>
        <el-form-item prop="name10" label="个人电话">
          <el-input v-model="addModelData.name10"></el-input>
        </el-form-item>
        <el-form-item prop="name11" label="办公邮箱">
          <el-input v-model="addModelData.name11"></el-input>
        </el-form-item>
        <el-form-item prop="name12" label="个人邮箱">
          <el-input v-model="addModelData.name12"></el-input>
        </el-form-item>
        <el-form-item prop="name13" label="备注">
          <el-input type="textarea" v-model="addModelData.name13"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改model -->
    <el-dialog title="用户修改" :visible.sync="editorDisplay" width="600px">
      <el-form ref="alertModelForm" :model="editorModelData" class="alert-model-form" label-width="80px" :show-message="false">
        <el-form-item prop="name1" label="编号" required>
          <el-input disabled v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="员工编号" required>
          <el-input disabled v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="用户名" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="密码" required>
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="真实姓名" required>
          <el-input v-model="editorModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="昵称">
          <el-input v-model="editorModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name7" label="性别" required>
          <el-select v-model="editorModelData.name7" placeholder="选择">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name8" label="状态" required>
          <el-select v-model="editorModelData.name8" placeholder="请选择用户状态">
            <el-option label="(VALID)-生效" value="VALID"></el-option>
            <el-option label="(FREEZE)-冻结" value="FREEZE"></el-option>
            <el-option label="(LEAVE)-离职" value="LEAVE"></el-option>
            <el-option label="(LOGIN)-已登陆" value="LOGIN"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name9" label="所属部门" required>
          <el-select v-model="editorModelData.name9" placeholder="请选择部门">
            <el-option label="(GYZB)-国银证保" value="1"></el-option>
            <el-option label="(TECH)-信息技术部" value="2"></el-option>
            <el-option label="(OPER)-运营管理部" value="4"></el-option>
            <el-option label="(FINA)-财务部" value="5"></el-option>
            <el-option label="(CPB)-产品部" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name10" label="角色" required>
          <el-select v-model="editorModelData.name10" multiple placeholder="请选择">
            <el-option v-for="item in roleModelData" :key="item.value" :label="item.name2" :value="item.name1">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name11" label="办公电话">
          <el-input v-model="editorModelData.name11"></el-input>
        </el-form-item>
        <el-form-item prop="name12" label="个人电话">
          <el-input v-model="editorModelData.name12"></el-input>
        </el-form-item>
        <el-form-item prop="name13" label="办公邮箱">
          <el-input v-model="editorModelData.name13"></el-input>
        </el-form-item>
        <el-form-item prop="name14" label="个人邮箱">
          <el-input v-model="editorModelData.name14"></el-input>
        </el-form-item>
        <el-form-item prop="name15" label="备注">
          <el-input type="textarea" v-model="editorModelData.name15"></el-input>
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
  name2: 'GYZB-0000',
  name3: '王小虎',
  name4: '国银证宝',
  name5: '男',
  name6: '012987654321',
  name7: '已登录',
  name8: '创建人',
  name9: '2016-05-02',
  name10: '管理员',
  name11: '2019-12-04 03:53:03'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {}, //查询字段
      addDisplay: false, //新增
      addModelData: {}, //新增表单数据
      editorDisplay: false, //编辑
      editorModelData: {}, //编辑数据
      roleModelData: [
        //角色选择
        { name1: 1, name2: '超级管理员' },
        { name1: 2, name2: '清结算组' },
        { name1: 3, name2: '产品组' },
        { name1: 5, name2: '客服' },
        { name1: 6, name2: '财务部' },
        { name1: 7, name2: '短信管理员' },
        { name1: 8, name2: '市场部' },
        { name1: 9, name2: '空权限' },
        { name1: 10, name2: '管理员' },
        { name1: 11, name2: '数据查询全权限' },
        { name1: 12, name2: '风控' },
        { name1: 13, name2: '技术部' },
        { name1: 14, name2: '录单' },
        { name1: 15, name2: 'OM' }
      ],
      tableData: new Array(10).fill(testData), //表单数据
      paginationOps: {
        //分页
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
    // 控制编辑
    editorModelSubmit() {
      window.console.log('提交', this.editorModelData);
    },
    editorModelCancel() {
      this.resetFormFields('alertModelForm');
      this.editorDisplay = false;
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.editorModelData = row;
      window.console.log(row);
    },
    // 新增
    insertItem() {
      this.addDisplay = true;
    },
    addModelSubmit() {
      window.console.log(this.addModelData);
      this.$refs['alertAddModelForm'].validate((files, object) => {
        if (files) {
          // 验证通过 发送请求添加数据到数据库
          this.addDisplay = false;
        } else {
          const keys = Object.keys(object);
          this.$message.error(`${keys[0]}不可为空`);
        }
      });
    },
    addModelCancel() {
      this.resetFormFields('alertAddModelForm');
      this.addDisplay = false;
    },
    // 查询
    goToSearch() {
      window.console.log(this.examine, '查询');
    }
  }
};
</script>

<style lang='scss' scoped>
.control-query {
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
  .el-form {
    width: 100%;
    display: flex;
    justify-content: space-around;
  }

  .control-query-ops {
    border-bottom: 1px solid #eee;
  }
  .control-query-btns {
    margin-top: 10px;
    .el-button {
      margin-right: 50px;
    }
    text-align: center;
  }
}

.table-inner {
  height: 100%;
  overflow: auto;
  padding: 10px;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
}
.page-model-pagination {
  padding: 10px 0;
}
</style>