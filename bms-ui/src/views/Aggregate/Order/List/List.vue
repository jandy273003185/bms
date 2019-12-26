<template>
  <!-- 聚合支付 => 订单信息 => 订单列表 -->
  <div class="aggregate-order-list-page">
    <page-model>
      <template slot="controlQueryOps">
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm1">
          <el-form-item label="支付产品" prop="name1">
            <el-select v-model="examine.name1" placeholder="请选择">
              <el-option value="h5.gateway.pay" label="H5支付"></el-option>
              <el-option value="h5_t.gateway.pay" label="原生H5支付"></el-option>
              <el-option value="mobile.plugin.pay" label="APP支付"></el-option>
              <el-option value="onecode.coll.pay" label="扫码支付"></el-option>
              <el-option value="pc.gateway.pay" label="网关支付"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="支付渠道" prop="name2">
            <el-select v-model="examine.name2" placeholder="请选择">
              <el-option v-for="(item,index) in paylist" :key="index" :label="item.name" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="订单状态" prop="name3">
            <el-select v-model="examine.name3" placeholder="请选择">
              <el-option label="等待支付" value="0"></el-option>
              <el-option label="支付处理中" value="1"></el-option>
              <el-option label="支付成功" value="2"></el-option>
              <el-option label="支付失败" value="3"></el-option>
              <el-option label="取消" value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="渠道" prop="name4">
            <el-select v-model="examine.name4" placeholder="请选择">
              <el-option label="微信" value="0"></el-option>
              <el-option label="七分钱" value="1"></el-option>
              <el-option label="支付宝" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm2">
          <el-form-item label="商户编号" prop="name5">
            <el-input v-model="examine.name5"></el-input>
          </el-form-item>
          <el-form-item label="订单号" prop="name6">
            <el-input v-model="examine.name6"></el-input>
          </el-form-item>
          <el-form-item label="中信订单号" prop="name7">
            <el-input v-model="examine.name7"></el-input>
          </el-form-item>
          <el-form-item label="渠道订单号" prop="name8">
            <el-input v-model="examine.name8"></el-input>
          </el-form-item>
        </el-form>
        <el-form :model="examine" label-width="110px" :inline="true" ref="controlQueryForm3">
          <el-form-item label="创建日期" prop="name9">
            <el-date-picker v-model="examine.name9" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="账期时间" prop="name10">
            <el-date-picker v-model="examine.name10" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </template>

      <template slot="controlQueryBtns">
        <el-button type="primary" @click="goToSearch">查询<i class="el-icon-search"></i> </el-button>
        <el-button type="warning" @click="restFormFiles">清空<i class="el-icon-rank"></i></el-button>
        <el-button type="info" @click="download">导出报表<i class="el-icon-download"></i></el-button>
      </template>

      <template slot="tableInner">
        <el-table :data="tableData" border class="nofix">
          <el-table-column prop='name1' label='订单号' width="100"></el-table-column>
          <el-table-column prop='name3' label='商户编号'></el-table-column>
          <el-table-column prop='name4' label='商户名称'></el-table-column>
          <el-table-column prop='name5' label='渠道编号'></el-table-column>
          <el-table-column prop='name16' label='下级渠道'></el-table-column>
          <el-table-column prop='name6' label='中信订单号'></el-table-column>
          <el-table-column prop='name7' label='渠道订单号'></el-table-column>
          <el-table-column prop='name8' label='订单金额'></el-table-column>
          <el-table-column prop='name9' label='付款金额'></el-table-column>
          <el-table-column prop='name10' label='结算金额'></el-table-column>
          <el-table-column prop='name11' label='代金券'></el-table-column>
          <el-table-column prop='name12' label='商品描述'></el-table-column>
          <el-table-column prop='name13' label='支付产品'></el-table-column>
          <el-table-column prop='name14' label='订单状态'></el-table-column>
          <el-table-column prop='name15' label='创建时间'></el-table-column>
        </el-table>
      </template>

      <template slot="pageModelPagination">
        <el-pagination background layout="prev, pager, next, sizes, total, jumper" :page-sizes="paginationOps.pageSizes" :total="paginationOps.total">
        </el-pagination>
      </template>
    </page-model>
  </div>
