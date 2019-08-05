<template>
  <div class="containter mt-5 pt-5">
    <Title title="posts"></Title>
    <router-link v-if="loginCheck && accountAuth != 3" :to="{ name: 'PostCreatePage'}" class="btn btn-sm btn-outline-info border-0">
      새 포트폴리오 작성&nbsp; <i class="fas fa-pen"></i>
    </router-link>
    <PostList class="container"></PostList>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import Title from '@/components/common/Title'
import PostList from '@/components/post/PostList'

export default {
  name: 'PostListPage',
  components: {
    Title,
    PostList,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth'])
  },
  methods: {
    ...mapActions('post', ['getPosts'])
  },
  created() {
    this.getPosts()
  }
}
</script>

<style>

</style>
