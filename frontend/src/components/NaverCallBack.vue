<template>
<div>
  callback 처리중입니다. 이 페이지에서는 callback을 처리하고 바로 main으로 redirect하기때문에 이 메시지가 보이면 안됩니다.
</div>
</template>

<script>
import Inner from '../apis/Inner'
import axios from 'axios'

export default {
  name: 'NaverCallBack',
  data () {
    return{
        naverLogin :new naver.LoginWithNaverId(
          {
            clientId: "BDMXXSAmX8cdQC95RrXg",
            callbackHandle: true
            /* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
          }
        ),
        url: "http://localhost:8080/api/bears/accounts/",
    }
  },
  mounted (){
    console.log("콜백 첫번째");
    
    this.naverLogin.init()
    let dt = this.naverLogin
    let ur = this.user
    let router = this.$router
    let login = this.naverLogin
    let url = this.url
    login.getLoginStatus(async function (status) {
        if (status) {
          /* 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
          
          console.log(dt.user.getEmail());
          ur.id=dt.user.getEmail();
          
          if( ur.id == undefined || ur.id == null) {
						alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
						/* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
						login.reprompt();
						return;
          }
          
          // 아이디를 우리 디비랑 확인하는 부분
          console.log("아이디를 우리 디비랑 확인하는 부분");
          let judge
          //아이디 있는지 찾아봄
          await axios.get(url+ur.id)
            .then((res)=>{
              //? 데이터 불러오는 방법 까먹음
              judge=res.status;
              console.log(res.status);
              if(judge*1>=200 &&judge*1<400)
                judge=true
              else
                judge=false
            }).catch((res)=>{
              judge=false
            })
          
          console.log("아이디를 우리 디비랑 확인하는 부분");
      
          if(judge){
            console.log("true일때");
            sessionStorage.setItem('id',ur.id)
            ur.name= dt.user.getName();
            router.push("/")
          }else{
            console.log("false일때");
            ur.name=dt.user.getName();
            ur.id=dt.user.getEmail();
            router.push("/signup")
          }
        } else {
          console.log("callback 처리에 실패하였습니다.");
        }
      })
  },
  props:{
     user : {
        id:"",
      }
  },
  methods: {
  },
}
</script>