import axios from 'axios'

export default {
  postImgur(url,clientId,imgFile) {
    let formData = new FormData();
    formData.append("image", imgFile); //required
    
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
        console.log(res)
        return res.data.data.link
      })
      .catch(e => {
        console.log(e)
    })
      
  }
}
