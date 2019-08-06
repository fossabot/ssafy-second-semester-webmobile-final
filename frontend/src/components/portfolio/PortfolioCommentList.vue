<template>
  <div class="container mb-5">
    <PortfolioCommentCreate v-if="loginCheck"></PortfolioCommentCreate>
    <div v-for="portfolioComment in portfolio.portfolioComments">
      <PortfolioComment :portfolioComment="portfolioComment"></PortfolioComment>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import PortfolioCommentCreate from './PortfolioCommentCreate'
import PortfolioComment from './PortfolioComment'

export default {
  name: 'PortfolioCommentList',
  components: {
    PortfolioCommentCreate,
    PortfolioComment,
  },
  computed: {
    ...mapState('portfolio',['portfolio']),
    ...mapState('account',['loginCheck'])
  },
}
</script>

<style>

</style>
