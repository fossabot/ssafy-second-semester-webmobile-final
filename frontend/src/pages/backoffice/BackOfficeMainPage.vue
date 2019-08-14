<template>
	<div id = "BackOfficeMainContainer">
		<nav id = "BackOfficeNavigation">
			<ul id = "BackOfficeNavigationMenuList">
				<li @click = "clickManage('accounts')">회원관리</li>
				<li @click = "clickManage('postings')">게시글 수 조회</li>
				<li @click = "clickManage('weblog')">웹로그</li>
				<li @click="adminLogout">로그아웃</li>
			</ul>
		</nav>

		<div id = "BackOfficeMainContents">
			<div v-if = "accounts">
				<ManageAccounts :accountList="accountList"></ManageAccounts>
			</div>

			<div v-else-if = "postings">
				<ManagePostings></ManagePostings>
			</div>

			<div v-else-if = "weblog">
				<ManageWeblog></ManageWeblog>
			</div>
			
		</div>
	</div>
</template>

<script>
import ManageAccounts from '@/components/backoffice/ManageAccounts.vue'
import ManagePostings from '@/components/backoffice/ManagePostings.vue'
import ManageWeblog from '@/components/backoffice/ManageWeblog.vue'
import firebase from '../../apis/firebase/firebase'
import {mapActions} from 'vuex'
import { constants } from 'crypto';

export default{
	name : 'BackOfficeMainPage',

	components : {
		ManageAccounts,
		ManagePostings,
		ManageWeblog
	},

	data() {
		return {
			accountList: [],
			accounts : '',
			postings : '',
			weblog : '',
			backOfficeIsOpen : true
		}
	},

	mounted () {
		firebase.getAccounts()
					.then((res) => {
						this.accountList = res
					})
		this.accounts = true;
		this.postings = false;
		this.weblog = false;

		if(this.backOfficeIsOpen){
			document.getElementById("header").style.display = 'none';
		}
	},

	methods : {
		clickManage(tab) {
			if(tab == "accounts"){
				this.accounts = true;
				this.postings = false;
				this.weblog = false;

				return;
			}

			if(tab == "postings"){
				this.accounts = false;
				this.postings = true;
				this.weblog = false;

				return;
			}

			if(tab == "weblog"){
				this.accounts = false;
				this.postings = false;
				this.weblog = true;

				return;
			}
		},

		...mapActions('account',['logout']),

		async adminLogout() {
			await this.logout()
			document.getElementById("header").style.removeProperty("display")
			this.$router.push({ name: 'HomePage'})
		}
	}
	
}
</script>
<style>
div#BackOfficeMainContainer{
	width : 100%;
	height : 1000px;
}
ul#BackOfficeNavigationMenuList{
	padding : 0px;
}

nav#BackOfficeNavigation{
	width : 15%;
	height : 100%;
	background : #333333;
	color : white;
	padding-top : 15%;
	float : left;
}
ul#BackOfficeNavigationMenuList li {
	list-style : none;
	margin-top : 50px;
}

ul#BackOfficeNavigationMenuList li:hover{
	text-decoration: underline;
	cursor : pointer;
}

div#BackOfficeMainContents{
	padding-top : 100px;
}

div#BackOfficeMainBannerWrapper div{
	padding-top : 50px;
	display : inline-block;
}

table.table{
	width : 80%;
	margin-top : 50px;
}

select{
	width : 30%;
	height : 30px;
	padding : 3px;
}
</style>