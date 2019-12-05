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
              <el-option label="(GYZB)-国银证保" value="1"></el-option>
              <el-option label="(TECH)-信息技术部" value="2"></el-option>
              <el-option label="(OPER)-运营管理部" value="4"></el-option>
              <el-option label="(FINA)-财务部" value="5"></el-option>
              <el-option label="(CPB)-产品部" value="6"></el-option>
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
          <el-table-column prop="serial" min-width="50" label="编号"></el-table-column>
          <el-table-column prop="employee" min-width="120" label="员工编号"></el-table-column>
          <el-table-column prop="name" label="姓名" min-width="120"></el-table-column>
          <el-table-column prop="industry" min-width="150" label="部门名称"></el-table-column>
          <el-table-column prop="sex" min-width="50" label="性别"></el-table-column>
          <el-table-column prop="phone" min-width="220" label="个人电话"></el-table-column>
          <el-table-column prop="status" min-width="100" label="状态"></el-table-column>
          <el-table-column prop="creator" min-width="120" label="创建人"></el-table-column>
          <el-table-column prop="date" label="创建时间" min-width="220"></el-table-column>
          <el-table-column prop="regenerator" label="更新人" min-width="150"></el-table-column>
          <el-table-column prop="turnovertime" label="更新时间" min-width="160"></el-table-column>
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
    <alert-model v-show="addDisplay" title="用户新增" @on-submit="addModelSubmit" @on-cancel="addModelCancel">
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
            <el-option label="(GYZB)-国银证保" value="1"></el-option>
            <el-option label="(TECH)-信息技术部" value="2"></el-option>
            <el-option label="(OPER)-运营管理部" value="4"></el-option>
            <el-option label="(FINA)-财务部" value="5"></el-option>
            <el-option label="(CPB)-产品部" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name8" label="角色" required>
          <div class="user-checked">
            <el-input v-model="addModelData.name8"></el-input>
            <el-button type="warning" @click="editorRoleClick">角色选择</el-button>
          </div>
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
    </alert-model>

    <!-- 修改model -->
    <alert-model v-show="display" title="用户修改" @on-submit="modelSubmit" @on-cancel="modelCancel">
      <el-form ref="alertModelForm" :model="addModelData" class="alert-model-form" label-width="80px" :show-message="false">
        <el-form-item prop="name1" label="编号" required>
          <el-input disabled v-model="addModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="员工编号" required>
          <el-input disabled v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="用户名" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="密码" required>
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="真是姓名" required>
          <el-input v-model="addModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="昵称">
          <el-input v-model="addModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name7" label="性别" required>
          <el-select v-model="addModelData.name7" placeholder="选择">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name8" label="状态" required>
          <el-select v-model="addModelData.name8" placeholder="请选择用户状态">
            <el-option label="(VALID)-生效" value="VALID"></el-option>
            <el-option label="(FREEZE)-冻结" value="FREEZE"></el-option>
            <el-option label="(LEAVE)-离职" value="LEAVE"></el-option>
            <el-option label="(LOGIN)-已登陆" value="LOGIN"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name9" label="所属部门" required>
          <el-select v-model="addModelData.name9" placeholder="请选择部门">
            <el-option label="(GYZB)-国银证保" value="1"></el-option>
            <el-option label="(TECH)-信息技术部" value="2"></el-option>
            <el-option label="(OPER)-运营管理部" value="4"></el-option>
            <el-option label="(FINA)-财务部" value="5"></el-option>
            <el-option label="(CPB)-产品部" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name10" label="角色" required>
          <div class="user-checked">
            <el-input v-model="addModelData.name10"></el-input>
            <el-button type="warning" @click="editorRoleClick">角色选择</el-button>
          </div>
        </el-form-item>
        <el-form-item prop="name11" label="办公电话">
          <el-input v-model="addModelData.name11"></el-input>
        </el-form-item>
        <el-form-item prop="name12" label="个人电话">
          <el-input v-model="addModelData.name12"></el-input>
        </el-form-item>
        <el-form-item prop="name13" label="办公邮箱">
          <el-input v-model="addModelData.name13"></el-input>
        </el-form-item>
        <el-form-item prop="name14" label="个人邮箱">
          <el-input v-model="addModelData.name14"></el-input>
        </el-form-item>
        <el-form-item prop="name15" label="备注">
          <el-input type="textarea" v-model="addModelData.name15"></el-input>
        </el-form-item>
      </el-form>
    </alert-model>

    <!-- 角色选择 -->
    <alert-model v-show="roleDisplay" title="角色选择" @on-submit="roleModelSubmit" @on-cancel="roleModelCancel">
      <el-table ref="roleTableForm" border :data="roleModelData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <!-- <el-table-column prop="name1" label="角色编号">
        </el-table-column>
        <el-table-column prop="name2" label="角色名称">
        </el-table-column> -->

        <el-table-column v-for="(item,index) in roleModelData[0]" :key="index" :label="{name1:'角色编号',name2:'角色名称'}[index]" :prop="index"></el-table-column>
      </el-table>
    </alert-model>
  </div>
</template>

<script>
const testData = {
  serial: '1',
  employee: 'GYZB-0000',
  name: '王小虎',
  industry: '国银证宝',
  sex: '男',
  phone: '012987654321',
  status: '已登录',
  creator: '创建人',
  date: '2016-05-02',
  regenerator: '管理员',
  turnovertime: '2019-12-04 03:53:03'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {}, //查询字段
      addDisplay: false, //新增
      display: false, //编辑
      roleDisplay: false, //角色选择
      modelData: {}, //编辑数据
      addModelData: {}, //新增表单数据
      multipleSelection: [], //角色选择checkbox数据
      roleModelData: [
        //角色选择
        { name1: '1', name2: '管理员', checked: false },
        { name1: '3', name2: '清结算组', checked: true },
        { name1: '4', name2: '产品组' },
        { name1: '5', name2: '测试人员' },
        { name1: '99', name2: 'Default' },
        { name1: '100', name2: 'test1' },
        { name1: '102', name2: '数据查询全权限' },
        { name1: '103', name2: '测试' },
        { name1: '104', name2: '市场部总裁' },
        { name1: '105', name2: '市场部副总裁' },
        { name1: '107', name2: '交易管理部分' }
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
    searchText(v, o) {
      if (!v || v === o) return;
      console.log(v);
    }
  },
  created() {},
  methods: {
    // 控制编辑
    modelSubmit(callback) {
      console.log('提交', this.modelData);
      callback();
    },
    modelCancel() {
      this.resetFormFields('alertModelForm');
      this.display = false;
    },
    editorClick(row) {
      this.display = true;
      console.log(row);
    },
    // 角色选择
    roleModelSubmit(callback) {
      console.log('角色选择提交', this.multipleSelection);
      callback();
    },
    roleModelCancel() {
      this.roleDisplay = false;
    },
    editorRoleClick() {
      this.roleDisplay = true;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 新增
    insertItem() {
      console.log('新增');
      this.addDisplay = true;
    },
    addModelSubmit(callback) {
      this.$refs['alertAddModelForm'].validate((files, object) => {
        if (files) {
          callback();
        } else {
          const keys = Object.keys(object);
          // console.log(keys);
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
      console.log(this.examine, '查询');
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

.user-checked {
  display: flex;
  .el-button {
    margin-left: 10px;
  }
}
</style>