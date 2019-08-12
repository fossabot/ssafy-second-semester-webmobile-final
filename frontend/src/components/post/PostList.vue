<template>
  <!-- Card List -->
  <div class="scrolling-wrapper my-5">
    <!-- Card -->
    <div v-for="post in posts" class="card mx-3" style="width: 15rem;">
      <!-- Card Image -->
      <img :src="post.postThumbnailUrl ? post.postThumbnailUrl : 'https://source.unsplash.com/random/500x500'" class="card-img-top" style="width: 100%; height: 15rem" alt="...">
      <!-- Card Body -->
      <div class="card-body">
        <h5 class="card-title card-text-ellipsis">{{ post.postTitle }}</h5>
        <p class="card-text card-text-ellipsis" style="color: gray">{{ post.postContent }}</p>
        <!-- Buttons -->
        <div class="row justify-content-end">
          <!-- 더 보기 -->
          <router-link :to="{ name: 'PostDetailPage', params: { postId: post.postId }}" class="btn btn-sm btn-outline-info mx-1">
            <i class="fas fa-plus"></i>
          </router-link>
        </div>        
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'PostList',
  computed: {
    ...mapState('post', ['posts']),
  },
}
</script>

<style>
.scrolling-wrapper {
  overflow-x: scroll;
  overflow-y: hidden;
  white-space: nowrap;
  width: 80%;
  height: 420px;
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
