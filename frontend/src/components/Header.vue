<template>
  <div class="container-fulid fixed-top mt-3">
    <!-- <modal v-if="showModal" @close="showModal = false"> -->
    <LoginModal :showModal="showModal" :user="user" @signIn="signIn" @close="close" :naverLogin="naverLogin"></LoginModal>
    <div class="container">
      
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="text-align-middle">
        <router-link :to="{ name: 'homepage', params: {} }" class="navbar navbar-brand  d-inline-flex">
          <i class="fas fa-paw fa-3x"></i>
           <h2 class="d-inline ml-3 mb-0 text-align-middle"> Bare Bears</h2>
        </router-link>
      </div>
        <button class=" navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
        <div class="nav navbar-nav ml-auto">            
          <router-link :to="{ name: 'homepage', params: {} }" class="nav-item nav-link h4">Home</router-link>
          <router-link :to="{ name: 'portfoliolistpage', params: {} }" class="nav-item nav-link h4">Portfolio</router-link>
          <router-link :to="{ name: 'postlistpage', params: {} }" class="nav-item nav-link h4">Post</router-link>
          <router-link :to="{ name: 'repopage', params: {} }" class="nav-item nav-link h4">Repo</router-link>
          <!-- <router-link :to="{ name: 'loginpage', params: {} }" class="nav-item nav-link h6">Login</router-link> -->
          <a v-if="!userlogin" class="nav-item nav-link h6" @click="showModal = true" >Login</a>
          <router-link v-if="!userlogin" :to="{ name: 'signuppage', params: {} }" class="nav-item nav-link h6">SignUp</router-link>
          <a href="#" v-if="userlogin" class="nav-item nav-link h6" @click="logOut" >환영합니다 {{user.id}}님</a>
        </div>
      </div>
    </nav>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
import LoginModal from './LoginModal'

export default {
  name: 'Header',
  components :{
    LoginModal
  },
  data () {
    return {
      categories : ['homepage','portfoliolistpage','postlistpage','loginpage','signuppage'],
      showModal : false,
      naverLogin : new naver.LoginWithNaverId({
        clientId: "BDMXXSAmX8cdQC95RrXg",
        callbackUrl: "http://localhost:8080/callback",
        isPopup: false, /* 팝업을 통한 연동처리 여부 */
        loginButton: {color: "green", type: 3, height: 60}, /* 로그인 버튼의 타입을 지정 */
      }),
      userlogin:false
    }
  },
  props :{
    user : { id:''}
  },
  mounted() {
    let idCheck = this.idCheck
    setTimeout(function(){
      idCheck();
    }, 1000);
  },
  updated(){
    this.naverLogin.init()
  },
  methods: {
    signIn(user) {
      console.log(user.id + user.password);
      // 객체 가지고 로그인 확인해야 함
      user.id="";
      user.password=""
      this.showModal = false
    },
    close(){
      // 정상 종료
      this.showModal =false
    },
    search(id){
      if(idCheck(id)){
        // 해당 데이터에 매칭해 아이디 접근
      }else{
        alert("존재하지 않는 아이디 입니다.")
      }
    },
    idCheck(){
      // 데이터 베이스에 접근해 해당 아이디가 있는지 확인
      if(sessionStorage.getItem('id')!=null){
        this.user.id=sessionStorage.getItem('id')
        this.userlogin= true
      }else{
        this.userlogin= false
      }
      console.log("idCheck:"+this.userlogin);
    },
    logOut(){
      this.showModal = false;
      if(sessionStorage.getItem('id')!=undefined && sessionStorage.getItem('id')!=null){
        this.userlogin =false;
        sessionStorage.removeItem('id')
        this.user.id=""
        // this.$router.push("/")
         this.$forceUpdate(); 
      }
    }
  }
}
</script>

<style>

</style>
