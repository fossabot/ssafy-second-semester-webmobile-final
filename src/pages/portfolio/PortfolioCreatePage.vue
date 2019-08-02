<template>
  <div class="container mt-5 pt-5">
    <PortfolioEditComp></PortfolioEditComp>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
import PortfolioEditComp from '../../components/portfolio/PortfolioEditComp'

export default {
  name: 'PortfolioCreatePage',
  components: {
    PortfolioEditComp,
  },
  methods: {
    ...mapMutations('portfolio',['setNewPortfolio'])
  },
  created() {
    this.setNewPortfolio()
  }

}
</script>

<style>

</style>
