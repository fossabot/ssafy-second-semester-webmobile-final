<template>
<div class="container-fluid px-0">
  <full-page ref="fullpage" :option = "options" id = "fullpage">
	 	<Carousel class = "mt-5"></Carousel>
	 	<Introduction class = "mt-5"></Introduction>
	 	<PortfolioIntro class = "mt-5"></PortfolioIntro>
	</full-page>
</div>
</template>

<script>
import Carousel from '@/components/home/Carousel.vue'
import Introduction from '@/components/home/Introduction.vue'
import PortfolioIntro from '@/components/home/PortfolioIntro.vue'


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
	PortfolioIntro
  },
  mounted() {
    /*if(Notification.permission === "default"){
       알람 허용 여부를 물어봄
      swRegistration.pushManager.getSubscription()

      return;
    }

    if(Notification.permission === "granted"){
      알람 허용 상태

      return;
    }

    if(Notification.permission === "denied"){
      알람 거부 상태

      return;
    }*/
  }
}

</script>

<style>

</style>
