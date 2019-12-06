import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

import Home from '@/views/Home/Home'
import Login from '@/views/Login/Login'
import Welcome from '@/views/Welcome/Welcome'
// 系统管理
import Suser from '@/views/System/Suser/Suser'
const Role = () => import(/* webpackChunkName: "system" */ '@/views/System/Role/Role');
const Menu = () => import(/* webpackChunkName: "system" */ '@/views/System/Menu/Menu');
const Depart = () => import(/* webpackChunkName: "system" */ '@/views/System/Depart/Depart');
const TaskConfig = () => import(/* webpackChunkName: "system" */ '@/views/System/TaskConfig/TaskConfig');
const TaskLog = () => import(/* webpackChunkName: "system" */ '@/views/System/TaskLog/TaskLog');
const SmsLog = () => import(/* webpackChunkName: "system" */ '@/views/System/SmsLog/SmsLog');
const Test = () => import(/* webpackChunkName: "system" */ '@/views/System/Test/Test');
const Auth = () => import(/* webpackChunkName: "system" */ '@/views/System/Market/Auth/Auth');
const Group = () => import(/* webpackChunkName: "system" */ '@/views/System/Market/Group/Group');
// 异常列表
const Account = () => import(/* webpackChunkName: "abnormal" */ '@/views/Abnormal/Account/Account');
const Exchange = () => import(/* webpackChunkName: "abnormal" */ '@/views/Abnormal/Exchange/Exchange');
const Message = () => import(/* webpackChunkName: "abnormal" */ '@/views/Abnormal/Message/Message');
// 工作流管理
const Wuser = () => import(/* webpackChunkName: "Workflow" */ '@/views/Workflow/Wuser/Wuser');
const Wgroup = () => import(/* webpackChunkName: "Workflow" */ '@/views/Workflow/Wgroup/Wgroup');
// 交易管理
const Consumption = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Consumption/Consumption');
const Summary = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Summary/Summary');
const Recharge = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Recharge/Recharge');
const Revocation = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Revocation/Revocation');
const Refund = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Refund/Refund');
const Transfer = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Transfer/Transfer');
const Transactions = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Transactions/Transactions');
const RechargeRe = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/RechargeRe/RechargeRe');
const TransferRe = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/TransferRe/TransferRe');
const Merchants = () => import(/* webpackChunkName: "Trading" */ '@/views/Trading/Merchants/Merchants');


