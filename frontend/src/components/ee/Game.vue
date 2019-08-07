<template>
    <div class="row">
        <div class="col">
        안녕하세요 이스터에그에 오신걸 환영합니다 
        </div>
        <div class="col">
            <a href="#" @click="update('jae637@naver.com','3')">나가기</a>
        </div>
        
    </div>
</template>

<script>
import { mapState, mapActions,mapMutations } from 'vuex'
import firebase from "../../apis/firebase/firebase"

export default{
    name : 'EsterEgg',
    components : {
    },
    computed : {
        ...mapState('account',['accountEmail','accountName','accountAuth','loginCheck'])
    },
    mounted() {
    },
    methods:{
        ...mapActions('account', ['isLogin','logout','getUser']),
        async update(email,auth){
            let token=await firebase.getToken(email)
            console.log("update");
            firebase.updateAuth(token,auth)
        }
    }
}
</script>
