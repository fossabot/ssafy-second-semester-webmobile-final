import axios from 'axios'

export default {
    getArticle(url) {
        var data =  axios.get(url)
                  .then((res) => {                  
                  console.log(res)
                  console.log("여기는 inner")
                  console.log("data : " + res.data)
                  console.log("header  :" + res.headers)
                  console.log("status  :" + res.status)
                  return res.data
              })
        return data
    },

    getRoot() {
        // TODO : 루트 url 자동생성 하는 함수
    }
}
