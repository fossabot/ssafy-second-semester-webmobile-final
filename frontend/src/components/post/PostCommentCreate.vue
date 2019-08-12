<template>
  <div class="container">
    <div class="form-group row justify-content-around">
      <input v-model="postCommentContent" type="text" class="form-control col-md-11 col-sm-10 col-10" :placeholder="example">
      <button @click="postPostComment" class="btn btn-outline-primary"><i class="far fa-comment"></i></button>
    </div>
  </div>
</template>

<script>
import {mapState, mapActions, mapMutations} from 'vuex'
import mainServices from '@/apis/mainservice/mainServices.js'
import pushAlarm from '@/apis/pushalarm/pushAlarm.js'

export default {
  name: 'PostCommentCreate',
  computed: {
    ...mapState('post',['post']),
  },
  data() {
    return {
      postCommentContent: '',
      example: 'Enter Comments'
    }
  },
  methods: {
    ...mapActions('post',['getPost']),
    async postPostComment() {
      await mainServices.postPostComment(this.post.postId, this.postCommentContent)
      .then((res) => {
          if(res.status == 201){
            pushAlarm.pushAlarmSend('postComment', this.post.postId);
          }
        }).catch((error)=>{
          console.log('postComment post error', error);
        })
      await this.getPost(this.post.postId)
      this.postCommentContent = ''
      this.example = 'Enter Comment'
    }
  },

}
</script>

<style>

</style>
