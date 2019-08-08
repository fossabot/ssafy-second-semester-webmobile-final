<template>
	<div id = "BackOfficeContainer">
		<div id = "BackOfficeLoginFormWrapper">
			<h3>Admin Page Login</h3>
			
				<input type = "email" v-model = "adminEmail" placeholder="Admin Account ID(email)" class = "adminInput"/><br>
				<input type = "password" v-model = "adminPassword" placeholder = "Admin Account Password" class = "adminInput"/><br>
				<button id = "goHome" @click = "goHome">홈</button>
				<button id = "adminLoginButton" @click = "adminLoginProcess()">Login</button>
			
		</div>
	</div>
</template>
<script>
import firebase from '../../apis/firebase/firebase'
import {mapState,mapActions,mapMutations} from 'vuex'

export default {
	name : 'BackOfficeLoginPage',
	data() {
		return {
			adminEmail : '',
			adminPassword : '',
			backOfficeIsOpen : true
		}
		
	},
	computed: {
		...mapState('account',['accountAuth'])
	},
	mounted() {
		if(this.backOfficeIsOpen){
			document.getElementById("header").style.display = 'none'
		}
	},
	methods : {
		async adminLoginProcess() {
			await this.logout()
			const res = await firebase.getLogin(this.adminEmail,this.adminPassword)

			if(res!=null || res != undefined) {
				let token = await firebase.getToken(this.adminEmail)
				sessionStorage.setItem('key',token)
				this.setUser({data:res})
				this.isLogin()
				
				if (this.accountAuth != "1") {
					alert("관리자 권한이 없습니다.")
					document.getElementById("header").style.removeProperty("display")
					this.$router.push({name: 'HomePage'})
				} else {
					this.$router.push({name: 'BackOfficeMainPage'})
				}

			} else{
				alert("회원이 아닙니다.")
				document.getElementById("header").style.removeProperty("display")
				this.$router.push({name: 'HomePage'})
			}
		

		},
		goHome() {
			document.getElementById("header").style.removeProperty("display")
			this.$router.push({name: "HomePage"})
		},

		...mapActions('account', ['isLogin','logout','getUser']),
		...mapMutations('account',['setUser'])
	}
}
</script>
<style>
div#BackOfficeContainer{
	margin-top : 100px;
}

div#BackofficeLoginFormWrapper{
	margin : 0 auto;
	border : 3px solid gray;
}

input.adminInput{
	border : 1px solid black;
	width : 25%;
	height : 50px;
	padding : 5px;
	margin : 15px;
	border-radius: 25px;
}

input:focus {
	outline : none;
}

button#adminLoginButton,
button#goHome {
	width : 70px;
	border : 1px solid black;
	background : blue;
	color : white;
}

form{
	display : block;
}

</style>