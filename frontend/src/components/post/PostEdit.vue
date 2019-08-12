<template>
  <div class="container" style="text-align: start">
    <!-- Title -->
    <div class="form-group">
      <label for="title">Title</label>
      <input v-model="newPost.postTitle" type="text" class="form-control" id="title" 
            :placeholder="newPost.postTitle ? newPost.postTitle : 'Enter Title'"> 
    </div>
    <!-- Image Upload compo -->
    <div class="form-group" style="width: 30%">
      <label for="image">Image</label>
      <Imgur id="image" 
              :imageUrl="(newPost.postThumbnailUrl) ? newPost.postThumbnailUrl : 'http://dy.gnch.or.kr/img/no-image.jpg' "
              @uploadImageUrl="uploadPostThumbnailUrl"></Imgur>
    </div>
    <!-- Content Markdown compo -->
    <div class="form-group">
      <label for="content">Content</label>
      <textarea id="content" :value="newPost.postContent" @input="postContentUpdate" class="form-control" style="height: 20vw;" ></textarea>
      <label for="preview">Preview</label>
      <div id="preview" v-html="compiledMarkdown" ></div>
    </div>
    <!-- Buttons -->
    <div class="my-3" >
      <div class="d-flex justify-content-center">
        <!-- 목록 -->
        <router-link :to="{ name: 'PostListPage'}" class="btn btn-outline-secondary mx-1"><i class="fas fa-arrow-left"></i></router-link>
        <!-- 저장 -->
        <button class="btn btn-outline-info mx-1" @click="postPutPost"><i class="far fa-save"></i></button>
      </div>
    </div>
  </div>
</template>

<script>
import mainServices from '../../apis/mainservice/mainServices'
import pushAlarm from '@/apis/pushalarm/pushAlarm.js'
import Imgur from '../common/Imgur'
import Title from '../common/Title'

export default {
  name: 'PostEdit',
  components: {
    Imgur,
    Title,
  },
  computed: {
    newPost: {
      get: function () {
        return this.$store.state.post.post;
      },
      set: function (newValue) {
      }
    },    
    compiledMarkdown: function () {
      return marked(this.newPost.postContent, { sanitize: true })
    }
  },

  methods: {
    
    postContentUpdate: _.debounce(function (e) {
      this.newPost.postContent = e.target.value
    }, 300),

    uploadPostThumbnailUrl(imageUrl) {
      this.newPost.postThumbnailUrl = imageUrl
    },

    async postPutPost () { 
      if ( !this.$route.params.postId ) { // 새로 만드는 경우
        await mainServices.postPost(this.newPost)
        .then((res) => {
          if(res.status == 201){
            pushAlarm.pushAlarmSend('post', '');
          }
        }).catch((error)=>{
          console.log('post posting error', error);
        })

        this.$router.push({ name: 'PostListPage'})
      } else { // 업데이트의 경우
        await mainServices.putPost(this.newPost)
        this.$router.push({ name: 'PostListPage'})
      }
    }
  }
}
</script>

<style>

</style>
