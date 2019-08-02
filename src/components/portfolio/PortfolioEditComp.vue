<template>
  <div class="container" style="text-align: start">
    <!-- Title -->
    <div class="form-group">
      <label for="title">Title</label>
      <input v-model="newPortfolio.portfolioTitle" type="text" class="form-control" id="title" 
            :placeholder="newPortfolio.portfolioTitle ? newPortfolio.portfolioTitle : 'Enter Title'"> 
    </div>
    <!-- Image Upload compo -->
    <div class="form-group">
      <label for="image">Image</label>
      <Imgur id="image" 
              :imageUrl="(newPortfolio.portfolioThumbnailUrl) ? newPortfolio.portfolioThumbnailUrl : 'http://dy.gnch.or.kr/img/no-image.jpg' "
              @uploadImageUrl="uploadPortfolioThumbnailUrl"></Imgur>
    </div>
    <!-- Content Markdown compo -->
    <div class="form-group">
      <label for="content">Content</label>
      <textarea id="content" :value="newPortfolio.portfolioContent" @input="portfolioContentUpdate" class="form-control" style="height: 20vw;" ></textarea>
      <label for="preview">Preview</label>
      <div id="preview" v-html="compiledMarkdown" ></div>
    </div>
    <!-- Buttons -->
    <div class="my-3" >
      <div class="d-flex justify-content-center">
        <!-- 목록 -->
        <router-link :to="{ name: 'PortfolioListPage'}" class="btn btn-outline-secondary mx-1"><i class="fas fa-arrow-left"></i></router-link>
        <!-- 저장 -->
        <button class="btn btn-outline-info mx-1" @click="postPutPortfolio"><i class="far fa-save"></i></button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import mainServices from '../../apis/mainservice/mainServices'
import Imgur from '../common/Imgur'
import Title from '../common/Title'

export default {
  name: 'PortfolioEditComp',
  components: {
    Imgur,
    Title,
  },
  data() {
    return {
      newPortfolio: {}
    }
  },
  computed: {
    ...mapState('portfolio',['portfolio']),
    compiledMarkdown: function () {
      return marked(this.newPortfolio.portfolioContent, { sanitize: true })
    }
  },
  created() {
    this.newPortfolio = this.portfolio // 복사 
  },
  methods: {
    portfolioContentUpdate: _.debounce(function (e) {
      this.newPortfolio.portfolioContent = e.target.value
    }, 300),

    uploadPortfolioThumbnailUrl(imageUrl) {
      this.newPortfolio.portfolioThumbnailUrl = imageUrl
    },

    async postPutPortfolio () { 
      if ( !this.$route.params.portfolioId ) { // 새로 만드는 경우
        await mainServices.postPortfolio(this.newPortfolio)
        console.log(this.portfolio)
      } else { // 업데이트의 경우
        /*axios.put(`${this.url}${this.$route.params.article_no}`,this.article)
            .then((res) => {
              console.log(res.data)
              this.$router.push({ name: 'portfoliolistpage', params: {}} )
            })
        */
      }
    }
  }
}
</script>

<style>

</style>
