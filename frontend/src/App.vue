<template>
  <div>
    <Header :user="user" class="show-on-scroll"></Header>
    <router-view :user="user" />
    <div id="google_translate_element" class="container"></div> 
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Footer from "@/components/Footer"

window.addEventListener('scroll', function() {
  var el = document.querySelector('.show-on-scroll');
  
  if(window.scrollY <= 600) el.classList.add('shown');
  else el.classList.remove('shown');
});

export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
  data(){
    return {
      user : { id:''}
    }
  }, 
  mounted(){
  }
}
</script>

<style>
.show-on-scroll { 
  visibility: hidden; 
}

.show-on-scroll.shown { 
  visibility: visible; 
}

.test {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.goog-te-banner-frame.skiptranslate {
    display: none !important;
    } 
body {
    top: 0px !important; 
    }
</style>
