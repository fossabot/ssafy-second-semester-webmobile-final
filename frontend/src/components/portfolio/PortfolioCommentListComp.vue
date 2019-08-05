<template>
  <div class="container mb-5">
    <PortfolioCommentCreateComp v-if="loginCheck"></PortfolioCommentCreateComp>
    <div v-for="portfolioComment in portfolio.portfolioComments">
      <PortfolioCommentComp :portfolioComment="portfolioComment"></PortfolioCommentComp>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import PortfolioCommentCreateComp from './PortfolioCommentCreateComp'
import PortfolioCommentComp from './PortfolioCommentComp'

export default {
  name: 'PortfolioCommentListComp',
  components: {
    PortfolioCommentCreateComp,
    PortfolioCommentComp,
  },
  computed: {
    ...mapState('portfolio',['portfolio']),
    ...mapState('account',['loginCheck'])
  },
}
</script>

<style>

</style>
