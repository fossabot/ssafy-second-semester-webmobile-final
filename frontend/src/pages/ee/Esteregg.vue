<template>
<div class="container">
    <div class="row">
        <div class="col">
        안녕하세요 이스터에그에 오신걸 환영합니다 
        </div>
        <div class="col">
            <a href="#" @click="logout">나가기</a>
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
        
        // 권한 업데이트 함수
        // async update(email,auth){
        //     let token=await firebase.getToken(email)
        //     console.log("update");
        //     firebase.updateAuth(token,auth)
        // }
    }
}
</script>
