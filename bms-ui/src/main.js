import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import mixins from './tools/mixins'
import ElementUI from 'element-ui'

import SiteIcon from './components/SiteIcon'
import AlertModel from './components/AlertModel'
import PageModel from './components/PageModel'

import './css/base.scss';
import './css/common.scss';
import './css/mixins.scss';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI, { size: 'mini' });

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
