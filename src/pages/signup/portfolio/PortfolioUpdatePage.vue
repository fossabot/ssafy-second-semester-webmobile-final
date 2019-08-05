<template>
  <div class="container mt-5 pt-5">
    <PortfolioEditComp></PortfolioEditComp>
  </div>
</template>

<script>
import {mapState,mapActions} from 'vuex'
import PortfolioEditComp from '../../components/portfolio/PortfolioEditComp'

export default {
  name: 'PortfolioUpdatePage',
  components: {
    PortfolioEditComp,
  },
  computed: {
    ...mapState('portfolio',['portfolio'])
  },
  methods: {
    ...mapActions('portfolio',['getPortfolio'])
  },
  async created() {
    // await this.getPortfolio(this.$route.params.portfolioId)
    
    console.log("Update")        
    console.log(this.portfolio)
  }

}
</script>

<style>

</style>
