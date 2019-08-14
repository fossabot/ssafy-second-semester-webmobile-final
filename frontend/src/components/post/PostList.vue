<template>
  <!-- Card List -->
  <div class="scrolling-wrapper mt-5" >
    <!-- Card -->
    <div v-for="post in posts" class="card mx-3" style="width: 15rem;">
      <!-- Card Image -->
      <img :src="post.postThumbnailUrl ? post.postThumbnailUrl : 'https://source.unsplash.com/random/500x500'" class="card-img-top" style="width: 100%; height: 15rem" alt="...">
      <!-- Card Body -->
      <div class="card-body">
        <h5 class="card-title card-text-ellipsis">{{ post.postTitle }}</h5>
        <p class="card-text card-text-ellipsis" style="color: gray">by {{ post.accountName }}</p>
        <p class="card-text card-text-ellipsis" style="color: rgb(150,150,150)">{{ post.postContent }}</p>
        <!-- Buttons -->
        <div class="row justify-content-end">
          <!-- 더 보기 -->
          <i class="far fa-eye py-2" style="color: rgb(100,100,100)"></i> &nbsp; 
          <span class="py-1" style="color: rgb(100,100,100)">{{ post.postViews}} </span> &nbsp;
          <router-link :to="{ name: 'PostDetailPage', params: { postId: post.postId }}" class="btn btn-sm btn-outline-info mx-1">
            <i class="fas fa-plus"></i>
          </router-link>
        </div>        
      </div>
    </div>
    <button v-if="totalPostPages != 1 && pageNo < totalPostPages" type="button" class="btn btn-outline-secondary border-0 align-top" @click="$emit('addPosts')" style="margin-top: 180px">
      <i class="fas fa-plus-circle fa-5x"></i>
    </button>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'PostList',
  props: {
    pageNo: {type: Number}
  },
  computed: {
    ...mapState('post', ['posts','totalPostPages']),
  },mounted() {
    document.querySelector('.scrolling-wrapper').addEventListener('wheel', function(event) {
      document.querySelector('.scrolling-wrapper').scrollLeft += event.deltaY;
    });
  }
}
</script>

<style>
.scrolling-wrapper {
  overflow-x: scroll;
  overflow-y: hidden;
  white-space: nowrap;
  direction: horizontal;
  width: 80%;
  height: 450px;
}

.scrolling-wrapper::-webkit-scrollbar {
  height: 2px;
}

.scrolling-wrapper::-webkit-scrollbar-track {
  width: 5px;
}

.scrolling-wrapper::-webkit-scrollbar-thumb {
  background:  #8e97ab;
  border-radius: 10px;
}

.card {
    display: inline-block;
}

.card-text-ellipsis {
    width: 13rem;
    overflow:hidden; 
    white-space:nowrap;
    text-overflow:ellipsis;

}
</style>