</template>

<script>
// const testData = {};
const paylist = [
  { value: 'ALIPAY.ALIPAY.APP', name: '支付宝-支付宝-APP' },
  { value: 'ALIPAY.ALIPAY.JSAPI', name: '支付宝-支付宝-公众号' },
  { value: 'ALIPAY.ALIPAY.MICROPAY', name: '支付宝-支付宝-刷卡' },
  { value: 'ALIPAY.ALIPAY.MWEB', name: '支付宝-支付宝-H5' },
  { value: 'ALIPAY.ALIPAY.NATIVE', name: '支付宝-支付宝-扫码' },
  { value: 'BEST_PAY.ALIPAY.APP', name: '翼支付-支付宝-APP' },
  { value: 'BEST_PAY.ALIPAY.MICROPAY', name: '翼支付-支付宝-刷卡' },
  { value: 'BEST_PAY.ALIPAY.NATIVE', name: '翼支付-支付宝-扫码' },
  { value: 'BEST_PAY.WEIXIN.APP', name: '翼支付-微信-APP' },
  { value: 'BEST_PAY.WEIXIN.JSAPI', name: '翼支付-微信-公众号' },
  { value: 'BEST_PAY.WEIXIN.MICROPAY', name: '翼支付-微信-刷卡' },
  { value: 'BEST_PAY.WEIXIN.MWEB', name: '翼支付-微信-H5' },
  { value: 'BEST_PAY.WEIXIN.NATIVE', name: '翼支付-微信-扫码' },
  { value: 'fm.pay.alipay.jspay', name: '富民银行支付宝公众号' },
  { value: 'fm.pay.alipay.native', name: '富民银行支付宝公众号' },
  {
    value: 'fm.pay.weixin.jspay',
    name: '富民银行微信公众账号支付（JS支付）接口'
  },
  { value: 'fm.pay.weixin.native', name: '富民银行微信扫码支付接口' },
  { value: 'fm.unified.trade.miFmopay', name: '富民银行刷卡' },
  { value: 'FM_COMBINEDPAY.ALIPAY.APP', name: '富民银行-支付宝-APP' },
  { value: 'FM_COMBINEDPAY.ALIPAY.MICROPAY', name: '富民银行-支付宝-刷卡' },
  { value: 'FM_COMBINEDPAY.ALIPAY.NATIVE', name: '富民银行-支付宝-扫码' },
  { value: 'FM_COMBINEDPAY.WEIXIN.APP', name: '富民银行-微信-APP' },
  { value: 'FM_COMBINEDPAY.WEIXIN.JSAPI', name: '富民银行-微信-公众号' },
  { value: 'FM_COMBINEDPAY.WEIXIN.MICROPAY', name: '富民银行-微信-刷卡' },
  { value: 'FM_COMBINEDPAY.WEIXIN.MWEB', name: '富民银行-微信-H5' },
  { value: 'FM_COMBINEDPAY.WEIXIN.NATIVE', name: '富民银行-微信-扫码' },
  {
    value: 'gd.pay.alipay.jspay',
    name: '光大银行支付宝统一下单（JS支付）接口'
  },
  { value: 'gd.pay.alipay.native', name: '光大银行支付宝扫码支付2.0接口' },
  {
    value: 'gd.pay.weixin.jspay',
    name: '光大银行微信公众账号支付（JS支付）接口'
  },
  { value: 'gd.pay.weixin.native', name: '光大银行微信扫码支付接口' },
  { value: 'gd.unified.trade.micropay', name: '光大银行统一小额支付接口' },
  { value: 'GHXB.ALIPAY.APP', name: '广东华兴-支付宝-APP' },
  { value: 'GHXB.ALIPAY.MICROPAY', name: '广东华兴-支付宝-刷卡' },
  { value: 'GHXB.ALIPAY.NATIVE', name: '广东华兴-支付宝-扫码' },
  { value: 'GHXB.WEIXIN.APP', name: '广东华兴-微信-APP' },
  { value: 'GHXB.WEIXIN.JSAPI', name: '广东华兴-微信-公众号' },
  { value: 'GHXB.WEIXIN.MICROPAY', name: '广东华兴-微信-刷卡' },
  { value: 'GHXB.WEIXIN.MWEB', name: '广东华兴-微信-H5' },
  { value: 'GHXB.WEIXIN.NATIVE', name: '广东华兴-微信-扫码' },
  { value: 'HELIPAY.ALIPAY.APP', name: '合利宝-支付宝-APP' },
  { value: 'HELIPAY.ALIPAY.MICROPAY', name: '合利宝-支付宝-刷卡' },
  { value: 'HELIPAY.ALIPAY.NATIVE', name: '合利宝-支付宝-扫码' },
  { value: 'HELIPAY.WEIXIN.APP', name: '合利宝-微信-APP' },
  { value: 'HELIPAY.WEIXIN.JSAPI', name: '合利宝-微信-公众号' },
  { value: 'HELIPAY.WEIXIN.MICROPAY', name: '合利宝-微信-刷卡' },
  { value: 'HELIPAY.WEIXIN.MWEB', name: '合利宝-微信-H5' },
  { value: 'HELIPAY.WEIXIN.NATIVE', name: '合利宝-微信-扫码' },
  { value: 'hr.pay.alipay.jspay', name: '华润银行支付宝公众号' },
  { value: 'hr.pay.alipay.native', name: '华润银行支付宝公众号' },
  {
    value: 'hr.pay.weixin.jspay',
    name: '华润银行微信公众账号支付（JS支付）接口'
  },
  { value: 'hr.pay.weixin.native', name: '华润银行微信扫码支付接口' },
  { value: 'hr.unified.trade.micropay', name: '华润银行刷卡' },
  { value: 'KFT_PAY.ALIPAY.APP', name: '快付通-支付宝-APP' },
  { value: 'KFT_PAY.ALIPAY.MICROPAY', name: '快付通-支付宝-刷卡' },
  { value: 'KFT_PAY.ALIPAY.NATIVE', name: '快付通-支付宝-扫码' },
  { value: 'KFT_PAY.WEIXIN.APP', name: '快付通-微信-APP' },
  { value: 'KFT_PAY.WEIXIN.JSAPI', name: '快付通-微信-公众号' },
  { value: 'KFT_PAY.WEIXIN.MICROPAY', name: '快付通-微信-刷卡' },
  { value: 'KFT_PAY.WEIXIN.MWEB', name: '快付通-微信-H5' },
  { value: 'KFT_PAY.WEIXIN.NATIVE', name: '快付通-微信-扫码' },
  { value: 'KQ_PAY.ALIPAY.APP', name: '快钱-支付宝-APP' },
  { value: 'KQ_PAY.ALIPAY.MICROPAY', name: '快钱-支付宝-刷卡' },
  { value: 'KQ_PAY.ALIPAY.NATIVE', name: '快钱-支付宝-扫码' },
  { value: 'KQ_PAY.WEIXIN.APP', name: '快钱-微信-APP' },
  { value: 'KQ_PAY.WEIXIN.JSAPI', name: '快钱-微信-公众号' },
  { value: 'KQ_PAY.WEIXIN.MICROPAY', name: '快钱-微信-刷卡' },
  { value: 'KQ_PAY.WEIXIN.MWEB', name: '快钱-微信-H5' },
  { value: 'KQ_PAY.WEIXIN.NATIVE', name: '快钱-微信-扫码' },
  { value: 'local.pay.qfq.jspay', name: '七分钱js调用接口' },
  {
    value: 'pf.pay.alipay.jspay',
    name: '浦发银行支付宝统一下单（JS支付）接口'
  },
  { value: 'pf.pay.alipay.native', name: '浦发银行支付宝扫码支付2.0接口' },
  {
    value: 'pf.pay.weixin.jspay',
    name: '浦发银行微信公众账号支付（JS支付）接口'
  },
  { value: 'pf.pay.weixin.native', name: '浦发银行微信扫码支付接口' },
  { value: 'pf.unified.trade.micropay', name: '浦发银行统一小额支付接口' },
  { value: 'QM_PAY.ALIPAY.APP', name: '易宝钱麦-支付宝-APP' },
  { value: 'QM_PAY.ALIPAY.MICROPAY', name: '易宝钱麦-支付宝-刷卡' },
  { value: 'QM_PAY.ALIPAY.NATIVE', name: '易宝钱麦-支付宝-扫码' },
  { value: 'QM_PAY.WEIXIN.APP', name: '易宝钱麦-微信-APP' },
  { value: 'QM_PAY.WEIXIN.JSAPI', name: '易宝钱麦-微信-公众号' },
  { value: 'QM_PAY.WEIXIN.MICROPAY', name: '易宝钱麦-微信-刷卡' },
  { value: 'QM_PAY.WEIXIN.MWEB', name: '易宝钱麦-微信-H5' },
  { value: 'QM_PAY.WEIXIN.NATIVE', name: '易宝钱麦-微信-扫码' },
  { value: 'SUIXING_PAY.ALIPAY.APP', name: '随行付-支付宝-APP' },
  { value: 'SUIXING_PAY.ALIPAY.MICROPAY', name: '随行付-支付宝-刷卡' },
  { value: 'SUIXING_PAY.ALIPAY.NATIVE', name: '随行付-支付宝-扫码' },
  { value: 'SUIXING_PAY.WEIXIN.APP', name: '随行付-微信-APP' },
  { value: 'SUIXING_PAY.WEIXIN.JSAPI', name: '随行付-微信-公众号' },
  { value: 'SUIXING_PAY.WEIXIN.MICROPAY', name: '随行付-微信-刷卡' },
  { value: 'SUIXING_PAY.WEIXIN.MWEB', name: '随行付-微信-H5' },
  { value: 'SUIXING_PAY.WEIXIN.NATIVE', name: '随行付-微信-扫码' },
  { value: 'SUM_PAY.ALIPAY.APP', name: '商盟聚合支付-支付宝-APP' },
  { value: 'SUM_PAY.ALIPAY.MICROPAY', name: '商盟聚合支付-支付宝-刷卡' },
  { value: 'SUM_PAY.ALIPAY.NATIVE', name: '商盟聚合支付-支付宝-扫码' },
  { value: 'SUM_PAY.WEIXIN.APP', name: '商盟聚合支付-微信-APP' },
  { value: 'SUM_PAY.WEIXIN.JSAPI', name: '商盟聚合支付-微信-公众号' },
  { value: 'SUM_PAY.WEIXIN.MICROPAY', name: '商盟聚合支付-微信-刷卡' },
  { value: 'SUM_PAY.WEIXIN.MWEB', name: '商盟聚合支付-微信-H5' },
  { value: 'SUM_PAY.WEIXIN.NATIVE', name: '商盟聚合支付-微信-扫码' },
  { value: 'TX_PAY.ALIPAY.APP', name: '腾讯云-支付宝-APP' },
  { value: 'TX_PAY.ALIPAY.MICROPAY', name: '腾讯云-支付宝-刷卡' },
  { value: 'TX_PAY.ALIPAY.NATIVE', name: '腾讯云-支付宝-扫码' },
  { value: 'TX_PAY.WEIXIN.APP', name: '腾讯云-微信-APP' },
  { value: 'TX_PAY.WEIXIN.JSAPI', name: '腾讯云-微信-公众号' },
  { value: 'TX_PAY.WEIXIN.MICROPAY', name: '腾讯云-微信-刷卡' },
  { value: 'TX_PAY.WEIXIN.MWEB', name: '腾讯云-微信-H5' },
  { value: 'TX_PAY.WEIXIN.NATIVE', name: '腾讯云-微信-扫码' },
  { value: 'wx.pay.h5pay', name: '微信H5支付接口' },
  { value: 'WX.WEIXIN.APP', name: '微信-微信-APP' },
  { value: 'WX.WEIXIN.JSAPI', name: '微信-微信-公众号' },
  { value: 'WX.WEIXIN.MICROPAY', name: '微信-微信-刷卡' },
  { value: 'WX.WEIXIN.MWEB', name: '微信-微信-H5' },
  { value: 'WX.WEIXIN.NATIVE', name: '微信-微信-扫码' },
  { value: 'XL_PAY.ALIPAY.APP', name: '讯联-支付宝-APP' },
  { value: 'XL_PAY.ALIPAY.MICROPAY', name: '讯联-支付宝-刷卡' },
  { value: 'XL_PAY.ALIPAY.NATIVE', name: '讯联-支付宝-扫码' },
  { value: 'XL_PAY.WEIXIN.APP', name: '讯联-微信-APP' },
  { value: 'XL_PAY.WEIXIN.JSAPI', name: '讯联-微信-公众号' },
  { value: 'XL_PAY.WEIXIN.MICROPAY', name: '讯联-微信-刷卡' },
  { value: 'XL_PAY.WEIXIN.MWEB', name: '讯联-微信-H5' },
  { value: 'XL_PAY.WEIXIN.NATIVE', name: '讯联-微信-扫码' },
  { value: 'YQB.ALIPAY.APP', name: '平安付-支付宝-APP' },
  { value: 'YQB.ALIPAY.MICROPAY', name: '平安付-支付宝-刷卡' },
  { value: 'YQB.ALIPAY.NATIVE', name: '平安付-支付宝-扫码' },
  { value: 'YQB.WEIXIN.APP', name: '平安付-微信-APP' },
  { value: 'YQB.WEIXIN.JSAPI', name: '平安付-微信-公众号' },
  { value: 'YQB.WEIXIN.MICROPAY', name: '平安付-微信-刷卡' },
  { value: 'YQB.WEIXIN.MWEB', name: '平安付-微信-H5' },
  { value: 'YQB.WEIXIN.NATIVE', name: '平安付-微信-扫码' },
  {
    value: 'zx.pay.alipay.jspay',
    name: '中信银行支付宝统一下单（JS支付）接口'
  },
  { value: 'zx.pay.alipay.native', name: '中信银行支付宝扫码支付2.0接口' },
  {
    value: 'zx.pay.weixin.jspay',
    name: '中信银行微信公众账号支付（JS支付）接口'
  },
  { value: 'zx.pay.weixin.native', name: '中信银行微信扫码支付接口' },
  { value: 'zx.unified.trade.micropay', name: '中信银行统一小额支付接口' }
];

export default {
  props: ['searchText'],
  data() {
    return {
      examine: {},
      paylist: paylist,
      tableData: [],
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
    restFormFiles() {
      this.$refs['controlQueryForm1'].resetFields();
      this.$refs['controlQueryForm2'].resetFields();
      this.$refs['controlQueryForm3'].resetFields();
    },
    goToSearch() {
      //查询
      window.console.log(this.examine, '查询');
    },
    lookClick(row) {
      this.lookDisplay = true;
      window.console.log(row);
    },
    download() {
      this.$message('导出报表');
    }
  }
};
</script>

<style lang='scss'>
.aggregate-order-list-page .el-input .el-input__inner {
  width: 193px;
}
</style>