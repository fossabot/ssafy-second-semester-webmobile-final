<template>
  <div class="container mt-5 pt-5">
    <PortfolioDetail></PortfolioDetail>
    <div class="d-flex justify-content-center">
      <!-- 목록 -->
      <router-link :to="{ name: 'PortfolioListPage'}" class="btn btn-outline-secondary mx-1">
        <i class="fas fa-arrow-left"></i>
      </router-link>
      <!-- 수정 -->
      <router-link v-if="isAuthor && accountAuth != 3" :to="{ name: 'PortfolioUpdatePage', params: { portfolioId: this.$route.params.portfolioId,  } }" class="btn btn-outline-info mx-1">
        <i class="far fa-edit"></i>
      </router-link>
      <!-- 삭제 -->
      <button v-if="isAuthor && accountAuth != 3" @click="deletePortfolio" class="btn btn-outline-danger mx-1">
        <i class="far fa-trash-alt"></i>
      </button>
    </div>

    <hr style="border: solid 0.5px lightsteelblue">
    <PortfolioCommentList></PortfolioCommentList>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import PortfolioDetail from '@/components/portfolio/PortfolioDetail'
import PortfolioCommentList from '@/components/portfolio/PortfolioCommentList'
import mainServices from '../../apis/mainservice/mainServices'

export default {
  name: 'PortfolioDetailPage',
  components: {
    PortfolioDetail,
    PortfolioCommentList,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth']),
    ...mapState('portfolio',['portfolio']),
    isAuthor() {
      if (this.loginCheck) { // logincheck 도 여기
        return mainServices.isAuthor(this.portfolio.accountEmail)
      } else {
        return false
      }
    }
  },
  methods: {
    ...mapActions('portfolio',['getPortfolio']),
    async deletePortfolio() {
      await mainServices.deletePortfolio(this.$route.params.portfolioId)
      this.$router.push({ name: 'PortfolioListPage'})
    }
  },
  async created() {
    await this.getPortfolio(this.$route.params.portfolioId)
  }

}
</script>

<style>

</style>
