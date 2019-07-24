<template>
  <div class="container">
    <!-- Comment view mode -->
    <div v-if="!isEdit">
      {{ comment.account_name}} / {{ comment.comment_content}} 
      <button @click="isEdit = true" class="btn btn-sm btn-outline-secondary border-0 ml-auto"><i class="fas fa-pen"></i></button>
      <button @click="deleteComment" class="btn btn-sm btn-outline-danger border-0"><i class="fas fa-trash-alt"></i></button>
    </div>
    <!-- Comment edit mode -->
    <div v-else class="form-group row justify-content-center">
      {{ comment.account_name}} /&nbsp;
      <input type="text" class="form-control form-control-sm col-lg-11 col-md-10 col-sm-10 col-9" v-model="updated">
      <button @click="updateComment" class="btn btn-sm btn-outline-primary ml-1 border-0"><i class="fas fa-comment"></i></button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "Comment",
  data () {
    return {
      updated: this.comment.comment_content,
      isEdit: false, // default
    }
  },
  props: {
    comment: {},
    commentUrl: "",
  },
  methods: {
    updateComment() {
      this.comment.comment_content = this.updated
      console.log(this.comment.comment_content)

      axios.put(this.commentUrl, this.comment)
          .then((res)=>{
            console.log(res)
            this.isEdit = false
          })   
    },
    deleteComment() {
      axios.delete(this.commentUrl)
          .then((res)=> {
            console.log(res)
            this.$emit('getComments')
          })
    }
  }

}
</script>

<style>

</style>
