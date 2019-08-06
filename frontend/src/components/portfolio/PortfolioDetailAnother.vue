<template>
  <div class="container">
    
    <div id="example-1">
      <button @click="show = !show">
        Toggle render
      </button>
      <transition name="slide-fade">
        <p v-if="show">hello</p>
      </transition>
    </div>




    <div>
      <div class='hold'>
        <div class='shadow'></div>
        <div id='lower' class='page-holder'>
          <div class='page'>
            <div class='front' style="background-image: url('https://saquibalam.com/images/photography/IMG_0628.jpg')"></div>
          </div> 
        </div>
        <div id='upper' class='page-holder'>
          <div class='page'>
            <div class='front' style="background-image: url('https://saquibalam.com/images/photography/IMG_0637.jpg')">
              <div class='back-holder'>
                <div class='back' style="background-image: url('https://saquibalam.com/images/photography/IMG_0885.jpg')"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> 
  </div>
</template>

<script>
import mainServices from '@/apis/mainservice/mainServices.js'
import {mapState} from 'vuex'

export default {
  name: 'PortfolioDetailAnother',
  data() {
    return {
      parsedPortfolioContent: {},
      show: true
    }
  },
  computed:{
    ...mapState('portfolio',['portfolio'])
  },
  created() {
    mapState('portfolio',['portfolio'])
    this.parsedPortfolioContent = mainServices.parsePortfolio(this.portfolio.portfolioContent)
  }
}
</script>

<style>
.slide-fade-enter-active {
  transition: all .3s ease;
}
.slide-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}





.hold{
  width: 1000px;
  margin: auto;
  position: relative;
  display: block;
  height: 600px;
}
.shadow{
  position: absolute;
  width: 500px;
  left: 50%;
  top: 150px;
  height: 300px;
  box-shadow: 6px 4px 10px 0px rgba(0,0,0, 0.7);
}
.page-holder{
  height: 600px;
  width: 1000px;
  position: absolute;
  overflow: hidden;
  cursor: pointer;
}
.page{
  height: 200%;
  width: 100%;
  top: -50%;
  left: 0%;
  position: absolute;
  overflow: hidden;
  transform: rotateZ(-30deg);
  transition: transform 1s, left 1s;
  transform-origin: 100% 37.5%;
}

.page>.front,
.page .back-holder,
.page .back{
  height: 300px;
  width: 500px;
  position: absolute;
  background-size: cover;
  transition: transform 1s, left 1s;
  background-color: black;
  color: white;
}

.page>.front{
  top: 37.5%;
  left: 50%;
  transform: rotateZ(30deg) translateX(0);
  transform-origin: 100% 0%;
  box-shadow: inset 10px 0 10px -8px #000000;
}
.page .back-holder{
  left: 100%;
  top: 0;
  transition: left 1s, transform 1s;
  transform-origin: 0% 0%;
  transform: rotateZ(-60deg);
}
.page .back{
  left: 0%;
  top: 0;
  transform: rotateZ(0deg);
  transform-origin: 0% 0%;
  transition: top 1s, left 1s;
  box-shadow: inset -15px 0 22px -10px #000000;
}
.page .back:after{
  content: ' ';
  position: absolute;
  height: 100%;
  transform: rotateZ(-0.7deg);
  transform-origin: 0% 100%;
  width: 100%;
  box-shadow: 10px 8px 10px -1px rgba(0,0,0, 0.8);
}
.page-holder:hover .page{
  transform: rotateZ(-0deg);
  left: -50%;
}
/* .page-holder:hover .page>.front{
  transform: rotateZ(0deg) translateX(100%);
} */

.page-holder:hover .back-holder{
  left: 0%;
  transform: rotateZ(0deg);
}


.page-holder:hover .back{
  left: -100%;
}

</style>
