<template>
  <div class="container">
    <!-- Image Preview -->
    <img :src="imageUrl" style="width: 100%; height: auto;" class="rounded">
    <br>
    <!-- Image File Input -->
    <div class="custom-file" style="width: 100%;">
      <input class="custom-file-input" @change="uploadImageUrl" type="file" id="file" ref="file" width="50%">
      <label class="custom-file-label imgur-text-ellipsis" for="file">{{ (imageFile) ? imageFile.name : "Choose File"}}</label>
    </div>
  </div>
</template>

<script>
import imgur from '../../apis/imgur/imgur.js'

export default {
  name: 'Imgur',
  data() {
    return {
      imageFile: null,
    }
  },
  props: {
    imageUrl: {type:String}
  },
  methods: {
    uploadImageUrl () {
      this.imageFile = this.$refs.file.files[0]
      imgur.postImgur(this.imageFile)
          .then((imageUrl)=> {
            this.imageUrl = imageUrl
            this.$emit('uploadImageUrl',imageUrl)
          })   
    },
  }
}
</script>

<style>
.imgur-text-ellipsis {
    overflow:hidden; 
    white-space:nowrap;
    text-overflow:ellipsis;
    text-align: start;

  }
</style>
