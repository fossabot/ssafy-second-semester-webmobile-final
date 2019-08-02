<template>
  <div class="container">
    <!-- Image Preview -->
    <img :src="(portfolio.portfolioThumbnail) ? portfolio.portfolioThumbnail : 'http://dy.gnch.or.kr/img/no-image.jpg' " style="width: 30%; height: auto;" class="rounded">
    <br>
    <!-- Image File Input -->
    <div class="custom-file" style="width: 30%;">
      <input class="custom-file-input" @change="postImgur" type="file" id="file" ref="file" width="50%">
      <label class="custom-file-label imgur-text-ellipsis" for="file">{{ (imageFile) ? imageFile.name : "Choose File"}}</label>
    </div>
  </div>
</template>

<script>
import {mapState, mapMutations} from 'vuex'
import imgur from '../../apis/imgur/imgur.js'

export default {
  name: 'Imgur',
  data() {
    return {
      imageFile: null,
    }
  },
  computed: {
    ...mapState('portfolio',['portfolio'])
  },
  methods: {
    ...mapMutations('portfolio',['setPortfolioThumbnail']),
    postImgur () {
      this.imageFile = this.$refs.file.files[0]
      imgur.postImgur(this.imageFile)
          .then((imageSrc)=> {
            this.setPortfolioThumbnail(imageSrc)
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
