<template>
  <div class="containter mt-5 pt-5">
    <Title title="posts" class="mt-3"></Title>
    <router-link v-if="loginCheck && accountAuth != 3" :to="{ name: 'PostCreatePage'}" class="btn btn-sm btn-outline-info border-0">
      새 포스트 작성&nbsp; <i class="fas fa-pen"></i>
    </router-link>
    <PostList @addPosts="addPosts" :pageNo="pageNo" class="container"></PostList>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import Title from '@/components/common/Title'
import PostList from '@/components/post/PostList'

export default {
  name: 'PostListPage',
  data() {
    return {
      pageNo: 1,
      isMore: true
    }
  },
  components: {
    Title,
    PostList,
  },
  computed: {
    ...mapState('account',['loginCheck','accountAuth'])
  },
  methods: {
    ...mapActions('post', ['loadPosts']),
    async addPosts() {
      this.pageNo += 1
      await this.loadPosts(this.pageNo)
    }
  },
  async created() {
    await mapState('account',['loginCheck', 'accountAuth'])
    await this.loadPosts(this.pageNo)
  }
}
</script>

<style>

</style>
