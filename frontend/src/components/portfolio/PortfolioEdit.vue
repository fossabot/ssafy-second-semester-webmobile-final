<template>
  <div class="container" style="text-align: start">
    <!-- Title -->
    <div class="form-group">
      <label for="title">Title</label>
      <input v-model="newPortfolio.portfolioTitle" type="text" class="form-control" id="title" 
            :placeholder="newPortfolio.portfolioTitle ? newPortfolio.portfolioTitle : 'Enter Title'"> 
    </div>
    <!-- Image Upload compo -->    
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalCenterTitle">Images</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <Imgur id="image" 
                      :imageUrl="(newPortfolio.portfolioThumbnailUrl) ? newPortfolio.portfolioThumbnailUrl : 'http://dy.gnch.or.kr/img/no-image.jpg' "
                      @uploadImageUrl="uploadPortfolioThumbnailUrl"></Imgur>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button @click="insertImage" data-dismiss="modal" type="button" class="btn btn-primary">Upload</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Content Markdown compo -->
    <div class="form-group">
      <label for="content">Content</label>
      <button type="button" class="mx-3 btn btn-outline-info border-0" data-toggle="modal" data-target="#exampleModalCenter">
        Upload Image
      </button>
      <textarea id="content" :value="newPortfolio.portfolioContent" @input="portfolioContentUpdate" class="form-control" style="height: 20vw;" ></textarea>
      <label for="preview">Preview</label>
      <div id="preview" v-html="compiledMarkdown" ></div>
    </div>
    <!-- Buttons -->
    <div class="my-3" >
      <div class="d-flex justify-content-center">
        <!-- 목록 -->
        <router-link :to="{ name: 'PortfolioListPage'}" class="btn btn-outline-secondary mx-1"><i class="fas fa-arrow-left"></i></router-link>
        <!-- 저장 -->
        <button class="btn btn-outline-info mx-1" @click="postPutPortfolio"><i class="far fa-save"></i></button>
      </div>
    </div>
  </div>
</template>

<script>
import mainServices from '../../apis/mainservice/mainServices'
import {mapState} from 'vuex'
import pushAlarm from '@/apis/pushalarm/pushAlarm.js'
import Imgur from '../common/Imgur'
import Title from '../common/Title'

import axios from 'axios' // 구현 함수 테스트 완료후 mainService.js에 통합하면서 지워야함.

export default {
  name: 'PortfolioEdit',
  components: {
    Imgur,
    Title,
  },
  computed: {
    newPortfolio: {
      get: function () {
        return this.$store.state.portfolio.portfolio;
      },
      set: function (newValue) {
      }
    },    
    compiledMarkdown: function () {
      return marked(this.newPortfolio.portfolioContent, { sanitize: true })
    },
    ...mapState('account',['accountAuth'])
  },

  methods: {
    
    portfolioContentUpdate: _.debounce(function (e) {
      this.newPortfolio.portfolioContent = e.target.value
    }, 300),

    uploadPortfolioThumbnailUrl(imageUrl) {
      this.newPortfolio.portfolioThumbnailUrl = imageUrl
    },

    async postPutPortfolio () { 
      if ( !this.$route.params.portfolioId ) { // 새로 만드는 경우
        await mainServices.postPortfolio(this.newPortfolio)
        .then((res) => {
          if(res.status == 201){
            pushAlarm.pushAlarmSend('portfolio', '');
          }
        }).catch((error)=>{
          console.log('portfolio post error', error);
        })

        this.$router.push({ name: 'PortfolioListPage'})
      } else { // 업데이트의 경우
        await mainServices.putPortfolio(this.newPortfolio)
        this.$router.push({ name: 'PortfolioListPage'})
      }
    },

    insertImage() {
      this.newPortfolio.portfolioContent = this.newPortfolio.portfolioContent + `![img](${this.newPortfolio.portfolioThumbnailUrl})` 
    }
  }
}
</script>

<style>

</style>
