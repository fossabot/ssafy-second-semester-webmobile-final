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

// 여기서부터 다시 07.23
const actions = {
  getArticles ({commit, getters}, categoryName) {
    console.log("articles.js 의 getArticles 들어옴")
    const categoryName2 = categoryName
    mainServices.getArticles(categoryName2) // 예외처리를 여기서 해줘야 하나?
                .then((data) => {
                  console.log("mainService 에서 res.data 받아옴")
                  console.log(data)
                  const articles = data
                  var articleCategoryId = 0
                  if (categoryName2 === 'portfolios') {
                    articleCategoryId = 1
                  } else {
                    articleCategoryId = 2
                  }
                  commit('setArticles',{articleCategoryId,articles})
                })
  }

}

const mutations = {
  
  setArticles (state, { articleCategoryId, articles }) {
    console.log("setArticles 에 커밋됨")
    console.log(articleCategoryId)
    console.log(articles)
    state.articleCategoryId = articleCategoryId
    state.articles = articles
    console.log("state 바뀌었니?")
    console.log(state.articleCategoryId)
    console.log(state.articles)    
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