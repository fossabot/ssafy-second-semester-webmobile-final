<template>
<div class="container">
    <div class="row">
        <div class="col">
        같은 그림 찾기
        </div>
        <div class="col">
            <a href="#" @click="exit">나가기</a>
        </div>
    </div>
    <div class="row">
        <Game />
    </div>
</div>
</template>

<script>
import { mapState, mapActions,mapMutations } from 'vuex'
import firebase from "../../apis/firebase/firebase"
import Game from "../../components/ee/Game"

export default{
    name : 'EsterEgg',
    components : {
        Game
    },
    computed : {
        ...mapState('account',['accountEmail','accountName','accountAuth','loginCheck'])
    },
    mounted() {
    },
    methods:{
        ...mapActions('account', ['isLogin','logout','getUser']),
        async exit(){
            await this.logout()
            // setTimeout(location.reload(),1000)
            location.reload()
            
        }
        // 권한 업데이트 함수
        // async update(email,auth){
        //     let token=await firebase.getToken(email)
        //     console.log("update");
        //     firebase.updateAuth(token,auth)
        // }
    }
}
</script>
