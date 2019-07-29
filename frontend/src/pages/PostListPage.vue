<template>
  <div class="container">
    <div style="height:100px"></div>
    <!-- Title -->
    <div class="row justify-content-center mt-5">
      <Title title="Posts"></Title>
      <router-link :to="{ name: 'newpost', params: {} }" class="btn btn-sm btn-outline-info border-0 mt-1 ml-1" style="height: 28px">
        <i class="fas fa-pen"></i>
      </router-link>
    </div>
    <!-- List compo -->
    <div class="row justify-content-center">
      <HorizenItem :cardData="testCard"></HorizenItem>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Inner from '../apis/Inner'
import Carousel from '../components/Carousel'
import HorizenItem from '../components/HorizenItem'
import Title from '../components/Title'

export default {
  name: 'PostListPage',
  data () {
    return {
      test: {},
      url: "http://localhost:8080/api/bears/articles/posts",
      testCard : [],
    }
  },
  mounted() {
    console.log("created 됨")
    console.log(this.$route.path)
    Inner.getArticle(this.url)
        .then((data)=> {
          this.testCard = data
          
        })
  },
  methods: {
  },
  components : {
    Carousel,
    HorizenItem,
    Title,

  }
}
</script>

<style>

</style>
