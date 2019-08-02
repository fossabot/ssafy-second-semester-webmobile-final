<template>
  <div class="container mt-5 pt-5">
    <PortfolioEditComp></PortfolioEditComp>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
import PortfolioEditComp from '../../components/portfolio/PortfolioEditComp'

export default {
  name: 'PortfolioUpdatePage',
  components: {
    PortfolioEditComp,
  },
  computed: {
    ...mapActions('portfolio',['getPortfolio'])
  },
  async created() {
    console.log(this.$route.params.portfolioId)
    await this.getPortfolio(this.$route.params.portfolioId)
  }

}
</script>

<style>

</style>
