<template>
  <div class="container mt-5 pt-5">
    <PostDetail></PostDetail>
    <div class="d-flex justify-content-center">
      <!-- 목록 -->
      <router-link :to="{ name: 'PostListPage'}" class="btn btn-outline-secondary mx-1">
        <i class="fas fa-arrow-left"></i>
      </router-link>
      <!-- 수정 -->
      <router-link v-if="isAuthor && accountAuth != 3" :to="{ name: 'PostUpdatePage', params: { postId: this.$route.params.postId,  } }" class="btn btn-outline-info mx-1">
        <i class="far fa-edit"></i>
      </router-link>
      <!-- 삭제 -->
      <button v-if="isAuthor && accountAuth != 3" @click="deletePost" class="btn btn-outline-danger mx-1">
        <i class="far fa-trash-alt"></i>
      </button>
    </div>

    <hr style="border: solid 0.5px lightsteelblue">
    <PostCommentList></PostCommentList>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import PostDetail from '@/components/post/PostDetail'
import PostCommentList from '@/components/post/PostCommentList'
import mainServices from '../../apis/mainservice/mainServices'

export default {
  name: 'PostDetailPage',
  components: {
    PostDetail,
    PostCommentList,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth']),
    ...mapState('post',['post']),
    isAuthor() {
      if (this.loginCheck) { // logincheck 도 여기
        return mainServices.isAuthor(this.post.accountEmail)
      } else {
        return false
      }
    }
  },
  methods: {
    ...mapActions('post',['getPost']),
    async deletePost() {
      await mainServices.deletePost(this.$route.params.postId)
      this.$router.push({ name: 'PostListPage'})
    }
  },
  async created() {
    await this.getPost(this.$route.params.postId)
  }

}
</script>

<style>

</style>
