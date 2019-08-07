<template>
  <div id="app">
    <template v-if= "accountAuth!=4">
      <Header></Header>
      <router-view/>
    </template>
    <template v-else>
      <Esteregg />
    </template>
  </div>
</template>
<script>
import Header from '@/components/common/Header.vue'
import Esteregg from './pages/ee/Esteregg'
import { mapState, mapActions,mapMutations } from 'vuex'
export default{
  name : 'App',
  components : {
    Esteregg,
    Header
  },
  computed:{
    ...mapState('account',['accountEmail','accountName','accountAuth'])
  }
  ,
  mounted() {
    /* 사용자로부터 권한 여부를 물음 */
    Notification.requestPermission()
    .then(function(status) {
        //status 를 어디에 저장하지 ? 쿠키? 디비?
      console.log('Status ', status);
      });
    }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height : 754px;
}
</style>
