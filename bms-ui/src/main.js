import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import mixins from './tools/mixins'
import {
  Button, Option, Select, Form, FormItem, Menu, MenuItem, Submenu, Pagination, Table, DatePicker, TableColumn,
  Input, Checkbox, CheckboxGroup, Header, Main, Aside, Container, Breadcrumb, BreadcrumbItem, Message, Radio,
  RadioGroup, Dialog, Upload, Tabs, TabPane, Col, Autocomplete
} from 'element-ui'

import SiteIcon from './components/SiteIcon'
import AlertModel from './components/AlertModel'
import PageModel from './components/PageModel'

import './css/base.scss';
import './css/common.scss';
import './css/mixins.scss';
import 'element-ui/lib/theme-chalk/index.css';


/* eslint-disable */
Message.install = function (Vue, options) {
  Vue.prototype.$message = Message
}
/* eslint-enable*/


Vue
  .use(Button).use(Option).use(Select).use(Form).use(FormItem).use(Menu).use(MenuItem).use(Submenu).use(Pagination).use(Table).use(TableColumn)
  .use(Input).use(Checkbox).use(CheckboxGroup).use(Header).use(Main).use(Aside).use(Container).use(BreadcrumbItem).use(Breadcrumb).use(DatePicker)
  .use(Message).use(Radio).use(RadioGroup).use(Dialog).use(Upload).use(Tabs).use(TabPane).use(Col).use(Autocomplete)

Vue.prototype.$ELEMENT = { size: 'mini' };

Vue.component('site-icon', SiteIcon);
Vue.component('alert-model', AlertModel);
Vue.component('page-model', PageModel);

Vue.config.productionTip = false

Vue.mixin(mixins)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
