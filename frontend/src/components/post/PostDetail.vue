<template>
  <div class ="container">
    <div class="jumbotron bg-white">
      <h1 class="display-4">{{ post.postTitle }}</h1> 
      <i class="far fa-eye"></i> {{ post.postViews}}
      <p class="lead">by {{ post.accountName }} at {{ post.postCreatedAt }} </p>
      <hr class="my-4">
      <div class="row justify-content-around">
        <img :src="post.postThumbnailUrl ? post.postThumbnailUrl : 'https://source.unsplash.com/random/1000x1000' " class="col-5"/>
        <div class="col-5" v-html="compiledMarkdown" ></div>       
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'PostDetail',
  computed: {
    ...mapState('post',['post']),
    compiledMarkdown() {
      return marked(this.post.postContent, { sanitize: true })
    },
  },  
}
</script>

<style>

</style>
