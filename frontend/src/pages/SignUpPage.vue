<template>
  <div class="container">
    <div style="height:100px"></div>
    <div class="row justify-content-center mt-5">
      <Title title="Sign Up"></Title>
    </div>
    <div class="d-flex justify-content-center">
       <div class="col-sm-6 col-md-offset-3">
        <form role="form">
          <div class="form-group">
              <label for="inputName">성명</label>
              <input type="text" class="form-control" v-model="account.account_name" placeholder="이름을 입력해 주세요">
          </div>
          <div class="form-group">
              <label for="InputEmail">아이디 (이메일 주소)</label>
              <input type="email" class="form-control" v-model="account.account_email" placeholder="이메일 주소를 입력해주세요">
          </div>
          <div class="form-group">
              <label for="inputPassword">비밀번호</label>
              <input type="password" class="form-control" v-model="account.account_password" placeholder="비밀번호를 입력해주세요">
          </div>
          <div class="form-group">
              <label for="inputPasswordCheck">비밀번호 확인</label>
              <input type="password" class="form-control" v-model="account_password_check" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
          </div>
          <div class="form-group">
              <label for="InputGit">GitLab 주소</label>
              <input type="text" class="form-control" v-model="account.account_gitlab_email" placeholder="gitlab 주소를 입력해주세요">
          </div>
          <div class="form-group text-center">
              <a class="btn btn-primary" @click="regist()">
                  회원가입 <i class="fa fa-check spaceLeft"></i>
              </a>
              <router-link :to="{ name: 'homepage', params: {} }" class="btn btn-warning">
                  가입취소 <i class="fa fa-times spaceLeft"></i>
              </router-link>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Inner from '../apis/Inner'
import axios from 'axios'
import Title from '../components/Title'

export default {
	name: 'SignUpPage',
	components: {
    Title
  },props :{
    user :{}
  },
  data() {
    return {
      data : {
        
      },
      account : {
        account_name:'',
        account_email:'',
        account_gitlab_email:'',
        account_password:'',
      },
      account_password_check:'',
      url: "http://localhost:8080/api/bears/accounts"
    }
  },
  mounted(){
    // this.data = Inner.getTests(this.url)
    console.log(this.user)
    if(this.user){
      this.account.account_name = this.user.name
      this.account.account_email = this.user.id
    }
    // eventBus.$on('sendUser', function(value){  // 이벤트를 받은 컴포넌트
    //   alert('전달받은 데이터:'+ value);
    // });
  }
  ,
  methods: {
    check() {
      if(escape(this.account.account_password).length>14 || escape(this.account.account_password).length<8){
        alert("패스워드의 길이가 적절하지 않습니다.")
        return false
      }else{
        if(escape(this.account_password_check)!=escape(this.account.account_password)){
          alert("비밀번호 확인과 비밀번호가 일치하지 않습니다.")
          return false
        }
      }
      if(!escape(this.account.account_email).includes("@")||!escape(this.account.account_email).includes(".")){
        alert("이메일의 형식이 적절하지 않습니다.")
        return false
      }
      return true
    },
   async regist() {
      if(this.check()){
        // 해당 데이터 (input)을 가지고 서버단에 post 메세지를 보냄
        await axios.post(this.url,this.account)
          .then((res)=>{
            //?
            console.log("확인 완료 : "+res.status);
          })
        alert("회원가입을 완료합니다")
        this.$router.push("/")
      }
      console.log(this.input)
    },
    
  }
}
</script>

<style>

</style>
