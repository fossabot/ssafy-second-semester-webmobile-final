import firebase from '../../apis/firebase/firebase'

const state = {
  accountEmail:"",
  accountName: "",
  accountAuth: "",
  accountIsPush : "",
  loginCheck : false
}

const getters = {
  getLoginUserAuth(state) {
    return state.accountAuth ? state.accountAuth : "NoUserAuth"
  },

  getLoginUserName(state) {
    return state.accountName ? state.accountName : "NoUserName"
  }
}

const actions = {
  isLogin({state}) {
    if(state.accountEmail!=null && state.accountName !=null &&state.accountEmail!="" &&state.accountName!="") {
      state.loginCheck = true;
    } else {
      state.loginCheck=false;      
    }
  },
  async logout({commit}) {
    await commit('setInit')
    sessionStorage.removeItem('key')
    //서버랑 연동 뒤 세션이나 쿠키 부분 핸들링 해야함
  },
  async getUser({commit,dispatch}, {key}) {
    let data = await firebase.getInfo(key)  
    commit('setUser',{data:data})
    dispatch('isLogin')
  }
}

const mutations = {
  setUser( state, { data }) { 
    if (data) {
      state.accountAuth=data.auth
      state.accountEmail=data.email
      state.accountName=data.name
      state.accountIsPush = data.ispush
    }
  },
  setInit(state) {
    state.accountEmail= ""
    state.accountName= ""
    state.accountAuth= ""
    state.accountIsPush = ""
    state.loginCheck=false
  },
  setSignUp(state, {loginId,loginName}) {
    state.accountEmail= loginId
    state.accountName= loginName
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
