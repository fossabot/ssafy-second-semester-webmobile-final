<template>
  <div class="container">
    <!-- Comment view mode -->
    <div v-if="!isEdit">
      {{ portfolioComment.accountName}} / {{ portfolioComment.portfolioCommentContent}} 
      <button v-if="isAuthor" @click="isEdit = true" class="btn btn-sm btn-outline-secondary border-0 ml-auto"><i class="fas fa-pen"></i></button>
      <button v-if="isAuthor" @click="deletePortfolioComment" class="btn btn-sm btn-outline-danger border-0"><i class="fas fa-trash-alt"></i></button>
    </div>
    <!-- Comment edit mode -->
    <div v-else class="form-group row justify-content-center">
      {{ portfolioComment.accountName}} /&nbsp;
      <input type="text" class="form-control form-control-sm  col-lg-10 col-md-10 col-sm-9 col-9" v-model="portfolioComment.portfolioCommentContent">
      <button @click="putPortfolioComment" class="btn btn-sm btn-outline-primary ml-1 border-0"><i class="fas fa-comment"></i></button>
    </div>    
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import mainServices from '@/apis/mainservice/mainServices.js'

export default {
  name: 'PortfolioComment',
  data () {
    return {
      isEdit: false,
    }
  },
  props: {
    portfolioComment : {type: Object}
  },
  computed: {
    ...mapState('portfolio',['portfolio']),
    ...mapState('account',['loginCheck']),
    isAuthor() {
      if (this.loginCheck) {
        return mainServices.isAuthor(this.portfolioComment.accountEmail)
      } else {
        return false
      }
    }
  },
  methods: {
    ...mapActions('portfolio',['getPortfolio']),
    async putPortfolioComment() {
      await mainServices.putPortfolioComment(this.portfolio.portfolioId, this.portfolioComment)
      this.isEdit = false
      await this.getPortfolio(this.portfolio.portfolioId)
    },
    async deletePortfolioComment() {
      await mainServices.deletePortfolioComment(this.portfolio.portfolioId, this.portfolioComment.portfolioCommentId)
      await this.getPortfolio(this.portfolio.portfolioId)
    }
  }
}
</script>

<style>

</style>
