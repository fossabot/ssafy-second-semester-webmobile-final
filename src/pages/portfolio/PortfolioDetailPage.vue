<template>
  <div class="container mt-5 pt-5">
    <PortfolioDetailComp></PortfolioDetailComp>
    <hr style="border: solid 0.5px lightsteelblue">
    <PortfolioCommentListComp></PortfolioCommentListComp>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import PortfolioDetailComp from '@/components/portfolio/PortfolioDetailComp'
import PortfolioCommentListComp from '@/components/portfolio/PortfolioCommentListComp'

export default {
  name: 'PortfolioDetailPage',
  components: {
    PortfolioDetailComp,
    PortfolioCommentListComp,
  },
  computed: {
  },
  methods: {
    ...mapActions('portfolio',['getPortfolio'])
  },
  async created() {
    await this.getPortfolio(this.$route.params.portfolioId)
  }

}
</script>

<style>

</style>
