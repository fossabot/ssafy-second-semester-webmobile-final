import axios from 'axios'

// 아직 전부 예외 처리 달지 않은 상태.. 디테일 작업 필요 07/23

const articleUrl  = "http://localhost:8080/api/bears/articles"
const commentUrl = "http://localhost:8080/api/bears/articles" // 이거 2개는 일단 분기...흠


export default {

  // Article CRUD
  getArticles(categoryName) {

    const url = `${articleUrl}/${categoryName}`
    return axios.get(url)
                .then((res) => {                  
                return res.data
              })
  },

  getArticle(categoryName, articleNo) {
    
    const url = `${articleUrl}/${categoryName}/${articleNo}`
    return axios.get(url)
                .then((res) => {                  
                return res.data
              })
  },

  postArticle(categoryName, article) {
    
    const url = `${articleUrl}/${categoryName}`
    return axios.post(url,article)
                .then((res) => {                  
                return res.data
              })
  },

  putArticle(categoryName, article) {
    
    const url = `${articleUrl}/${categoryName}/${article.article_no}`
    return axios.put(url,article)
                .then((res) => {                  
                return res.data
              })
  },

  deleteArticle(categoryName, articleNo) {

    const url = `${articleUrl}/${categoryName}/${articleNo}`
    return axios.delete(url)
                .then((res) => {                  
                return res.data
              })
  },

  // Comment CRUD
  getComments(categoryName, articleNo) {

    const url = `${commentUrl}/${categoryName}/${articleNo}/comments`
    return axios.get(url)
                .then((res) => {
                  return res.data
                })
  },

  getComment(categoryName, articleNo, commentNo) {

    const url = `${commentUrl}/${categoryName}/${articleNo}/comments/${commentNo}`
    return axios.get(url)
                .then((res) => {
                  return res.data
                })
  },

  postComment(categoryName, articleNo, comment) {

    const url = `${commentUrl}/${categoryName}/${articleNo}/comments`
    return axios.post(url, comment)
                .then((res) => {
                  return res.data
                })
  },

  putComment(comment) {

    const url = `${commentUrl}/${categoryName}/${articleNo}/comments/${comment.comment_no}`
    return axios.put(url, comment)
                .then((res) => {
                  return res.data
                })
  },

  deleteComment(categoryName, articleNo, commentNo) {

    const url = `${commentUrl}/${categoryName}/${articleNo}/comments/${commentNo}`
    return axios.delete(url)
                .then((res) => {
                  return res.data
                })
  }
}