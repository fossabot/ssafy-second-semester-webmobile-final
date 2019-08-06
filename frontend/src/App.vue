<template>
  <div id="app">
    <Header></Header>
    <router-view/>
  </div>
</template>
<script>
import Header from '@/components/common/Header.vue'
import firebase from 'firebase'
export default{
  name : 'App',
  components : {
    Header
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
