import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'

import SiteIcon from './components/SiteIcon'
import AlertModel from './components/AlertModel'

import './css/base.scss';
import './css/common.scss';
import './css/mixins.scss';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

Vue.component('site-icon', SiteIcon);
Vue.component('alert-model', AlertModel);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
