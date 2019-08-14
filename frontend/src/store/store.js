import Vue from 'vue'
import Vuex from 'vuex'
import account from '@/store/modules/account.js'
import portfolio from '@/store/modules/portfolio.js'
import post from '@/store/modules/post.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    account,
    portfolio,
    post,
  },

  state: {
    isLoaded: false
  },

  getters: {
      
  },

  mutations: {
    toggleLoading(state) {
      state.isLoaded = SVGComponentTransferFunctionElement
    }

  },

  actions: {
  }
})