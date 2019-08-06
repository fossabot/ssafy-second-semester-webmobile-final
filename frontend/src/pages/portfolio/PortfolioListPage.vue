<template>
  <div class="containter mt-5 pt-5">
    <Title title="portfolios"></Title>
    <router-link v-if="loginCheck && accountAuth != 3" :to="{ name: 'PortfolioCreatePage'}" class="btn btn-sm btn-outline-info border-0">
      새 포트폴리오 작성&nbsp; <i class="fas fa-pen"></i>
    </router-link>
    <PortfolioList class="container"></PortfolioList>
    <div v-if="isMore" class="row justify-content-center">
      <button type="button" class="btn btn-outline-success my-3" @click="addPortfolios">
        <i class="fas fa-plus"></i> 포트폴리오 더보기
      </button>
    </div>
  </div>
</template>

<script>
// TODO : 다 불러오면 더보기 버튼 사라지게 만드는 기능 구현
import {mapState, mapActions} from 'vuex'
import Title from '@/components/common/Title'
import PortfolioList from '@/components/portfolio/PortfolioList'

export default {
  name: 'PortfolioListPage',
  data() {
    return {
      pageNo : 1,
      isMore: true,
    }
  },
  components: {
    Title,
    PortfolioList,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth']),
  },
  methods: {
    ...mapActions('portfolio', ['loadPortfolios']),
    async addPortfolios() {
      this.pageNo += 1
      await this.loadPortfolios(this.pageNo)
    }
  },
  async created() {
    await this.loadPortfolios(this.pageNo)
  }
}
</script>

<style>

</style>
