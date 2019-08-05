import axios from 'axios'

const state = {
  default: null,  
}

const getters = {
  defaultGetter(state, getter) {
    return state.default ? state.default.length : 0
  }
}

const actions = {
  defaultAction( { state, commit, getters }, payload ) {
    console.log(state.default)
    commit('setDefault',{default: "Hello World"})
    console.log(getters.defaultGetter)
    
  }
}

const mutations = {
  setDefault(state,payload) {
    state.default = payload.default
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
