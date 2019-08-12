<template>
  <div class="container">
    <div class="form-group row justify-content-around">
      <input v-model="portfolioCommentContent" type="text" class="form-control col-md-11 col-sm-10 col-10" :placeholder="example">
      <button @click="postPortfolioComment" class="btn btn-outline-primary"><i class="far fa-comment"></i></button>
    </div>
  </div>
</template>

<script>
import {mapState, mapActions, mapMutations} from 'vuex'
import mainServices from '@/apis/mainservice/mainServices.js'
import pushAlarm from '@/apis/pushalarm/pushAlarm.js'

export default {
  name: 'PortfolioCommentCreate',
  computed: {
    ...mapState('portfolio',['portfolio']),
  },
  data() {
    return {
      portfolioCommentContent: '',
      example: 'Enter Comments'
    }
  },
  methods: {
    ...mapActions('portfolio',['getPortfolio']),
    async postPortfolioComment() {
      await mainServices.postPortfolioComment(this.portfolio.portfolioId, this.portfolioCommentContent)
      .then((res)=>{
        if(res.status == 201){
          pushAlarm.pushAlarmSend('portfolioComment', this.portfolio.portfolioId);
        }
      })
      .catch((error)=>{

      })

      await this.getPortfolio(this.portfolio.portfolioId)
      this.portfolioCommentContent = ''
      this.example = 'Enter Comment'
    }
  },

}
</script>

<style>

</style>
