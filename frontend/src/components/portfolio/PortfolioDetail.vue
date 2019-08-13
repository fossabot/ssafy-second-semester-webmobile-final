<template>
  <div class ="container my-5">
    <h1 class="my-3">{{ portfolio.portfolioTitle }}</h1>
    
      
    <div class="container my-5 py-5 row justify-content-center">
      <div class="scene scene--page" :style="{'z-index': zflip}">
        <div class="page__face" style="color: black">{{ images[pageIndex-1] }}</div>
        <div class="page" :class="{ 'is-flipped': isFlipped }">
          <div class="page__face page__face--front" @click="goPrev">{{ images[pageIndex] }}</div>
          <div class="page__face page__face--back">{{ contents[pageIndex-1] }}</div>
        </div>
      </div>
      <div class="scene scene--page2">
        <div class="page__face" style="color: black">{{ contents[pageIndex+1] }}</div>
        <div class="page2" :class="{ 'is-flipped': isFlipped2 }">
          <div class="page2__face page2__face--front" @click="goNext">{{ contents[pageIndex] }}</div>
          <div class="page2__face page2__face--back">{{ images[pageIndex+1] }}</div>
        </div>
      </div>
    </div>
  


          <div v-html="compiledMarkdown" >

  </div>
  </div>
</template>

<script>
import mainServices from '../../apis/mainservice/mainServices'
import {mapState} from 'vuex'

export default {
  name: 'PortfolioDetail',
  data() {
    return {
      contentsIndex: 0,

      isFlipped: false,
      isFlipped2: false,
      zflip: 0,
      pageIndex: 0


    }
  },
  computed: { 
    images: {
      get: function () {     
        return mainServices.parsePortfolio(this.$store.state.portfolio.portfolio.portfolioContent).images;
      },
      set: function (newValue) {
      }

    },
    contents: {
      get: function () {     
        return mainServices.parsePortfolio(this.$store.state.portfolio.portfolio.portfolioContent).contents;
      },
      set: function (newValue) {
      }
    },
    ...mapState('portfolio',['portfolio']),
    compiledMarkdown() {      
      return marked(this.contents[this.pageIndex], { sanitize: true })
    },
  },
  methods: {
    goPrev() {
      if (this.pageIndex == 0) {
        alert('첫 페이지 입니다.')
      } else {
        this.zflip = 5
        this.isFlipped = true
        setTimeout(function () {
          this.isFlipped = false
          this.pageIndex -= 1
          this.zflip = 0
        }.bind(this), 1000);
      }
    },

    goNext() {
      if (this.pageIndex == this.images.length-1) {
        alert('마지막 페이지 입니다.')
      } else {

        this.isFlipped2 = true
        setTimeout(function () {
          this.isFlipped2 = false
          this.pageIndex += 1
        }.bind(this), 1000);
      }
    }
  }
  
}

</script>

<style>

.scene {
  width: 200px;
  height: 260px;
  border: 1px solid #CCC;
  margin: 40px 0;
  perspective: 600px;
}



.page {
  position: relative;
  width: 100%;
  height: 100%;
  cursor: pointer;
  transform-style: preserve-3d;
  transform-origin: center right;
}

.page.is-flipped {
  transition: transform 1s;
  transform: rotateY(180deg);
  transform-origin: right;
}



.page__face {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: 260px;
  color: white;
  text-align: center;
  font-weight: bold;
  font-size: 40px;
  backface-visibility: hidden;
}

.page__face--front {
  background: red;
}

.page__face--back {
  background: blue;
  transform: rotateY(180deg);
}


.page2 {
  position: relative;
  width: 100%;
  height: 100%;
  cursor: pointer;
  transform-style: preserve-3d;
  transform-origin: center right;
}

.page2.is-flipped {
  transform: rotateY(-180deg);
  transform-origin: left;
  transition: transform 1s; 
}

.page2__face {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: 260px;
  color: white;
  text-align: center;
  font-weight: bold;
  font-size: 40px;
  backface-visibility: hidden;
}

.page2__face--front {
  background: red;
}

.page2__face--back {
  background: blue;
  transform: rotateY(180deg);
}


</style>
