<template>
    <div class ="container">
      <div class="jumbotron bg-white">
        <h1 class="display-4">{{ articleData.article_title }}</h1>
        <p class="lead">by {{ articleData.account_name }} at {{ articleData.article_write_date}} </p>
        <hr class="my-4">
        <div class="row justify-content-around">
          <img :src="articleData.article_imgsrc" class="col-5"/>
          <div class="col-5" v-html="compiledMarkdown" ></div>       
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Article',
  props: {
    articleData : { }
  },
  computed: {
    compiledMarkdown: function () {
      return marked(this.articleData.article_content, { sanitize: true })
    }
  },
}
</script>

<style>


</style>
