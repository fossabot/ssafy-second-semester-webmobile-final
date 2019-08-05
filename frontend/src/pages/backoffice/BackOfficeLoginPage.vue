<template>
	<div id = "BackOfficeContainer">
		<div id = "BackOfficeLoginFormWrapper">
			<h3>Admin Page Login</h3>
			<form>
				<input type = "email" v-model = "adminEmail" placeholder="Admin Account ID(email)" class = "adminInput"/><br>
				<input type = "password" v-model = "adminPassword" placeholder = "Admin Account Password" class = "adminInput"/><br>
				<button id = "adminLoginButton" @click = "adminLoginProcess()">Login</button>
			</form>
		</div>
	</div>
</template>
<script>
export default {
	name : 'BackOfficeLoginPage',
	data() {
		return {
			adminEmail : '',
			adminPassword : '',
			backOfficeIsOpen : true
		}
		
	},
	mounted() {
		if(this.backOfficeIsOpen){
			document.getElementById("header").style.display = 'none'
		}
	},
	methods : {
		adminLoginProcess() {
			console.log(this.adminEmail);
			console.log(this.adminPassword);
		}
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

button#adminLoginButton{
	width : 70px;
	border : 1px solid black;
	background : blue;
	color : white;
}

form{
	display : block;
}

</style>