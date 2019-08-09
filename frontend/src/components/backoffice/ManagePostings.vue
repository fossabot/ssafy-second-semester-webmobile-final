<template>
	<div>
		<h1>블로그 내 게시글 수</h1>
			<div id = "BackOfficeMainBannerWrapper">
			<div style = "width : 30%; height : 100%;">
				포스팅 수 : {{postingCounts}}
			</div>
			<div style = "width : 30%; height : 100%;">
				포트폴리오 수 : {{portfolioCounts}}
			</div>
		</div>
	</div>
</template>
<script>
import mainServices from '../../apis/mainservice/mainServices'

export default{
	name : 'ManagePostings',
	data() {
		return {
			postingCounts : "",
			portfolioCounts : ""
		}
	},
	async created() {
		await mainServices.getPortfoliosCount()
						  .then((portfoliosCount) => {
							this.portfolioCounts = portfoliosCount
						  })
		await mainServices.getPostsCount()
						  .then((postsCount) => {
							  this.postingCounts = postsCount
						  })
	}
}
</script>
<style>
	
</style>