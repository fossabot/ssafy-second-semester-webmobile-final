import axios from 'axios'
import store from '../../store/store.js'

// 아직 전부 예외 처리 달지 않은 상태.. 디테일 작업 필요 

let loginUser = store.state.account // 현재 로그인 유저, 권한체크 및 기타 등등
// 이거 왜안되는지 나중에 알아보자.
const portfolioUrl = "http://70.12.246.106:9090/api/bears/portfolios"  


export default {
  // 이 함수는 근데 이미 account.js에 변수화 되어 있어서 vue 특성상 변수화 된 애를 쓰는게 편함 버리는 함수
  isLogin() {
    if (loginUser.loginCheck) {
      return true
    } else {
      return false
    }
  },

  // 근데 얘네도 걍 state 에서 바로 접근하는게 나을듯 아니지 얘는 근데 인자가 있네...
  isAuthor(authorEmail) { // 이건 프론트에서 버튼 보이게 할지 말지 
    if (loginUser.accountEmail === authorEmail) {
      return true
    } else {
      return false
    }
  },

  // 얘는 스테이트 직접접근이 나을듯
  isTeam() { // 나중에 글 작성할 때 필요한 함수
    if (loginUser.accountAuth === 3) { // 방문자
      return false
    } else {
      return true
    }
  },

  isAdmin() { // 얘도
    if (loginUser.accountAuth === 1) {
      return true
    } else {
      return false
    }
  },

  // Portfolios CRUD
  getPortfolios() {
    return axios.get(portfolioUrl)
                .then((res) => {                  
                  return res.data.content 
              })
  },

  getPortfolio(portfolioId) {
    return axios.get(`${portfolioUrl}/${portfolioId}`)
                .then((res) => {                
                  return res.data
              })
  },

  postPortfolio(portfolio) { 
    // 새 글 작성은 작성자 체크 할 필요 없이 바로 현재 로그인 유저로 작성하면 된다. 애초에 로그인한 유저에게만 포스트 버튼이 보이므로
    // but 만일을 위해 여기도 로그인 안했을시를 분기해서 에러 핸들링하자.. 나중에^^
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    portfolio.accountEmail = loginUser.accountEmail
    portfolio.accountName = loginUser.accountName
    return axios.post(portfolioUrl, portfolio, { "headers": headers })          
                .then((res) => {                  
                  return res.data
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
    // 새 댓글 작성은 작성자 체크 할 필요 없이 바로 현재 로그인 유저로 작성하면 된다. 애초에 로그인한 유저에게만 포스트 버튼이 보이므로
    // but 만일을 위해 여기도 로그인 안했을시를 분기해서 에러 핸들링하자.. 나중에^^
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
                  return res.data
                })
  }, 
  
  // 어차피 프론트 단에서 작성자 식별 및 로그인 식별하고 요청이 실행되므로 여기서 권한 담아서 보낸다.
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

  // 어차피 프론트 단에서 작성자 식별 및 로그인 식별하고 요청이 실행되므로 여기서 권한 담아서 보낸다.
  deletePortfolioComment(portfolioId, portfolioCommentId) {
    const headers = {
      "Content-Type": "application/json",
      "accountEmail": loginUser.accountEmail,
      "accountAuth": loginUser.accountAuth
    }
    return axios.delete(`${portfolioUrl}/${portfolioId}/comments/${portfolioCommentId}`, {}, { "headers": headers })
                .then((res) => {
                  return res.data
                })
  }
}
