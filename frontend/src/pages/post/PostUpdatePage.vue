<template>
  <div class="container mt-5 pt-5">
    <PostEdit></PostEdit>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
import PostEdit from '../../components/post/PostEdit'

export default {
  name: 'PostUpdatePage',
  components: {
    PostEdit,
  },
  methods: {
    ...mapActions('post',['getPost'])
  },
  async created() {
    await this.getPost(this.$route.params.postId) // methods는 created 될 때도 들어오지만 computed는 created 될 때 안들어 오는듯..
  }

}
</script>

<style>

</style>
