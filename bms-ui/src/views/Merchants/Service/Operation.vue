<template>
  <!-- 商户列表操作 (新增1 + 预览0 + 商户更新2 + 商户审核3) 不同字段，对应不同状态-->
  <div class='merchanrts-list-operation-page'>
    <h3>服务商信息</h3>
    <el-form :model="merchanrtsInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :show-message="false">
      <table class="info-item-table" border="1">
        <tr>
          <td colspan="3">
            <el-form-item label="服务商账号" prop="name1" required>
              <el-input v-model="merchanrtsInfo.name1" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </td>
        </tr>
      </table>
    </el-form>

    <h3>基本信息</h3>
    <el-form :model="basicInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :show-message="false">
      <table class="info-item-table" border="1">
        <tr>
          <td>
            <el-form-item label="服务商类型" prop="name2" required>
              <el-select v-model="basicInfo.name2" placeholder="请选择">
                <el-option label="企业" value="1"></el-option>
                <el-option label="小微商户" value="2"></el-option>
                <el-option label="个体户" value="3"></el-option>
              </el-select>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="所属业务人员" prop="name4" required>
              <el-autocomplete v-model="basicInfo.name1" :fetch-suggestions="merchantQuerySearch" placeholder="请输入商户名" @select="handleSelect">
              </el-autocomplete>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="服务商名称" prop="name3" required>
              <el-input v-model="basicInfo.name3" placeholder="请输入商户名称"></el-input>
            </el-form-item>
          </td>
        </tr>

        <tr>
          <td>
            <el-form-item label="服务商简称" prop="name5" required>
              <el-input v-model="basicInfo.name5" placeholder="请输入服务商简称"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="服务商邮箱" prop="name5" required>
              <el-input v-model="basicInfo.name5" placeholder="请输入服务商邮箱"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="客服号码" prop="name6" required>
              <el-input v-model="basicInfo.name6" placeholder="请输入客服号码"></el-input>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <el-form-item label="服务商地址" prop="address" required class="form-item-address">
              <el-select v-model="basicInfo.address.name1" placeholder="请选择">
                <el-option v-for="(item,index) in province" :key="index" :label="item.name" :value="item.value">
                </el-option>
              </el-select>
              &nbsp;
              <el-select v-model="basicInfo.address.name2" placeholder="请选择">
                <el-option v-for="(item,index) in city" :key="index" :label="item.name" :value="item.value">
                </el-option>
              </el-select>
              &nbsp;
              <el-select v-model="basicInfo.address.name3" placeholder="请选择">
                <el-option v-for="(item,index) in area" :key="index" :label="item.name" :value="item.value">
                </el-option>
              </el-select>
              &nbsp;
              <el-input class="address-input" v-model="basicInfo.address.name4" placeholder="详细地址"></el-input>
            </el-form-item>
          </td>

          <td>
            <el-form-item label="营业执照编号" prop="name7" required>
              <el-input v-model="basicInfo.name7" placeholder="请输入营业执照编号"></el-input>
            </el-form-item>
          </td>

        </tr>
        <tr>
          <td>
            <el-form-item label="营业执照有效期" prop="name8" required>
              <el-date-picker v-model="basicInfo.name8" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <el-form-item prop="name11" label="营业执照扫描件" required>
              <SiteUpload v-model="basicInfo.name11" />
            </el-form-item>
          </td>
        </tr>
      </table>
    </el-form>
    <h3>法人信息</h3>
    <el-form :model="basicInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :show-message="false">
      <table class="info-item-table" border="1">
        <tr>
          <td>
            <el-form-item label="法人真实姓名" prop="name15" required>
              <el-input v-model="basicInfo.name15" placeholder="请输入法人真实姓名"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="法人身份证号码" prop="name16" required>
              <el-input v-model="basicInfo.name16" placeholder="请输入法人身份证号码"></el-input>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <el-form-item prop="name18" label="法人身份证正面" required>
              <SiteUpload v-model="basicInfo.name18" />
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <el-form-item prop="name19" label="法人身份证背面" required>
              <SiteUpload v-model="basicInfo.name19" />
            </el-form-item>
          </td>
        </tr>
      </table>
    </el-form>

    <h3>联系信息</h3>
    <el-form :model="basicInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :show-message="false">
      <table class="info-item-table" border="1">
        <tr>
          <td>
            <el-form-item label="联系人姓名" prop="name20" required>
              <el-input v-model="basicInfo.name20" placeholder="请输入法人真是姓名"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="联系人手机号码" prop="name21" required>
              <el-input v-model="basicInfo.name21" placeholder="请输入联系人手机号码"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="推荐人" prop="name21" required>
              <el-input v-model="basicInfo.name21" placeholder="请输入推荐人姓名"></el-input>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td>
            <el-form-item label="服务商级别" prop="name20" required>
              <el-input v-model="basicInfo.name20" placeholder="请输入服务商级别"></el-input>
            </el-form-item>
          </td>
        </tr>
      </table>
    </el-form>
    <h3>结算信息</h3>
    <el-form :model="basicInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :show-message="false">
      <table class="info-item-table" border="1">
        <tr>
          <td>
            <el-form-item label="结算账号" prop="name22" required>
              <el-input v-model="basicInfo.name22" placeholder="请输入法人结算账号"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="开户人" prop="name23" required>
              <el-input v-model="basicInfo.name23" placeholder="请输入开户人"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="开户省份" prop="address" required>
              <el-select v-model="basicInfo.address.name1" placeholder="请选择">
                <el-option v-for="(item,index) in province" :key="index" :label="item.name" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td>
            <el-form-item label="开户城市" prop="address" required>
              <el-select v-model="basicInfo.address.name1" placeholder="请选择">
                <el-option v-for="(item,index) in city" :key="index" :label="item.name" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="开户银行" prop="name24" required>
              <el-input v-model="basicInfo.name24" placeholder="开户银行"></el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item label="开户支行" prop="name25" required>
              <el-input v-model="basicInfo.name25" placeholder="请输入法人真是姓名"></el-input>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td>
            <el-form-item label="结算类型" prop="name26" required>
              <el-select v-model="basicInfo.name27" placeholder="请选择">
                <el-option label="对公" value="1"></el-option>
                <el-option label="对私" value="0"></el-option>
              </el-select>
            </el-form-item>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <el-form-item prop="name27" label="开户许可证" required>
              <SiteUpload v-model="basicInfo.name27" />
            </el-form-item>
          </td>
        </tr>
      </table>
    </el-form>

    <template v-if="type === '0' || type === '3'">
      <h3>操作记录</h3>
      <el-form :model="operationInfo" label-width="130px" :disabled="isLook" :inline="true" ref="controlQueryForm" :show-message="false">
        <table class="info-item-table" border="1">
          <tr>
            <td>
              <el-form-item label="录入人" prop="name1" required>
                <el-input v-model="operationInfo.name1"></el-input>
              </el-form-item>
            </td>
            <td>
              <el-form-item label="录入时间" prop="name2" required>
                <el-input v-model="operationInfo.name2"></el-input>
              </el-form-item>
            </td>
            <td>
              <el-form-item v-if="type === '0'" label="审核人" prop="name3" required>
                <el-input v-model="operationInfo.name3"></el-input>
              </el-form-item>
            </td>
          </tr>
          <tr v-if="type === '0'">
            <td>
              <el-form-item label="审核时间" prop="name4" required>
                <el-input v-model="operationInfo.name4"></el-input>
              </el-form-item>
            </td>
            <td>
              <el-form-item label="审核记录" prop="name5" required>
                <el-input v-model="operationInfo.name5"></el-input>
              </el-form-item>
            </td>
            <td>

            </td>
          </tr>
        </table>
      </el-form>
    </template>

    <div class="btns">
      <!-- 新增及更新 -->
      <template v-if="type === '1' || type === '2'">
        <el-button type="primary" @click="goToSave">保存</el-button>
      </template>
      <!-- 审核状态 -->
      <template v-if="type === '3'">
        <el-button type="primary" @click="goToPass">审核通过</el-button>
        <el-button type="primary" @click="goToOnPass">审核不通过</el-button>
      </template>
      <el-button type="info" @click="goToBack">关闭</el-button>
    </div>

  </div>
