<template>
  <div class="containter mt-5 pt-5">
    <Title title="portfolios"></Title>
    <router-link v-if="loginCheck && accountAuth != 3" :to="{ name: 'PortfolioCreatePage'}" class="btn btn-sm btn-outline-info border-0">
      새 포트폴리오 작성&nbsp; <i class="fas fa-pen"></i>
    </router-link>
    <PortfolioListComp class="container"></PortfolioListComp>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import Title from '@/components/common/Title'
import PortfolioListComp from '@/components/portfolio/PortfolioListComp'

export default {
  name: 'PortfolioListPage',
  components: {
    Title,
    PortfolioListComp,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth'])
  },
  methods: {
    ...mapActions('portfolio', ['getPortfolios'])
  },
  created() {
    this.getPortfolios()
  }
}
</script>

<style>

</style>
