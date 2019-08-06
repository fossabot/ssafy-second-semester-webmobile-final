<template>
    <div>
        안녕하세요 이스터에그에 오신걸 환영합니다<a href="#" @click="logout">나가기</a>
        
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
    }
}
</script>
