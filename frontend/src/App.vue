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
import firebase from "@/apis/firebase/firebase"

export default{
  name : 'App',
  components : {
    Esteregg,
    Header
  },
  computed:{
    ...mapState('account',['accountEmail','accountName','accountAuth','accountIsPush'])
  },
  mounted() {
  },
  updated() {
  }
}
</script>
<style>
#app {
  /* font-family: 'Avenir', Helvetica, Arial, sans-serif; */
  font-family: 'Walter Turncoat', 'Poor Story', cursive; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
