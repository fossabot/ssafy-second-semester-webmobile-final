<template>
  <div class="container">
    <div class="form-group row justify-content-around">
      <input v-model="comment.comment_content" type="text" class="form-control col-md-11 col-sm-10 col-10">
      <button @click="postComment" class="btn btn-outline-primary"><i class="far fa-comment"></i></button>
    </div>
  </div>     
</template>

<script>
import axios from 'axios'

export default {
  name: "CommentCreater",
  data () {
    return {

      comment: {
        comment_no: "", // auto
        comment_content: "Enter a comment under 1000 spells.",
        comment_write_date: "", // auto
        article_no: "", // 
        account_no: "1", // default. should be modified
        account_name: "KSJ", // default

      },
    }
  },
  props: {
    commentsUrl: {type:String},
    articleNo : {type:String}
  },
  methods: {
    postComment() {

      // article number binding
      this.comment.article_no = this.articleNo
      // post comment
      axios.post(this.commentsUrl,this.comment)
          .then((res)=> {
            console.log(res)
            this.comment.comment_content = "Enter a comment under 1000 spells." 
            this.$emit('getComments')
            
          })
    }
    
  },
  mounted() {
  }
}
</script>

<style>

</style>
