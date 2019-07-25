import axios from 'axios'
import mainServices from '../apis/mainServices'

const state = {
  // checkoutState = 0, 이건 나중에 생각해보자.
  articleCategoryName: null,
  articles = [],
  article = {},
  isAuthor = false,
}

const getters = {
  totalAticle : state => {

  },
  totalComment: state => {

  }

}

// 여기서부터 다시 07.23
const actions = {
  getArticles ({commit, getters}, categoryName) {
    mainServices.getAritcles(categoryName)
                .then((data) => {

                })
  }

}

const mutations = {
  
  setArticles (state, { articleCategoryId, articles}) {
    state.articleCategoryId = articleCategoryId
    state.articles = articles    
  },

  setArticle( state, { article }) {
    state.article = article
  },
  
  // 흠 이건 잘 모르겠다. 어떤식으로 설정할지는 나중에 user 모델 생기면 수정 07.23
  setIsAuthor( state, { isAuthor }) {
    state.isAuthor = isAuthor 
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations

}