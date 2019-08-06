<template>
  <div class="container mt-5 pt-5">
    <PortfolioEdit></PortfolioEdit>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
import PortfolioEdit from '../../components/portfolio/PortfolioEdit'

export default {
  name: 'PortfolioCreatePage',
  components: {
    PortfolioEdit,
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
