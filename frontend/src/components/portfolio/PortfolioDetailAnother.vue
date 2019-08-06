<template>
  
</template>

<script>
import mainServices from '@/apis/mainservice/mainServices.js'
import {mapState} from 'vuex'

export default {
  name: 'PortfolioDetailAnother',
  computed: {
    ...mapState('portfolio',['portfolio'])
  }
}
</script>

<style>

</style>
