<template>
  <div class="container">
    <Imgur></Imgur>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import Imgur from '../common/Imgur'

export default {
  name: 'PortfolioEditComp',
  components: {
    Imgur
  },
  computed: {
    ...mapState('portfolio',['portfolio']),
  },
}
</script>

<style>

</style>