// 菜单列表
export const menuRouter = [
  {
    name: '系统管理',
    path: '',
    children: [
      { name: '用户管理', path: '/system/suser', component: Suser },
      { name: '角色管理', path: '/system/role', component: Role },
      { name: '菜单管理', path: '/system/menu', component: Menu },
      { name: '部门管理', path: '/system/depart', component: Depart },
      { name: '任务调度配置', path: '/system/taskconfig', component: TaskConfig },
      { name: '任务调度日志', path: '/system/tasklog', component: TaskLog },
      { name: '短信邮件日志', path: '/system/smslog', component: SmsLog },
      { name: '测试', path: '/system/test', component: Test },
      {
        name: '市场部管理',
        path: '',
        children: [
          { name: '市场部权限', path: '/abnormal/market/auth', component: Auth },
          { name: '市场部分组管理', path: '/abnormal/market/group', component: Group }
        ]
      }
    ]
  },
  {
    name: '异常列表',
    path: '',
    children: [
      { name: '交广科技开户未明列表', path: '/abnormal/account', component: Account },
      { name: '交广科技交易未明列表', path: '/abnormal/exchange', component: Exchange },
      { name: '异常信息列表', path: '/abnormal/message', component: Message }
    ]
  },
  {
    name: '工作流管理',
    path: '',
    children: [
      { name: '工作流用户', path: '/workflow/wuser', component: Wuser },
      { name: '工作流用户组', path: '/workflow/wgroup', component: Wgroup }
    ]
  },
  {
    name: '交易管理',
    path: '',
    children: [
      { name: '消费查询', path: '/trading/consumption', component: Consumption },
      { name: '商户交易汇总', path: '/trading/summary', component: Summary },
      { name: '充值查询', path: '/trading/recharge', component: Recharge },
      { name: '交易撤销', path: '/trading/revocation', component: Revocation },
      { name: '退款管理', path: '/trading/refund', component: Refund },
      { name: '客户转账', path: '/trading/transfer', component: Transfer },
      { name: '交易汇总查询', path: '/trading/transactions', component: Transactions },
      { name: '充值撤销管理', path: '/trading/rechargere', component: RechargeRe },
      { name: '转账撤销管理', path: '/trading/transferre', component: TransferRe },
      { name: '商户转账', path: '/trading/merchants', component: Merchants }
    ]
  },
  {
    name: '聚合支付',
    path: '/system/user',
    children: [
      {
        name: '代理商交易管理',
        path: '/system/user',
        children: [
          { name: '代理商每日汇集', path: '/system/user' },
          { name: '代理商协议', path: '/system/user' },
          { name: '代理商结算', path: '/system/user' }
        ]
      },
      {
        name: '商户交易管理',
        path: '/system/user',
        children: [
          { name: '渠道金额限制', path: '/system/user' },
          { name: '商户每日汇集数据', path: '/system/user' },
          { name: '商户产品费率', path: '/system/user' },
          { name: '商户退款', path: '/system/user' },
          { name: '商户交易汇总', path: '/system/user' }
        ]
      },
      {
        name: '订单信息',
        path: '/system/user',
        children: [
          { name: '订单列表', path: '/system/user' },
          { name: '退款列表', path: '/system/user' }
        ]
      },
      {
        name: '产品渠道',
        path: '/system/user',
        children: [
          { name: '支付渠道管理', path: '/system/user' },
          { name: '支付产品管理', path: '/system/user' }
        ]
      },
      { name: '订单异常列表', path: '/system/user' },
      { name: '渠道路由', path: '/system/user' }
    ]
  },
  {
    name: '物料管理',
    path: '/system/user',
    children: [{ name: '物料管理', path: '/system/user' }]
  },
  {
    name: '财务管理',
    path: '/system/user',
    children: [
      { name: '商户结算', path: '/system/user' },
      { name: '财务查询', path: '/system/user' },
      { name: '交广科技对账结果', path: '/system/user' },
      { name: '渠道源数据', path: '/system/user' },
      { name: '银联对账结果', path: '/system/user' },
      { name: '财务异常处理', path: '/system/user' },
      { name: '金蝶财务', path: '/system/user' },
      { name: '中信对账结果查询', path: '/system/user' },
      { name: '代理商结算', path: '/system/user' },
      { name: '对账汇总', path: '/system/user' },
      { name: '差错处理', path: '/system/user' },
      { name: '对账汇总（V2）', path: '/system/user' }
    ]
  },
  {
    name: '交广科技信息查询',
    path: '/system/user',
    children: [
      { name: '持卡人信息', path: '/system/user' },
      { name: '卡账号信息', path: '/system/user' },
      { name: '交广科技交易结果', path: '/system/user' },
      { name: '交广科技应收应付查询', path: '/system/user' },
      { name: '交广科技对账/清算文件', path: '/system/user' }
    ]
  },
  {
    name: '银联交易查询',
    path: '/system/user',
    children: [
      { name: '银联交易结果查询', path: '/system/user' },
      { name: '银联交易应付查询', path: '/system/user' },
      { name: '银联对账/清算文件', path: '/system/user' }
    ]
  },
  {
    name: '码表维护',
    path: '/system/user',
    children: [
      { name: '节假日管理', path: '/system/user' },
      { name: '手续费管理', path: '/system/user' },
      { name: '银行信息管理', path: '/system/user' },
      { name: '证件管理', path: '/system/user' },
      { name: '城市信息管理', path: '/system/user' },
      { name: '问题管理列表', path: '/system/user' },
      { name: '首页广告维护', path: '/system/user' },
      { name: '协议模板维护', path: '/system/user' },
      { name: '数据字典', path: '/system/user' },
      { name: '交易控制', path: '/system/user' },
      { name: '提现控制', path: '/system/user' },
      { name: '支行信息管理', path: '/system/user' },
      { name: '渠道管理', path: '/system/user' },
      { name: '渠道控制管理', path: '/system/user' },
      { name: '渠道路由管理', path: '/system/user' },
      { name: '代付垫资费率信息管理', path: '/system/user' }
    ]
  },
  {
    name: '商户管理',
    path: '/system/user',
    children: [
      { name: '商户网关维护', path: '/system/user' },
      { name: '商户协议管理', path: '/system/user' },
      { name: '后台商户注册审核列表', path: '/system/user' },
      { name: '费率管理', path: '/system/user' },
      { name: '商户注册', path: '/system/user' },
      { name: '商户账户管理', path: '/system/user' },
      { name: '前台商户注册审核表', path: '/system/user' },
      { name: '商户列表', path: '/system/user' },
      { name: '代理商签约', path: '/system/user' },
      { name: '收银员', path: '/system/user' },
      { name: '门店管理', path: '/system/user' },
      { name: '商户报备查询', path: '/system/user' },
      { name: '商户报备', path: '/system/user' },
      {
        name: '商户渠道管理',
        path: '/system/user',
        children: [{ name: '商户渠道列表', path: '/system/user' }]
      },
      { name: '商户列表1', path: '/system/user' },
      { name: '商户列表（新）', path: '/system/user' },
      { name: '商户报备（新）', path: '/system/user' },
      { name: '商户报备选择', path: '/system/user' },
      { name: '商户交易详细', path: '/system/user' },
      { name: '商户列表（最新）', path: '/system/user' },
      { name: '服务商列表（最新）', path: '/system/user' },
      { name: '商户录入管理', path: '/system/user' },
      { name: '商户报备列表', path: '/system/user' },
      { name: '商户产品管理', path: '/system/user' },
      { name: '商户设备管理', path: '/system/user' },
      { name: '服务商列表', path: '/system/user' }
    ]
  },
  {
    name: '客户管理',
    path: '/system/user',
    children: [
      { name: '证件审核管理', path: '/system/user' },
      { name: '客户管理列表', path: '/system/user' },
      { name: '客户账户管理', path: '/system/user' }
    ]
  },
  {
    name: '我的工作空间',
    path: '/system/user',
    children: [
      { name: '我的申请单', path: '/system/user' },
      { name: '我的待办任务', path: '/system/user' },
      { name: '我的已审核', path: '/system/user' },
      { name: '所有任务', path: '/system/user' }
    ]
  },
  {
    name: '科目管理',
    path: '/system/user',
    children: [
      { name: '科目信息', path: '/system/user' },
      { name: '科目配置信息', path: '/system/user' },
      { name: '科目汇总', path: '/system/user' },
      { name: '内部账户管理', path: '/system/user' }
    ]
  },
  {
    name: '调账管理',
    path: '/system/user',
    children: [
      { name: '待办', path: '/system/user' },
      { name: '单边调账经办', path: '/system/user' },
      { name: '调账查询', path: '/system/user' },
      { name: '单边调账查询', path: '/system/user' }
    ]
  },
  {
    name: '短信管理',
    path: '/system/user',
    children: [{ name: '短信管列表', path: '/system/user' }]
  },
  {
    name: 'APP管理',
    path: '/system/user',
    children: [
      { name: 'APP登录页广告管理', path: '/system/user' },
      { name: '信用卡申请链接管理', path: '/system/user' }
    ]
  },
  {
    name: '社交',
    path: '/system/user',
    children: [
      { name: '红包列表', path: '/system/user' },
      { name: '红包详细信息列表', path: '/system/user' },
      { name: '红包对账结果', path: '/system/user' }
    ]
  },
  {
    name: '代理商管理', path: '/system/user',
    children: [
      { name: '代理商商户注册', path: '/system/user' },
      { name: '代理商商户列表', path: '/system/user' },
      { name: '代理商注册', path: '/system/user' },
      { name: '代理商列表', path: '/system/user' },
      { name: '代理商审核列表', path: '/system/user' },
      { name: '代理商报表', path: '/system/user' },
      { name: '代理商商户审核列表', path: '/system/user' },
      { name: '代理商协议管理', path: '/system/user' },
      { name: '代理商产品审核', path: '/system/user' },
      { name: '代理商商户审核', path: '/system/user' },
      { name: '服务商注册审核', path: '/system/user' },
      { name: '服务商注册审核(新)', path: '/system/user' }
    ]
  },
  {
    name: '实名认证', path: '/system/user',
    children: [
      { name: '文件列表', path: '/system/user' },
      { name: '验证明细', path: '/system/user' }
    ]
  },
  {
    name: '聚合支付对账', path: '/system/user',
    children: [
      { name: '对账文件列表', path: '/system/user' },
      { name: '对账结果', path: '/system/user' },
      { name: '渠道对账文件下载', path: '/system/user' }
    ]
  },
  {
    name: '学生管理', path: '/system/user',
    children: [{ name: '学生信息', path: '/system/user' }]
  },
  {
    name: '代付管理', path: '/system/user',
    children: [
      { name: '批量代付', path: '/system/user' },
      { name: '代付查询', path: '/system/user' },
      { name: '代付账户管理', path: '/system/user' },
      { name: '代付审核', path: '/system/user' },
      { name: '开通代付审核', path: '/system/user' },
      { name: '代付统计报表', path: '/system/user' },
      { name: '代付记录', path: '/system/user' },
      { name: '充值审核', path: '/system/user' },
      { name: '代付列表', path: '/system/user' },
      { name: '新增代付', path: '/system/user' },
      { name: '凭证审核列表(新)', path: '/system/user' },
      { name: '代付产品列表', path: '/system/user' },
      { name: '代付详细列表', path: '/system/user' },
      { name: '代付收益', path: '/system/user' },
      { name: '商户渠道', path: '/system/user' }
    ]
  }
];

function flatten(arr) {
  const arr2 = [];
  function deal(obj) {
    for (var key in obj) {
      if (typeof obj[key] === "object" && obj[key] !== null && obj[key].length) {
        obj[key].forEach(ele => {
          if (ele.children && ele.children.length) {
            deal(ele);
          } else {
            arr2.push(ele);
          }
        });
      }
    }
  }
  arr.forEach(ele => deal(ele));
  return arr2.flat();
}

const menuRoutes = [...flatten(menuRouter), { path: '/welcome', component: Welcome }];

// 默认路由
const tacitly = [
  {
    path: '/login',
    component: Login
  },
  {
    path: '/system',
    component: Home,
    children: menuRoutes
  }
];

// 通配路由
const common = [
  {
    path: '/',
    redirect: '/welcome'
  },
  {
    path: '*',
    redirect: '/system/suser'
  }
];

const routes = [...tacitly, ...common];

export default new Router({
  routes
})
