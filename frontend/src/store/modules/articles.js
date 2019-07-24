import axios from 'axios'
import mainServices from '../../apis/mainServices'

const state = {
  // checkoutState = 0, 이건 나중에 생각해보자.
  articleCategoryId: null,
  articles: [],
  article: {},
  isAuthor: false,
}

const getters = {
  totalAticle : state => {

  },
  totalComment: state => {

  }

}

const actions = {
  getArticles ({commit, getters}, categoryName) {
    mainServices.getArticles(categoryName) // 예외처리를 여기서 해줘야 하나?
                .then((data) => {
                  const articles = data
                  var articleCategoryId = 0
                  if (categoryName === 'portfolios') {
                    articleCategoryId = 1
                  } else {
                    articleCategoryId = 2
                  }
                  commit('setArticles',{articleCategoryId,articles})
                })
  },
  
  getArticle({commit, getters}, [categoryName, articleNo]) {
    mainServices.getArticle(categoryName, articleNo) 
                .then((data) => {
                  const article = data
                  commit('setArticle', article)
                })
  }
}

const mutations = {
  
  setArticles (state, { articleCategoryId, articles }) {
    state.articleCategoryId = articleCategoryId
    state.articles = articles    
  },

  setArticle( state, article) {
    state.article = article
  },
  
  // 흠 이건 잘 모르겠다. 어떤식으로 설정할지는 나중에 user 모델 생기면 수정 07.23
  setIsAuthor( state, isAuthor) {
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