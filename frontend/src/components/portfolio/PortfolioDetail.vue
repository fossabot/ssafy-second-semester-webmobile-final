<template>
  <div class ="container">
    <h1 class="mt-3">{{ portfolio.portfolioTitle }}</h1>
    <p class="lead" style="color: gray">authored by {{ portfolio.accountName }} </p>
    <div class="container row justify-content-center">
      <div class="scene row justify-content-end align-items-center" style="z-index: 5">
        <!-- cover -->
        <div class="page__face"><img :src="images[pageIndex-1]" class="py-3" style="max-width: 80%; max-height: 80%"/></div>
        <!-- cover end --> 
        <div class="page row justify-content-end align-items-center mx-0" :class="{ 'is-flipped': isFlipped }">
          <div class="page__face page__face--front" @click="goPrev"><img :src="images[pageIndex]" class="py-3" style="max-width: 80%; max-height: 80%"/></div>
          <div class="page__face page__face--back"><div v-html="previousContent" ></div></div>
        </div>
      </div>
      <div class="scene2 row align-items-center" :style="{'z-index': zflip}">
        <!-- cover -->
        <div class="page2__face"><div v-html="nextContent" ></div></div>
        <!-- cover end-->
        <div class="page2 row align-items-center mx-0" :class="{ 'is-flipped': isFlipped2 }">
          <div class="page2__face page2__face--front" @click="goNext"><div v-html="currentContent"></div></div>
          <div class="page2__face page2__face--back"><img :src="images[pageIndex+1]"  class="py-3" style="max-width: 80%; max-height: 80%"/></div>
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
        const images = mainServices.parsePortfolio(this.$store.state.portfolio.portfolio.portfolioContent).images 
        if (!images) {
          images = ['https://source.unsplash.com/random']
        }
        return images;
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
    currentContent() {      
      return marked(this.contents[this.pageIndex], { sanitize: true })
    },
    previousContent() {
      if (this.pageIndex == 0) {
        return 0
      } else {
        return marked(this.contents[this.pageIndex-1], { sanitize: true })
      }
    },
    nextContent() {
      if (this.pageIndex == this.images.length-1) {
        return 0
      } else {
        return marked(this.contents[this.pageIndex+1], { sanitize: true })
      }
    }
  },
  methods: {
    goPrev() {
      if (this.pageIndex == 0) {
        alert('첫 페이지 입니다.')
      } else {
        this.isFlipped = true
        setTimeout(function () {
          this.isFlipped = false
          this.pageIndex -= 1
        }.bind(this), 1000);
      }
    },

    goNext() {
      if (this.pageIndex == this.images.length-1) {
        alert('마지막 페이지 입니다.')
      } else {
        this.zflip = 5
        this.isFlipped2 = true
        setTimeout(function () {
          this.isFlipped2 = false
          this.pageIndex += 1
          this.zflip = 0
        }.bind(this), 1000);
      }
    }
  }
  
}

</script>

<style>

.scene {
  width: 50%;
  height: 500px;
  margin: 40px 0;
  perspective: 600px;
  background-image: url('https://i.imgur.com/PQfGKvv.png');
  background-size: 100% 100%;
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
  width: 96%;
  height: 96%;
  line-height: 260px;
  color: black;
  text-align: center;
  backface-visibility: hidden;
  background-image: url("https://i.imgur.com/7vfA7Kl.png");
  background-size: 100% 100%;
}

.page__face--front {
  background-image: url("https://i.imgur.com/7vfA7Kl.png");
  background-size: 100% 100%;
}

.page__face--back {
  background-image: url("https://i.imgur.com/4FUgnLs.png");
  background-size: 100% 100%;
  transform: rotateY(180deg);
}

.scene2 {
  width: 50%;
  height: 500px;
  margin: 40px 0;
  perspective: 600px;
  background-image: url('https://i.imgur.com/yN9dLUa.png');
  background-size: 100% 100%;
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
  width: 96%;
  height: 96%;
  line-height: 260px;
  color: black;
  text-align: center;
  backface-visibility: hidden;
  background-image: url("https://i.imgur.com/4FUgnLs.png");
  background-size: 100% 100%;
}

.page2__face--front {
  background-image: url("https://i.imgur.com/4FUgnLs.png");
  background-size: 100% 100%;
}

.page2__face--back {
  background-image: url("https://i.imgur.com/7vfA7Kl.png");
  background-size: 100% 100%;
  transform: rotateY(180deg);
}


</style>
