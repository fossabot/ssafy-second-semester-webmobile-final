<template>
  <div class="container">
    <!-- Comment view mode -->
    <div v-if="!isEdit">
      {{ postComment.accountName}} / {{ postComment.postCommentContent}} 
      <button v-if="isAuthor" @click="isEdit = true" class="btn btn-sm btn-outline-secondary border-0 ml-auto"><i class="fas fa-pen"></i></button>
      <button v-if="isAuthor" @click="deletePostComment" class="btn btn-sm btn-outline-danger border-0"><i class="fas fa-trash-alt"></i></button>
    </div>
    <!-- Comment edit mode -->
    <div v-else class="form-group row justify-content-center">
      {{ postComment.accountName}} /&nbsp;
      <input type="text" class="form-control form-control-sm col-lg-10 col-md-10 col-sm-9 col-9" v-model="postComment.postCommentContent">
      <button @click="putPostComment" class="btn btn-sm btn-outline-primary ml-1 border-0"><i class="fas fa-comment"></i></button>
    </div>    
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import mainServices from '@/apis/mainservice/mainServices.js'

export default {
  name: 'PostComment',
  data () {
    return {
      isEdit: false,
    }
  },
  props: {
    postComment : {type: Object}
  },
  computed: {
    ...mapState('post',['post']),
    ...mapState('account',['loginCheck']),
    isAuthor() {
      if (this.loginCheck) {
        return mainServices.isAuthor(this.postComment.accountEmail)
      } else {
        return false
      }
    }
  },
  methods: {
    ...mapActions('post',['getPost']),
    async putPostComment() {
      await mainServices.putPostComment(this.post.postId, this.postComment)
      this.isEdit = false
      await this.getPost(this.post.postId)
    },
    async deletePostComment() {
      await mainServices.deletePostComment(this.post.postId, this.postComment.postCommentId)
      await this.getPost(this.post.postId)
    }
  }
}
</script>

<style>

</style>
