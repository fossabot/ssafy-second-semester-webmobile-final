import axios from 'axios'

export default {
  postImgur(imgFile) {
    const url =  "https://api.imgur.com/3/image/"
    const clientId = "d106d6638d11f39"
    
    let formData = new FormData();
    formData.append("image", imgFile); 
    
    return axios({ // 객체 방식의 axios. 이것도 나중에 고쳐보자.
      method: 'POST',
      url: url,
      data: formData,
      headers: {
      Authorization: "Client-ID " + clientId 
      },
      mimeType: 'multipart/form-data'
      })
      .then(res => {
        console.log(res)
        return res.data.data.link
      })
      .catch(e => {
        console.log(e)
    })
      
  }
}
