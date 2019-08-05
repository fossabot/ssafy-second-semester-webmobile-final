<template>
<div>
  ..
</div>
</template>

<script>
import firebase from '../../apis/firebase/firebase'
import { mapState, mapActions,mapMutations } from 'vuex'

export default {
  name: 'NaverCallBack',
  data () {
    return{
        naverLogin :new naver.LoginWithNaverId(
          {
            clientId: "BDMXXSAmX8cdQC95RrXg",
            callbackHandle: true
          }
        ),
    }
  },
  computed : {
    ...mapState('account',['accountEmail','accountName','loginCheck','accountAuth'])
  }
  ,
  mounted (){
    console.log("콜백 첫번째");
    
    this.naverLogin.init()
    let dt = this.naverLogin
    let router = this.$router
    let login = this.naverLogin
    let mut = this.setUser
    let signUpMut = this.setSignUp
    let act = this.isLogin
    let loginCheck = this.loginCheck
    login.getLoginStatus(async function (status) {
        if (status) {
          /* 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
          
          console.log(dt.user.getEmail());
          
          if( dt.user.getEmail() == undefined || dt.user.getEmail() == null) {
						alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
						/* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
						login.reprompt();
						return;
          }
          
          // 아이디를 우리 디비랑 확인하는 부분
          console.log("아이디를 우리 디비랑 확인하는 부분");
          let judge

          //아이디 있는지 찾아봄 (firebase)
          let userInfo=await firebase.getNaverLogin(dt.user.getEmail())
          
          console.log(userInfo);
          //토큰 생성
          let key = await firebase.getToken(dt.user.getEmail())
          
          //결과 확인
          console.log("결과");
          console.log(userInfo);
          console.log(key);

          //토큰에 해당되는 값 가져오는 방법
          let test = await firebase.getInfo(key)
          console.log(test);
      
          if(userInfo != undefined){
            console.log("true일때");
            sessionStorage.setItem('key',key)
            await mut({
              data:userInfo
              })
            await act()
            router.push("/")
          }else{
            console.log("false일때");
            await signUpMut({
              loginId:dt.user.getEmail(),
              loginName:dt.user.getName()
              })
              alert("회원가입을 해 주세요")
            router.push("/signup")
          } 
        } else {
          console.log("callback 처리에 실패하였습니다.");
        }
      })
  },
  methods: {
    ...mapActions('account',['isLogin']),
    ...mapMutations('account',['setUser','setSignUp'])
  },
}
</script>