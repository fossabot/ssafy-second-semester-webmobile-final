<template>
  <div class="container">
    <div class="form-group row justify-content-around">
      <input v-model="comment.comment_content" type="text" class="form-control col-md-11 col-sm-10 col-10">
      <button @click="postComment" class="btn btn-outline-primary"><i class="far fa-comment"></i></button>
    </div>
  </div>     
</template>

<script>
import {mapState, mapActions} from 'vuex'
import mainServices from '../apis/mainServices'

export default {
  name: "CommentCreater",
  data () {
    return {
      comment: {
        comment_no: "", // auto
        comment_content: "Enter a comment under 1000 spells.",
        comment_write_date: "", // auto
        article_no: "", 
        account_no: "1", // default. should be modified
        account_name: "KSJ", // default

      },
    }
  },
  computed: {
    ...mapState('articles',['categoryName','articleNo','comments'])
  },
  methods: {
    ...mapActions('articles',['getComments']),
    async postComment() {
      this.comment.article_no = this.articleNo
      await mainServices.postComment(this.categoryName,this.articleNo,this.comment)
      await this.getComments({
        categoryName: this.categoryName,
        articleNo: this.articleNo
      })
    }
  },

}
</script>

<style>

</style>
