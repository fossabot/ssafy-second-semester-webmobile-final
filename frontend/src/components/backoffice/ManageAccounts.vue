<template>
	<div>
		<h1>회원관리</h1>
		<table class = "table">
			<th>회원번호</th>
			<th>계정</th>
			<th>이름</th>
			<th>등급</th>
			<tr v-for = "(account,idx) in accountList" :key="idx"><!-- 첫번째 줄 시작 -->
		  	<td>{{idx+1}}</td>
		  	<td>{{account.email}}</td>
		  	<td>{{account.name}}</td>
		  	<td>
			 		<select name = "auth" v-model = "account.auth" @change="changeAuth(account.email,account.auth)">
			 			<option value = "1">관리자</option>
			 			<option value = "2">팀원</option>
			 			<option value = "3">방문자</option>
						<option value = "4">zzz</option>
			 		</select>
			 	</td> <!-- DB에는 1,2,3 으로 저장 -->
			</tr><!-- 첫번째 줄 끝 -->
		</table>
	</div>
</template>
<script>
import firebase from '../../apis/firebase/firebase'

export default {
	name : 'ManageAccounts',
	props: {
		accountList: {type:Array}
	},

	methods: {
		async changeAuth(accountEmail,accountAuth) {
			await firebase.getToken(accountEmail)
						  .then((token) => {
							  firebase.updateAuth(token,accountAuth)
						  })
		}
	}
}
</script>
<style>
	
</style>