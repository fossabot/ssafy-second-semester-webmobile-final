<template>
  <div class="container">
    <div style="height:100px"></div>
    <!-- Detail compo -->
    <Article :article="article"></Article>
    <!-- Buttons -->
    <div class="d-flex justify-content-center">
      <!-- 목록 -->
      <router-link :to="{ name: 'portfoliolistpage'}" class="btn btn-outline-secondary mx-1" @click="cleanArticle">
        <i class="fas fa-arrow-left"></i>
      </router-link>
      <!-- 수정 -->
      <router-link :to="{ name: 'editarticle', params: { article_no: articleNo } }" class="btn btn-outline-info mx-1">
        <i class="far fa-edit"></i>
      </router-link>
      <!-- 삭제 -->
      <button @click="deleteArticle" class="btn btn-outline-danger mx-1">
        <i class="far fa-trash-alt"></i>
      </button>
    </div>
    
    <hr class="my-5">
    
    <!-- Comments -->
    <label for="comments">Comments</label>
    <!-- Comment List compo -->
    <CommentList :commentsUrl="commentsUrl" :articleNo="articleNo" id="comments" class="mb-5"></CommentList>
  </div>
</template>

<script>
import mainServices from '../apis/mainServices'
import { mapState, mapActions, mapMutations } from 'vuex'
import Article from '../components/Article'
import CommentList from '../components/CommentList'

export default {
	name: 'DetailArticlePage',
  components : {
    Article,
    CommentList,
  },
  data() {
    return {
      commentsUrl: `http://localhost:8080/api/bears/articles/portfolios/${this.$route.params.article_no}/comments`,
    }
  },
  computed: {
    ...mapState('articles', ['categoryName','articleNo']),
  },
  methods: {
    ...mapMutations('articles', ['setArticleNo','cleanArticle']),
    deleteArticle () {
      mainServices.deleteArticle(this.categoryName, this.articleNo)
                  .then(
                    this.$router.push({ name: 'portfoliolistpage'})
                  )
    }
  },
  created() {
    this.setArticleNo(this.$route.params.article_no)
  }
}
</script>