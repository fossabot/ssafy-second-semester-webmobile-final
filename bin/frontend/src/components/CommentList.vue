<template>
  <div class="container">
    <CommentCreater :articleNo="articleNo" :commentsUrl="commentsUrl" @getComments="getComments"></CommentCreater>   
    <div v-for="comment in commentList">
      <Comment :comment="comment" :commentUrl="`${commentsUrl}/${comment.comment_no}`" @getComments="getComments"></Comment>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Comment from '../components/Comment'
import CommentCreater from '../components/CommentCreater'

export default {
  name: "CommentList",
  components: {
    Comment,
    CommentCreater,
  },
  data() {
    return {
      commentList: []
    }
  },
  props: {
    commentsUrl: { type: String },
    articleNo: { type: String},
  },
  mounted () {
    this.getComments()

  },
  methods: {
    getComments () {
      axios.get(this.commentsUrl)
        .then((res)=>{
          console.log("여기가 comment list")
          console.log(res.data)
          this.commentList = res.data
        })
    }
  }
}
</script>

<style>

</style>
