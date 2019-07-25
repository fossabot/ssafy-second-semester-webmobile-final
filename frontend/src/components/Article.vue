<template>
    <div class ="container">
      <div class="jumbotron bg-white">
        <h1 class="display-4">{{ article.article_title }}</h1>
        <p class="lead">by {{ article.account_name }} at {{ article.article_write_date}} </p>
        <hr class="my-4">
        <div class="row justify-content-around">
          <img :src="article.article_imgsrc" class="col-5"/>
          <div class="col-5" v-html="compiledMarkdown" ></div>       
        </div>
      </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'Article',
  computed: {
    ...mapState('articles',['categoryName','articleNo','article']),
    compiledMarkdown: function () {
      return marked(this.article.article_content, { sanitize: true })
    },
  },
  methods: {
    ...mapActions('articles',['getArticle'])
  },
  created () { 
    this.getArticle({
      categoryName: this.categoryName,
      articleNo: this.articleNo
    })

  }
}
</script>

<style>
</style>
