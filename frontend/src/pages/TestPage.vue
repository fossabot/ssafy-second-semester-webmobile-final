<template>
  <div class="container">
    <button @click="sayHello" class="btn btn-danger">안녕이라고 내게 말하지 마</button>
    <!-- getArticles -->
    <div>
      <h1>포트폴리오 목록</h1>
      Article_Category_ID : {{ testArticleCategory }}
      <div v-for="article in testArticles">
        {{ article.article_no }} / {{ article.article_title }} / {{ article.article_content }}
      </div>
    </div>
    <!-- getArticle -->
    <div>
      <h1>포트폴리오 1번</h1>
      {{ testArticle }}
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "TestPage",
  computed: {
    ...mapState('articles', {
      testArticleCategory: state => state.articleCategoryId,
      testArticles: state => state.articles,
      testArticle: state => state.article,
      testIsAuthor:  state => state.isAuthor,
    })
  },
  methods: {
    ...mapActions('articles', {
      getTestArticles: 'getArticles',
      getTestArticle: 'getArticle',
      }),

    sayHello() {
      console.log("Hello")
      
    }
  },
  mounted () { 
    this.getTestArticles('portfolios') // 단일 객체니까 이렇게만 넘겨도 됨
    this.getTestArticle({
      categoryName: 'portfolios',
      articleNo: 1
      })

  },
}

</script>

<style>

</style>
