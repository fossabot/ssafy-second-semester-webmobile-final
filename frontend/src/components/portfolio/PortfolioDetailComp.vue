<template>
  <div class ="container">
    <div class="jumbotron bg-white">
      <h1 class="display-4">{{ portfolio.portfolioTitle }}</h1>
      <p class="lead">by {{ portfolio.accountName }} at {{ portfolio.portfolioCreatedAt }} </p>
      <hr class="my-4">
      <div class="row justify-content-around">
        <img :src="portfolio.portfolioThumbnailUrl ? portfolio.portfolioThumbnailUrl : 'https://source.unsplash.com/random/1000x1000' " class="col-5"/>
        <div class="col-5" v-html="compiledMarkdown" ></div>       
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'PortfolioDetailComp',
  computed: {
    ...mapState('portfolio',['portfolio']),
    compiledMarkdown() {
      return marked(this.portfolio.portfolioContent, { sanitize: true })
    },
  },
  methods: {

  },
  
}
</script>

<style>

</style>
