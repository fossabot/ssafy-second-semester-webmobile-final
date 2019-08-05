<template>
  <div class="container mb-5">
    <PostCommentCreate v-if="loginCheck"></PostCommentCreate>
    <div v-for="postComment in post.postComments">
      <PostComment :postComment="postComment"></PostComment>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import PostCommentCreate from './PostCommentCreate'
import PostComment from './PostComment'

export default {
  name: 'PostCommentList',
  components: {
    PostCommentCreate,
    PostComment,
  },
  computed: {
    ...mapState('post',['post']),
    ...mapState('account',['loginCheck'])
  },
}
</script>

<style>

</style>