</template>

<script>
import SiteUpload from '@/components/SiteUpload';
import BusinessPeople from '@/tools/data4'; //业务所属人员
export default {
  components: { SiteUpload },
  data() {
    return {
      type: '',
      isLook: false,
      merchanrtsInfo: {},
      basicInfo: {
        address: {},
        name8: null
      },
      operationInfo: {},
      // 地址选择
      province: [{ name: '广东', value: '0' }],
      city: [{ name: '深圳', value: '0' }],
      area: [{ name: '南山', value: '0' }],
      uploadFileName: '未选择任何文件',
      uploadImageSrc: ''
    };
  },
  created() {
    // 新增1 + 浏览0 + 修改2 + 审核3
    const { type } = this.$route.params;
    this.type = type;
    if (type === '0' || type === '3') this.isLook = true;
  },
  methods: {
    // 业务人员候选操作
    merchantQuerySearch(queryString, cb) {
      var restaurants = BusinessPeople;
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
    goToSave() {
      window.console.log(this.basicInfo, this.$router);
    },
    goToPass() {
      window.console.log('通过');
    },
    goToNoPass() {
      window.console.log('不通过');
    },
    goToBack() {
      this.$router.back();
    }
  }
};
</script>

<style lang="scss">
.merchanrts-list-operation-page {
  h3 {
    height: 30px;
    line-height: 30px;
    padding-left: 10px;
    font-size: 17px;
    background-color: #ccc;
  }

  table,
  td,
  td {
    border: 1px solid rgb(240, 240, 240);
  }

  table {
    border-left: 0;
    border-right: 0;
  }
  overflow: scroll;
  .info-item-table {
    width: 100%;
  }
  .el-form-item {
    margin: 0 !important;
  }
  .form-item-address {
    .el-select {
      .el-input {
        width: 130px;
      }
    }
  }
  td {
    text-align: left;
    padding: 6px 0;
    width: 33.33%;
  }
  // .el-form .el-form-item {
  //   width: 50%;
  //   margin: 0;
  // }
  .address-input {
    width: 250px;
  }

  .btns {
    padding: 20px 0;
    text-align: center;
  }
}
</style>