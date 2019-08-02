import axios from 'axios'

export default {
  postImgur(imageFile) {
    const url =  "https://api.imgur.com/3/image/"
    const clientId = "d106d6638d11f39"
    
    let formData = new FormData();
    formData.append("image", imageFile); 
    
    return axios({ 
      method: 'POST',
      url: url,
      data: formData,
      headers: {
      Authorization: "Client-ID " + clientId 
      },
      mimeType: 'multipart/form-data'
      })
      .then(res => {
        return res.data.data.link
      })
      .catch(e => {
        console.log(e)
    })
      
  }
}
