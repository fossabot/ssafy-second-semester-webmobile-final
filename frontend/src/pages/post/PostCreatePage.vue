<template>
  <div class="container mt-5 pt-5">
    <PostEdit></PostEdit>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
import PostEdit from '../../components/post/PostEdit'

export default {
  name: 'PostCreatePage',
  components: {
    PostEdit,
  },
  methods: {
    ...mapMutations('post',['setNewPost'])
  },
  created() {
    this.setNewPost()
  }

}
</script>

<style>

</style>
