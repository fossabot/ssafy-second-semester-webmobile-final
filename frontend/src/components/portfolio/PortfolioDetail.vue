<template>
  <div class ="container">
    <div class="jumbotron bg-white">
      <h1 class="display-4">{{ portfolio.portfolioTitle }}</h1>
      <p class="lead">by {{ portfolio.accountName }} at {{ portfolio.portfolioCreatedAt }} </p>
      <hr class="my-4">  
      <div v-html="compiledMarkdown" ></div>       
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'PortfolioDetail',
  computed: {
    ...mapState('portfolio',['portfolio']),
    compiledMarkdown() {      
      return marked(this.portfolio.portfolioContent, { sanitize: true })
    },
  },
}
</script>

<style>

</style>
