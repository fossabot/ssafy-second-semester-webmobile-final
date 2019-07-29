<template>
  <div class="container">
    <!-- Image Preview -->
    <img :src="imgSrc" style="width: 30%; height: auto;" class="rounded">
    <br>
    <!-- Image File Input -->
    <div class="custom-file" style="width: 30%;">
      <input class="custom-file-input" @change="FileUploading" type="file" id="file" ref="file" width="50%">
      <label class="custom-file-label imgur-text-ellipsis" for="file">{{ (imgFile) ? imgFile.name : "Choose File"}}</label>
    </div>
  </div>
</template>

<script>
import Outer from "../apis/Outer"
import axios from 'axios'

export default {

  name: "ImgurUpload",

  data() {
    return {
      imgFile: "",
      url: "https://api.imgur.com/3/image/",
      clientId: "d106d6638d11f39"
      }
  },

  props: {
    imgSrc: ""
  },

  methods : {
    FileUploading () {
      this.imgFile = this.$refs.file.files[0]
      console.log(this.imgFile)
      Outer.postImgur(this.url,this.clientId,this.imgFile)
          .then((link)=> {
            this.imgSrc = link
            // TODO : 함수들은 전부 로직 좀 따로 빼고 정리하자.
            console.log("에밋전")
            this.$emit('transmitData',this.imgSrc)
          })   
    },
  },

}

</script>

<style>
  .imgur-text-ellipsis {
    overflow:hidden; 
    white-space:nowrap;
    text-overflow:ellipsis;

  }

</style>
