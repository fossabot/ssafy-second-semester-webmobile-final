<template>
  <div class="container mt-5 pt-5">
    <PortfolioEdit></PortfolioEdit>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
import PortfolioEdit from '../../components/portfolio/PortfolioEdit'

export default {
  name: 'PortfolioUpdatePage',
  components: {
    PortfolioEdit,
  },
  methods: {
    ...mapActions('portfolio',['getPortfolio'])
  },
  async created() {
    await this.getPortfolio(this.$route.params.portfolioId) // methods는 created 될 때도 들어오지만 computed는 created 될 때 안들어 오는듯..
  }

}
</script>

<style>

</style>
