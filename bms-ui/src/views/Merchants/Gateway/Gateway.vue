<template>
  <!-- 商户管理 > 商户网关维护 -->
  <div class="merchants-gateway-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="130px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户编号" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="商户名称" prop="name2">
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
          <el-table-column prop='name1' label='商户编号' min-width="120"></el-table-column>
          <el-table-column prop='name2' label='商家名称' min-width="130"></el-table-column>
          <el-table-column prop='name3' label='交易类型' width="80"></el-table-column>
          <el-table-column prop='name4' label='支持信用卡' width="90"></el-table-column>
          <el-table-column prop='name5' label='绑定的商户端IP' width="120"></el-table-column>
          <el-table-column prop='name6' label='证书路径' min-width="100"></el-table-column>
          <el-table-column prop='name7' label='备注' min-width="100"></el-table-column>
          <el-table-column prop='name8' label='创建人' width="90"></el-table-column>
          <el-table-column prop='name9' label='创建时间' width="160"></el-table-column>
          <el-table-column prop='name10' label='修改人' width="90"></el-table-column>
          <el-table-column prop='name11' label='修改时间' width="160"></el-table-column>

          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
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

    <!-- 添加商户配置 -->
    <el-dialog title="添加商户配置" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="150px" :show-message="false">
        <el-form-item prop="name1" label="商户" required>
          <el-col>
            <el-autocomplete v-model="addModelData.name1" :fetch-suggestions="querySearch" placeholder="请输入内容" @select="handleSelect">
            </el-autocomplete>
          </el-col>
        </el-form-item>
        <el-form-item prop="name2" label="交易类型" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="支持信用卡" required>
          <el-select v-model="examine.name3" placeholder="请选择">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name4" label="绑定商户端IP" required>
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="存放路径" required>
          <el-input v-model="addModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="商户证书公钥" required>
          <el-input type="textarea" v-model="addModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="备注">
          <el-input type="textarea" v-model="addModelData.name6"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 提现控制修改 -->
    <el-dialog title="交易控制" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" class="alert-model-form" label-width="150px" :show-message="false">
        <el-form-item prop="name1" label="商户" required>
          <el-col>
            <el-autocomplete v-model="editorModelData.name1" :fetch-suggestions="querySearch" placeholder="请输入内容" @select="handleSelect">
            </el-autocomplete>
          </el-col>
        </el-form-item>
        <el-form-item prop="name2" label="交易类型" required>
          <el-input v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="支持信用卡" required>
          <el-select v-model="examine.name3" placeholder="请选择">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name4" label="绑定商户端IP" required>
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="存放路径" required>
          <el-input v-model="editorModelData.name5"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="商户证书公钥" required>
          <el-input type="textarea" v-model="editorModelData.name6"></el-input>
        </el-form-item>
        <el-form-item prop="name6" label="备注">
          <el-input type="textarea" v-model="editorModelData.name6"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editorModelCancel">取 消</el-button>
        <el-button type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 任务删除 -->
    <el-dialog title="删除任务" :visible.sync="deleteDisplay" width="640px">
      <div class="dialog-deleted-content">您确定要删除该商户<span>[ae6495a9bd1c4eacaa1105bf5af4b5be]</span>网关维护么吗？</div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="deleteModelCancel">取 消</el-button>
        <el-button type="primary" @click="deleteModelSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { preview } from '@/tools/utils';
import merchant from '@/tools/merchant';

const testData = {
  name1: 'C2019032717235300011',
  name2: '河北恒海网络科技有限公司',
  name3: '10001',
  name4: 'Y',
  name5: '10.0.0.1',
  name6: '',
  name7: '',
  name8: '',
  name9: '2019-03-29 04:48:23',
  name10: '管理员',
  name11: '2019-03-29 04:48:23'
};

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {
        name3: null
      },
      restaurants: [],
      editorDisplay: false,
      editorModelData: {},
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
      window.console.log(v);
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
      window.console.log(this.addModelData);
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    // 商户候选操作
    querySearch(queryString, cb) {
      var restaurants = this.getOptions();
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      cb(results);
    },
    createFilter(queryString) {
      return restaurant =>
        restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0;
    },
    getOptions() {
      return merchant;
    },
    // 选中候选中的项
    handleSelect(item) {
      window.console.log(item, 'item');
    },
    editorClick(row) {
      this.editorDisplay = true;
      this.isLook = false;
      this.editorModelData = row;
      window.console.log(row);
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit() {
      window.console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    editorUploadChange(res) {
      const { name, raw } = res;
      const src = preview(raw);
      this.editorUploadFileName = name;
      this.editorUploadImageSrc = src;
      this.editorModelData.name3 = raw;
    },
    editorChooseFile() {
      // 上传之前清除文件
      this.$refs.editorUpload.clearFiles();
    },
    deleteModelSubmit() {
      this.deleteDisplay = false;
    },
    deleteModelCancel(row) {
      this.deleteDisplay = false;
      window.console.log(row);
    },
    deleteClick(row) {
      this.deleteDisplay = true;
      window.console.log(row);
    },
    goToSearch() {
      //查询
      window.console.log(this.examine, '查询');
    },
    insertItem() {
      // 新增
      this.addDisplay = true;
    }
  }
};
</script>

<style lang='scss'>
.merchants-gateway-page {
  .el-autocomplete {
    width: 100%;
  }
}
</style>