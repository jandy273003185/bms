import Vue from 'vue'
import Vuex, { Store } from 'vuex'

Vue.use(Vuex)

export default new Store({
  state: {
    accessToken: ''
  },
  mutations: {
    setToken(state, token) {
      window.console.log(token, 'token');
      state.accessToken = token;
    },
    getToken(state) {
      return state.accessToken;
    }
  },
  actions: {
  },
  modules: {
  }
})
