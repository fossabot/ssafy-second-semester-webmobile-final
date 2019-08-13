<template>
  <div> 
    <div v-show="$store.state.isLoaded" class="container-fluid px-0">
      <full-page ref="fullpage" :options = "options" id = "fullpage">
        <Carousel class = "mt-3"></Carousel>
        <Introduction class = "mt-5"></Introduction>
        <PortfolioIntro class = "mt-5"></PortfolioIntro>
      </full-page>
    </div>
    <div v-show="!$store.state.isLoaded">
      <Loading></Loading>
    </div>
  </div>

  
</template>

<script>
import Carousel from '@/components/home/Carousel.vue'
import Introduction from '@/components/home/Introduction.vue'
import PortfolioIntro from '@/components/home/PortfolioIntro.vue'
import Loading from '@/components/common/Loading.vue'


export default {
  name: 'HomePage',

  data () {
    return {
      options : {
      	licenseKey: 'OPEN-SOURCE-GPLV3-LICENSE',
        sectionsColor: ['dark', 'dark', 'dark','dark']
      }	
    }
  },
  components: {
    Carousel,
    Introduction,
    PortfolioIntro,
    Loading
  },
  mounted() {
    if (! this.$store.state.isLoaded) {
      document.getElementById("header").style.display = 'none'
      setTimeout(function () {
        this.$store.commit('toggleLoading')
        document.getElementById("header").style.removeProperty("display")
      }.bind(this), 3400);
    }
  }
}

</script>

<style>

</style>
