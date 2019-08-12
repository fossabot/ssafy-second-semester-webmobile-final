<template>
  <div class ="container my-5">
    <h1 class="my-3">{{ portfolio.portfolioTitle }}</h1>
    <div class="row">
      <!-- Portfolio Images -->
      <div id="carouselExampleIndicators" class="carousel slide col-6 mx-0" data-ride="carousel" >
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li v-for="idx in parsedPortfolio.contents.length-1" data-target="#carouselExampleIndicators" :data-slide-to="idx+1"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="d-block w-100" :src="parsedPortfolio.images[0]" alt="First slide">
          </div>
          <div v-for="idx in parsedPortfolio.images.length-1" class="carousel-item">
            <img class="d-block w-100" :src="parsedPortfolio.images[idx]" alt="Another slide">
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev" @click="movePrevious">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next" @click="moveNext">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>

      <!-- Portfolio Contents -->
      <div class="col-5">
        <div>
          <div v-html="compiledMarkdown" ></div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import mainServices from '../../apis/mainservice/mainServices'
import {mapState} from 'vuex'
import turn from '../../apis/turn/turn.min.js'



export default {
  name: 'PortfolioDetail',
  data() {
    return {
      contentsIndex: 0
    }
  },
  computed: {
    parsedPortfolio: {
      get: function () {     
        return mainServices.parsePortfolio(this.$store.state.portfolio.portfolio.portfolioContent);
      },
      set: function (newValue) {
      }
    },  
    ...mapState('portfolio',['portfolio']),
    compiledMarkdown() {      
      return marked(this.parsedPortfolio.contents[this.contentsIndex], { sanitize: true })
    },
  },
  methods: {
    moveNext() {
      if (this.contentsIndex == this.parsedPortfolio.contents.length-1) {
        this.contentsIndex = 0
      } else {
        this.contentsIndex++
      }
    },
    movePrevious() {
      if (this.contentsIndex == 0) {
        this.contentsIndex = this.parsedPortfolio.contents.length-1
      } else {
        this.contentsIndex--
      }
    }
  }
  
}

</script>

<style>

</style>
