<template>
  <div class="container">
    <CommentCreater></CommentCreater>   
    <div v-for="comment in comments">
      <Comment :comment="comment"></Comment>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import Comment from '../components/Comment'
import CommentCreater from '../components/CommentCreater'

export default {
  name: "CommentList",
  components: {
    Comment,
    CommentCreater,
  },
  computed: {
    ...mapState('articles',['categoryName','articleNo','comments'])
  },
  methods: {
    ...mapActions('articles',['getComments'])
  },
  created () {
    this.getComments({
      categoryName: this.categoryName, 
      articleNo: this.articleNo
    })
  }
}
</script>

<style>

</style>
