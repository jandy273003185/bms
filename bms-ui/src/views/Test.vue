<!-- 注释 -->
<template>
  <div class="home-page">
    <div class="home-page-content">
      <el-table :data="user" border width="100%">
        <!-- 展示数据 -->
        <el-table-column label="序号" type="index" width="50"></el-table-column>
        <el-table-column v-for="(item,key) in mapUserInitData()" :key="key+'1'" :prop="key" :label="item.name" :width="item.width"></el-table-column>
        <el-table-column label="操作" width="120" fixed="right">
          <template slot-scope="scope">
            <el-button type="text" @click="reset(scope.row,scope.$index)">编辑</el-button>
            <el-button type="text" @click="remove(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 编辑页面 -->
    <div class="compile-form-wrap" v-show="compileFormShow">
      <el-form :model="compileFormDate" class="compile-form" label-width="50px">
        <el-form-item v-for="(item,key) in mapUserInitData()" :key="key" :prop="key" :label="item.name">
          <el-input v-model="compileFormDate[key]"></el-input>
        </el-form-item>
        <el-form-item class="compile-btns">
          <el-button size="small" type="success" @click="compileSave">保存</el-button>
          <el-button size="small" @click="compileCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="[5, 10, 15, 20]" :total="100" @current-change="handleCurrentChange" @size-change="handleSizeChange">
    </el-pagination>
  </div>
</template>

<script>
const user_init_data = {
  date: { name: '时间', value: '2016-05-02', width: '140' },
  name: { name: '姓名', value: '王小虎', width: '120' },
  province: { name: '地区', value: '上海', width: '120' },
  city: { name: '市区', value: '普陀区', width: '120' },
  address: { name: '地址', value: '上海市普陀区金沙江路1518弄' },
  zip: { name: '编号', value: 200333, width: '120' }
};
const info = {
  date: '2016-05-02',
  name: '王小一',
  province: '上海',
  city: '普陀区',
  address: '上海市普陀区金沙江路 1518 弄',
  zip: 200333
};
export default {
  components: {},
  data() {
    return {
      index: 0,
      compileFormShow: false,
      user: new Array(10).fill(info),
      data: [],
      compileFormDate: {}
    };
  },
  methods: {
    mapUserInitData() {
      return user_init_data;
    },
    reset(row) {
      console.log('编辑');
      // this.compileFormDate = Object.assign({},this.user[index]);
      // console.log(this.user[index] === row); //true
      this.compileFormDate = Object.assign({}, row);
      this.compileFormShow = true;
    },
    remove(index) {
      console.log('删除');
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // 确定时
          this.user.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })
        .catch(() => {
          // 取消时
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    compileSave() {
      console.log('save');
      this.$message({ type: 'success', message: '修改成功' });
      this.user.splice(this.index, 1, this.compileFormDate);
      this.compileFormShow = false;
    },
    compileCancel() {
      console.log('cancel');
      this.compileFormShow = false;
    },
    handleCurrentChange() {
      console.log(arguments[0], 'handleCurrentChange');
    },
    handleSizeChange() {
      console.log(arguments[0], 'handleSizeChange');
    }
  },
  created() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      province: '上海',
      city: '普陀区',
      address: '上海市普陀区金沙江路 1518 弄',
      zip: 200333
    };
    const data = new Array(10).fill(item);
    this.data = data;
  }
};
</script>
<style lang='scss' scoped>
//@import url();
.home-page {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.home-page-content {
  flex: 1;
  overflow: auto;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
  // white-space:nowrap;//针对（注意，此时子元素需要设置display:inline-block）
  // overflow-y: scroll;
  // -ms-overflow-style: none; //IE 10+
  // overflow: -moz-scrollbars-none; //Firefox
  // &::-webkit-scrollbar {
  //   // &在sass中就表示要修饰的父元素即.pics_wall
  //   display: none; //Safari and Chrome
  // }
}
el-tabel {
  width: 100%;
  td {
    text-align: center;
  }
}
.compile-form-wrap {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.1);
  position: fixed;
  z-index: 100;
  top: 0;
  left: 0;
}
.compile-form {
  width: 50%;
  max-width: 500px;
  position: absolute;
  padding: 20px 20px 0 0;
  top: 50%;
  left: 50%;
  background-color: #fff;
  border-radius: 10px;
  transform: translateX(-50%) translateY(-50%);
}
.compile-btns {
  text-align: center;
}
.el-pagination {
  // position: absolute;
  // bottom: 70px;
  margin-top: 20px;
}
</style>