<template>
	<div>
		<h1>회원관리</h1>
		<table class = "table">
			<th>회원번호</th>
			<th>계정</th>
			<th>이름</th>
			<th>등급</th>
			<tr v-for = "account in accounts"><!-- 첫번째 줄 시작 -->
		  	<td>{{account.account_id}}</td>
		  	<td>{{account.account_email}}</td>
		  	<td>{{account.account_name}}</td>
		  	<td>
			 		<select name = "auth" v-model = "account.account_auth">
			 			<option value = "1">관리자</option>
			 			<option value = "2">팀원</option>
			 			<option value = "3">방문자</option>
			 		</select>
			 	</td> <!-- DB에는 1,2,3 으로 저장 -->
			</tr><!-- 첫번째 줄 끝 -->
		</table>
	</div>
</template>
<script>
export default{
	name : 'ManageAccounts',
	data() {
		return {
			accounts : [
				{
					account_id : 100,
					account_email : "100abc@abc.com",
					account_name : "100name",
					account_auth : 1
				},
				{
					account_id : 101,
					account_email : "101abc@abc.com",
					account_name : "101name",
					account_auth : 2
				},
				{
					account_id : 102,
					account_email : "102abc@abc.com",
					account_name : "102name",
					account_auth : 3
				},
				{
					account_id : 103,
					account_email : "103abc@abc.com",
					account_name : "103name",
					account_auth : 2
				},
				{
					account_id : 104,
					account_email : "104abc@abc.com",
					account_name : "104name",
					account_auth : 3
				}
			]
		}
	},

	mounted() {
		
	}
}
</script>
<style>
	
</style>