<template>
  <!-- 商户管理 > 门店管理 -->
  <div class="merchants-shop-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="70px" :inline="true" ref="controlQueryForm">
          <el-form-item label="商户名称" prop="name1">
            <el-input v-model="examine.name1"></el-input>
          </el-form-item>
          <el-form-item label="商户编号" prop="name2">
            <el-input v-model="examine.name2"></el-input>
          </el-form-item>
          <el-form-item label="门店名称" prop="name3">
            <el-input v-model="examine.name3"></el-input>
          </el-form-item>
          <el-form-item label="门店编号" prop="name4">
            <el-input v-model="examine.name4"></el-input>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="$refs['controlQueryForm'].resetFields()">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="insertItem">添加新门店<i class="el-icon-circle-plus-outline"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border class="tableInner-content">
          <el-table-column prop='name1' label='商户编号' min-width="100"></el-table-column>
          <el-table-column prop='name2' label='商户名称' min-width="80"></el-table-column>
          <el-table-column prop='name3' label='门店编号' min-width="90"></el-table-column>
          <el-table-column prop='name4' label='门店名称' min-width="100"></el-table-column>
          <el-table-column prop='name5' label='门店地址' min-width="100"></el-table-column>
          <el-table-column prop='name6' label='门店联系方式' width="110"></el-table-column>
          <el-table-column prop='name7' label='门店状态' width="80"></el-table-column>

          <el-table-column fixed="right" label="操作" width="180">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editorClick(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="newClick(scope.row)">添加</el-button>
              <el-button type="text" size="small" @click="lookClick(scope.row)">查看收款二维码</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>

    <!-- 添加门店 -->
    <el-dialog title="添加门店" :visible.sync="addDisplay" width="600px">
      <el-form ref="alertAddModelForm" :model="addModelData" class="alert-model-form" label-width="150px" :show-message="false">
        <el-form-item prop="name1" label="商户" required>
          <el-autocomplete class="" v-model="addModelData.name1" :fetch-suggestions="merchantQuerySearch" placeholder="请输入内容" @select="handleSelect">
          </el-autocomplete>
        </el-form-item>
        <el-form-item prop="name2" label="门店名称" required>
          <el-input v-model="addModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="门店地址" required>
          <el-input v-model="addModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="门店联系方式" required>
          <el-input v-model="addModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="状态" required>
          <el-select v-model="addModelData.name5" placeholder="请选择">
            <el-option label="可用" value="1"></el-option>
            <el-option label="不可用" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addModelCancel">取 消</el-button>
        <el-button type="primary" @click="addModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改门店资料 -->
    <el-dialog title="修改门店资料" :visible.sync="editorDisplay" width="600px">
      <el-form :model="editorModelData" class="editor-model-form" label-width="120px" :show-message="false">
        <el-form-item prop="name1" label="商户" required>
          <el-input disabled v-model="editorModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name2" label="门店编号" required>
          <el-input disabled v-model="editorModelData.name2"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="门店名称" required>
          <el-input v-model="editorModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="门店地址" required>
          <el-input v-model="editorModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="门店联系方式" required>
          <el-input type="textarea" v-model="editorModelData.name5"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="name6" required>
          <el-select v-model="editorModelData.name6" placeholder="请选择">
            <el-option label="可用" value="1"></el-option>
            <el-option label="不可用" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editorModelCancel">取 消</el-button>
        <el-button type="primary" @click="editorModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加门店资料 -->
    <el-dialog title="添加门店资料" :visible.sync="newDisplay" width="600px">
      <el-form :model="newModelData" class="editor-model-form" label-width="120px" :show-message="false">
        <el-form-item prop="name1" label="商户" required>
          <el-input disabled v-model="newModelData.name1"></el-input>
        </el-form-item>
        <el-form-item prop="name3" label="门店名称" required>
          <el-input v-model="newModelData.name3"></el-input>
        </el-form-item>
        <el-form-item prop="name4" label="门店地址" required>
          <el-input v-model="newModelData.name4"></el-input>
        </el-form-item>
        <el-form-item prop="name5" label="门店联系方式" required>
          <el-input type="textarea" v-model="newModelData.name5"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="name6" required>
          <el-select v-model="newModelData.name6" placeholder="请选择">
            <el-option label="可用" value="1"></el-option>
            <el-option label="不可用" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="newModelCancel">取 消</el-button>
        <el-button type="primary" @click="newModelSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 收款二维码 -->
    <el-dialog title="收款二维码" :visible.sync="lookDisplay" width="400px" class="alert-qrcode-model">
      <el-radio-group v-model="QrCode">
        <el-radio label="0">七分钱样式</el-radio>
        <el-radio label="1">原始样式</el-radio>
      </el-radio-group>

      <div class="QrCode-img">
        <div class="img"
          style="backgroundImage:url(https://dss1.bdstatic.com/6OF1bjeh1BF3odCf/it/u=829888290,2650878176&fm=74&app=80&f=JPG&size=f121,121?sec=1880279984&t=274501980523d63a7d93c94f85aabbab)"></div>
        <div>二维码收款可打印张贴在店门口</div>
      </div>
      <div class="qrcode-download">
        <el-button type="primary">下载</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const testData = {
  name1: 'M051537134435794',
  name2: '葡萄酒庄',
  name3: 'MD20191223114930',
  name4: '手打奥所',
  name5: 'qqqqqqqqqqqqqq',
  name6: '12345678912',
  name7: '可用'
};
import cashier from '@/tools/cashier';
export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      addDisplay: false,
      addModelData: {},
      restaurants: [],
      editorDisplay: false,
      editorModelData: {},
      newDisplay: false,
      newModelData: {},
      QrCode: '0',
      lookDisplay: false,
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
    // 商户候选操作
    merchantQuerySearch(queryString, cb) {
      var restaurants = cashier;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      cb(results);
    },
    createFilter(queryString) {
      return restaurant =>
        restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0;
    },
    // 选中候选中的项
    handleSelect(item) {
      window.console.log(item, 'item');
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
      window.console.log(this.addModelData);
    },
    addModelCancel() {
      this.addDisplay = false;
      // this.resetFormFields('alertAddModelForm');
    },
    editorClick(row) {
      this.editorDisplay = true;
      // this.editorModelData = row;
      window.console.log(row);
    },
    editorModelCancel() {
      this.editorDisplay = false;
    },
    editorModelSubmit() {
      window.console.log(this.editorModelData);
      this.editorDisplay = false;
    },
    // 添加门店资料
    newClick(row) {
      this.newDisplay = true;
      // this.newModelData = row;
      window.console.log(row);
    },
    newModelCancel() {
      this.newDisplay = false;
    },
    newModelSubmit() {
      window.console.log(this.newModelData);
      this.newDisplay = false;
    },
    lookClick(row) {
      this.lookDisplay = true;
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
.merchants-shop-page {
  .alert-model-form {
    .el-input {
      width: 370px;
    }
  }

  .alert-qrcode-model .QrCode-img {
    .img {
      display: inline-block;
      width: 200px;
      height: 200px;
      background-color: rgb(250, 250, 250);
      background-size: contain;
      background-repeat: no-repeat;
      margin-top: 5px;
    }
    text-align: center;
  }
  .qrcode-download {
    text-align: right;
  }
}
</style>
