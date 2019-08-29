import axios from 'axios'
import firebase from '../firebase/firebase'
import store from '../../store/store'


let loginUser = store.state.account 

const getLoginUserInfo = async function() {
  if (sessionStorage.getItem('key')) {
    const LoginUserInfo = firebase.getInfo(sessionStorage.getItem('key'))
    return LoginUserInfo
  } else {
    return false
  }
}

const portfolioUrl = 'https://70.12.246.106:9090/api/bears/portfolios' 
const postUrl = 'https://70.12.246.106:9090/api/bears/posts' 

export default {
  isLogin() {
    if (loginUser.loginCheck) {
      return true
    } else {
      return false
    }
  },

  isAuthor(authorEmail) { 
    if (loginUser.accountEmail === authorEmail) {
      return true
    } else {
      return false
    }
  },

  isTeam() { 
    if (loginUser.accountAuth === 3) { 
      return false
    } else {
      return true
    }
  },

  isAdmin() {
    if (loginUser.accountAuth === 1) {
      return true
    } else {
      return false
    }
  },

  // Portfolios CRUD
  getPortfoliosCount() {
    return axios.get(`${portfolioUrl}/count`)
                .then((res) => {                  
                  return res.data.countPortfolios
              })
  },

  async getPortfoliosBest() {
    return axios.get(`${portfolioUrl}/best`)  
                .then((res) => {
                  return res.data.content
                })
  },

  async loadPortfolios(pageNo) {
    const headers = {
      "Content-Type": "application/json",
      accountEmail: "",
      accountAuth: "" 
    }
    await getLoginUserInfo()
      .then((LoginUserInfo) => {
        if (LoginUserInfo) {
          headers.accountEmail = LoginUserInfo.email
          headers.accountAuth = LoginUserInfo.auth
        }
      })

    return axios.get(`${portfolioUrl}/pages/${pageNo}`,{"headers": headers})
                .then((res) => {
                  this.setCookie("portfolios",res.data.content,1)
                  return res.data
                })
  },


  async getPortfolio(portfolioId) {
    const headers = {
      "Content-Type": "application/json",
      accountEmail: "",
      accountAuth: "" 
    }
    await getLoginUserInfo()
      .then((LoginUserInfo) => {
        if (LoginUserInfo) {
          headers.accountEmail = LoginUserInfo.email
          headers.accountAuth = LoginUserInfo.auth
        }
      })
    return axios.get(`${portfolioUrl}/${portfolioId}`,{"headers": headers})
                .then((res) => {                
                  return res.data
              })
  },

  postPortfolio(portfolio) { 
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    portfolio.accountEmail = loginUser.accountEmail
    portfolio.accountName = loginUser.accountName
    return axios.post(portfolioUrl, portfolio, { "headers": headers })          
                .then((res) => {  
                  return res
                })
  },

  putPortfolio(portfolio) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    portfolio.accountEmail = loginUser.accountEmail
    portfolio.accountName = loginUser.accountName
    return axios.put(`${portfolioUrl}/${portfolio.portfolioId}`,portfolio,{ "headers": headers })
                .then((res) => {                  
                  return res.data
              })
  },

  deletePortfolio(portfolioId) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.delete(`${portfolioUrl}/${portfolioId}`,{ "headers": headers })
                .then((res) => {                  
                  return res.data
              })
  },

  // Portfolio Comment CRUD
  
  postPortfolioComment(portfolioId, portfolioCommentContent) {
    const portfolioComment = {
      "portfolioId": portfolioId,
      "accountEmail": loginUser.accountEmail,
      "accountName": loginUser.accountName,
      "portfolioCommentContent": portfolioCommentContent,
    }
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.post(`${portfolioUrl}/${portfolioId}/comments`,  portfolioComment, { "headers": headers })
                .then((res) => {
                  return res
                })
  }, 
  
  putPortfolioComment(portfolioId, portfolioComment) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.put(`${portfolioUrl}/${portfolioId}/comments/${portfolioComment.portfolioCommentId}`, portfolioComment, { "headers": headers })
                .then((res) => {
                  return res.data
                })
  },

  deletePortfolioComment(portfolioId, portfolioCommentId) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.delete(`${portfolioUrl}/${portfolioId}/comments/${portfolioCommentId}`, { "headers": headers })
                .then((res) => {
                  return res.data
                })
  },
  

  // Posts CRUD

  getPostsCount() {
    return axios.get(`${postUrl}/count`)
                .then((res) => {                  
                  return res.data.countPosts
              })
  },

  async getPostsBest() {
    return axios.get(`${postUrl}/best`)
                .then((res) => {
                  return res.data.content
                })
  },
  
  async loadPosts(pageNo) { 
    const headers = {
      "Content-Type": "application/json",
      accountEmail: "",
      accountAuth: "" 
    }
    await getLoginUserInfo()
      .then((LoginUserInfo) => {
        if (LoginUserInfo) {
          headers.accountEmail = LoginUserInfo.email
          headers.accountAuth = LoginUserInfo.auth
        }
      })

    return axios.get(`${postUrl}/pages/${pageNo}`,{"headers": headers})
                .then((res) => {
                  this.setCookie("posts",res.data.content,1)
                  return res.data
                })
  },

  async getPost(postId) {
    const headers = {
      "Content-Type": "application/json",
      accountEmail: "",
      accountAuth: "" 
    }
    await getLoginUserInfo()
      .then((LoginUserInfo) => {
        if (LoginUserInfo) {
          headers.accountEmail = LoginUserInfo.email
          headers.accountAuth = LoginUserInfo.auth
        }
      })
    return axios.get(`${postUrl}/${postId}`,{"headers": headers})
                .then((res) => {                
                  return res.data
              })
  },

  postPost(post) { 
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    post.accountEmail = loginUser.accountEmail
    post.accountName = loginUser.accountName
    return axios.post(postUrl, post, { "headers": headers })          
                .then((res) => {                  
                  return res
                })
  },

  putPost(post) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    post.accountEmail = loginUser.accountEmail
    post.accountName = loginUser.accountName
    return axios.put(`${postUrl}/${post.postId}`,post,{ "headers": headers })
                .then((res) => {                  
                  return res.data
              })
  },

  deletePost(postId) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.delete(`${postUrl}/${postId}`,{ "headers": headers })
                .then((res) => {                  
                  return res.data
              })
  },

  // Post Comment CRUD
  
  postPostComment(postId, postCommentContent) {
    const postComment = {
      "postId": postId,
      "accountEmail": loginUser.accountEmail,
      "accountName": loginUser.accountName,
      "postCommentContent": postCommentContent,
    }
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.post(`${postUrl}/${postId}/comments`,  postComment, { "headers": headers })
                .then((res) => {
                  return res
                })
  }, 
  
  
  
  putPostComment(postId, postComment) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.put(`${postUrl}/${postId}/comments/${postComment.postCommentId}`, postComment, { "headers": headers })
    .then((res) => {
      return res.data
    })
  },
  
  deletePostComment(postId, postCommentId) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.delete(`${postUrl}/${postId}/comments/${postCommentId}`, { "headers": headers })
    .then((res) => {
      return res.data
    })
  },
 
  // 기타
  parsePortfolio(portfolioContent) {
    let contents_start = []
    let contents_end = []
    let contents = []
    let images_start = []
    let images_end = []
    let images = []
    
    let i = 0
    while (i<portfolioContent.length) {
      if (portfolioContent.slice(i,i+5) === "![img") {
        contents_end.push(i)
        images_start.push(i+7)  
        i += 5
        while (portfolioContent[i] != ')') {
          i++
        }
        images_end.push(i)
        contents_start.push(i+1)
      }
      i++
    }
    contents_end.push(portfolioContent.length -1)
  
    for (var j=0;j<contents_start.length;j++) {
      contents.push(portfolioContent.slice(contents_start[j],contents_end[j+1]))
      images.push(portfolioContent.slice([images_start[j]],images_end[j]))
    }

    return {contents,images}
  },
  
  setCookie(name, value, exp) {
    var date = new Date()
    date.setTime(date.getTime() + exp*24*60*60*1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
  },
}