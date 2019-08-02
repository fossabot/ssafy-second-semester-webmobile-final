<template>
  <div class="container-fluid px-0 fixed-top" id = "header">
    <!-- <modal v-if="showModal" @close="showModal = false"> -->
    <LoginModal :showModal="showModal" @signIn="signIn" @close="close" :naverLogin="naverLogin"></LoginModal>
    <div class="container-fluid px-0">
      
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="text-align-middle">
        <router-link :to="{ name: 'HomePage' }" class="navbar navbar-brand  d-inline-flex">
          <i class="fas fa-paw fa-3x"></i>
           <h2 class="d-inline ml-3 mb-0 text-align-middle"> Bare Bears</h2>
        </router-link>
      </div>
        <button class=" navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
        <div class="nav navbar-nav ml-auto">
          <router-link :to="{ name: 'HomePage' }" class="nav-item nav-link h4">Home</router-link>
          <router-link :to="{ name: 'PortfolioListPage' }" class="nav-item nav-link h4">Portfolio</router-link>
          <router-link :to="{ name: 'PostListPage' }" class="nav-item nav-link h4">Post</router-link>
          <router-link :to="{ name: '' }" class="nav-item nav-link h4">Repo</router-link>
          <!-- <router-link :to="{ name: 'loginpage', params: {} }" class="nav-item nav-link h6">Login</router-link> -->
          <a v-if="!loginCheck" class="nav-item nav-link h6" @click="showModal = true" >Login</a>
          <!-- <a v-if="userlogin" class="nav-item nav-link h6" @click="logOut" >환영합니다 {{user.id}}님</a> -->
          <a v-else class="nav-item nav-link h6" @click="logout" >환영합니다 {{accountName}}님</a>

          <router-link v-if="!loginCheck" :to="{ name: 'SignUpPage' }" class="nav-item nav-link h6">SignUp</router-link>

          
        </div>
      </div>
    </nav>
  </div>
  </div>
</template>

<script>
import LoginModal from './LoginModal.vue'
import { mapState, mapActions,mapMutations } from 'vuex'
import firebase from "../../apis/firebase/firebase"

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
  computed : {
    ...mapState('account',['accountEmail','accountName','accountAuth','loginCheck'])
  },
  mounted() {
    this.getUser({key:sessionStorage.getItem('key')})
    this.isLogin()
    console.log(this.loginCheck); // 이거 왜 다르져
  },
  updated(){
    this.naverLogin.init()
  },
  methods: {
    ...mapActions('account', ['isLogin','logout','getUser']),
    ...mapMutations('account', ['setUser','setInit']),
    close(){
      // 정상 종료
      this.showModal =false
    },
    async signIn(email,password){
      let data = await firebase.getLogin(email,password)
      if(data!=null || data != undefined){
        let token = await firebase.getToken(email)
        sessionStorage.setItem('key',token)
        this.setUser({data:data})
        this.isLogin()
        this.showModal=false
      }else{
        alert("아이디 비밀번호를 확인하세요 죽기싫으면")
      }
    }
  }
}
</script>

<style>
nav.navbar-light {
  background : white;
}


</style>
