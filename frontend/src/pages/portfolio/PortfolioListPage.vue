<template>
  <div class="containter mt-5 pt-5">
    <Title title="portfolios" class="mt-3"></Title>
    <router-link v-if="loginCheck && accountAuth != 3" :to="{ name: 'PortfolioCreatePage'}" class="btn btn-sm btn-outline-info border-0">
      새 포트폴리오 작성&nbsp; <i class="fas fa-pen"></i>
    </router-link>
    <PortfolioList class="container"></PortfolioList>
    <div v-if="totalPortfolioPages != 1 && pageNo < totalPortfolioPages" class="row justify-content-center mb-4">
      <button type="button" class="btn btn-outline-success my-3" @click="addPortfolios">
        <i class="fas fa-plus"></i> 포트폴리오 더보기
      </button>
    </div>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import Title from '@/components/common/Title'
import PortfolioList from '@/components/portfolio/PortfolioList'

export default {
  name: 'PortfolioListPage',
  data() {
    return {
      pageNo : 1,
    }
  },
  components: {
    Title,
    PortfolioList,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth']),
    ...mapState('portfolio',['totalPortfolioPages'])
  },
  methods: {
    ...mapActions('portfolio', ['loadPortfolios']),
    async addPortfolios() {
      this.pageNo += 1
      await this.loadPortfolios(this.pageNo)
    }
  },
  async created() {
    await mapState('account',['loginCheck','accountAuth'])
    await this.loadPortfolios(this.pageNo)
  }
}
</script>

<style>

</style>
