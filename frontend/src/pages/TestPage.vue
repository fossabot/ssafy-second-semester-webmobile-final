<template>
  <div class="container">
    <h1>으앙 왜 안나와 ㅠㅠ</h1>
    {{ testArticleCategory }}
    <div v-for="article in testArticles">
      {{ article }}
    </div>
    <button class="btn btn-danger" @click="fuck">fuck</button>
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
      testAritcle: state => state.article,
      testIsAuthor:  state => state.isAuthor,
    })
  },
  methods: {
    ...mapActions('articles', {
      getTestArticles: 'getArticles' 
      }),
    fuck() {
      console.log("fuck")
      console.log(this.testArticleCategory)
      console.log(this.testArticles)
    }
  },
  mounted () { 
    console.log('getTestArticles 실행시켜바아아아')
    this.getTestArticles('portfolios')

  },
}

</script>

<style>

</style>
