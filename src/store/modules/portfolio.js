import mainServices from '@/apis/mainservice/mainServices.js'

const state = {
  portfolios: [],
  portfolio: {},
}

const getters = {
  totalComments(state) {
    return state.portfolio.portfolioComments ? state.portfolio.portfolioComment.length : 0
  }
}

const actions = {
  getPortfolios({commit}) {
    mainServices.getPortfolios()
                .then((portfolios) => {
                  commit('setPortfolios', portfolios)
                })
  },
  getPortfolio({commit},portfolioId) {
    mainServices.getPortfolio(portfolioId) 
                .then((portfolio) => {
                  commit('setPortfolio',portfolio)
                })
  },
}

const mutations = {
  setPortfolios(state, portfolios) {
    state.portfolios = portfolios
  },
  setPortfolio(state, portfolio) {
    state.portfolio = portfolio
  },

  setNewPortfolio(state) { // 새 글 작성은 작성자 구분 없이 로그인 되어있는 계정으로 바로 쓰고, portfolioEditor를 새 글과 수정에 공유할 거라서 따로 만든다.
    state.portfolio = {
      accountEmail: "",
      accountName: "",
      portfolioTitle: "",
      portfolioContent: "",
      portfolioGiturl: "",
      portfolioThumbnailUrl: "",
      portfolioImages: [],
    }
  },

}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
