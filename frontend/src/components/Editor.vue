<template>
  <div class="container my-3">
    <!-- Title -->
    <div class="form-group">
      <label for="title">Title</label>
      <input v-model="article.article_title" type="text" class="form-control" id="title" 
            :placeholder="article.article_title ? article.article_title : 'Enter Title'"> 
    </div>
    <!-- Image Upload compo -->
    <div class="form-group">
      <label for="image">Image</label>
      <ImgurUpload id="image" 
                  :imgSrc="article.article_imgsrc ? article.article_imgsrc : 'http://dy.gnch.or.kr/img/no-image.jpg'" 
                  @transmitData="mergeData">
      </ImgurUpload>
    </div>
    <!-- Content Markdown compo -->
    <div class="form-group">
      <label for="content">Content</label>
      <textarea id="content" :value="article.article_content" @input="update" class="form-control" style="height: 20vw;" ></textarea>
      <label for="preview">Preview</label>
      <div id="preview" v-html="compiledMarkdown" ></div>
    </div>
    <!-- Buttons -->
    <div class="my-3" >
      <div class="d-flex justify-content-center">
        <!-- 목록 -->
        <router-link :to="{ name: 'portfoliolistpage'}" class="btn btn-outline-secondary mx-1"><i class="fas fa-arrow-left"></i></router-link>
        <!-- 저장 -->
        <button class="btn btn-outline-info mx-1" @click="postArticle"><i class="far fa-save"></i></button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ImgurUpload from '../components/ImgurUpload'
import Inner from '../apis/Inner'

export default {
	name: 'Editor',
	components: {
    ImgurUpload
  },
  data() {
    return {
      url: "http://localhost:8080/api/bears/articles/portfolios/",
      article : {
        article_no: "",
        article_title : "",
        article_content: "",
        article_imgsrc: "",
        article_category_id: "",
        account_no: 1, // default
        account_name: "Anonymous", // default
      },  
    }
  }
  ,
  computed: {
    compiledMarkdown: function () {
      return marked(this.article.article_content, { sanitize: true })
    }
  },
  mounted() {
    if ( this.$route.params.article_no ) { // 파라미터 있는  채로 넘어 오는 것은 수정이니까
      Inner.getArticle(`${this.url}${this.$route.params.article_no}`)
            .then((data) => {
              console.log(data)
              this.article = data
              console.log("바인딩 잘 됐니?")
              console.log(this.article)
            })
    }
  },
  methods: {
    update: _.debounce(function (e) {
      this.article.article_content = e.target.value
    }, 300),

    mergeData (imgsrc) {
      this.article.article_imgsrc = imgsrc
      console.log("에밋 잘 되니?")
      console.log(this.article.article_imgsrc)
    },

    postArticle () { // TODO : 이것도 따로 빼야 함 ㅠㅠ 나중에 함수들은 다시 정리
      console.log(this.article)
      if ( !this.$route.params.article_no ) { // 새로 만드는 경우
        axios.post(this.url,this.article)
            .then((res) => {
              console.log(res.data)
              this.$router.push({ name: 'portfoliolistpage', params: {}} )
            })
      } else { // 업데이트의 경우
        axios.put(`${this.url}${this.$route.params.article_no}`,this.article)
            .then((res) => {
              console.log(res.data)
              this.$router.push({ name: 'portfoliolistpage', params: {}} )
            })
      }
    }
  }
}
</script>

<style>
.editor-textarea{
  width : 100%;
  height: 20vw;
  min-height: 180px;
  resize: none;
}
</style>

