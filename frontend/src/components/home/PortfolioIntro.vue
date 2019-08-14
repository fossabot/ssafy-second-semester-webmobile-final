<template>
	<div class="container my-5 py-5 section">
		<div style="height: 4%"></div>
		<Title title="Our Works" class="mt-4"></Title>
		<!-- Cards -->
		<div class="row" style="height: 70%">
		  <div class="col-4">
		    <Poster class="my-4"
		            width="100%"
		            height="45%"
		            :title="postsBest.postTitle"
		            :content="postsBest.accountName"
		            :body="postsBest.postContent"
		            :imgsrc="postsBest.postThumbnailUrl"
					:cardLink="`https://ssafy-barebears.firebaseapp.com/posts/${postsBest.postId}`"></Poster>
		    
		    <Poster class="my-4"
						width="100%"
		        		height="45%"
						:title="postsSecond.postTitle"
						:content="postsSecond.accountName"
						:body="postsSecond.postContent"
						:imgsrc="postsSecond.postThumbnailUrl"
						:cardLink="`https://ssafy-barebears.firebaseapp.com/posts/${postsSecond.postId}`"></Poster>
		  </div>
		  <div class="col-8 mt-4">
			<Poster width="100%"
					height="100%"
					:title="portfoliosBest.portfolioTitle"
					:content="portfoliosBest.accountName"
					:body="portfoliosBest.portfolioContent"
					:imgsrc="portfoliosBest.portfolioThumbnailUrl"
					:cardLink="`https://ssafy-barebears.firebaseapp.com/portfolios/${portfoliosBest.portfolioId}`"></Poster>
		  </div>
		</div>  
	</div>
</template>

<script>
import Title from '@/components/common/Title.vue'
import Poster from '@/components/home/Poster.vue'
import mainServices from '@/apis/mainservice/mainServices.js'

export default {
	name : 'PortfolioIntro',
	components : {
		Title,
		Poster
	},
	data() {
		return {
			portfoliosBest: {},
			postsBest: {},
			postsSecond: {},
		}
	},
	
	async mounted() {
		await mainServices.getPortfoliosBest()
						  .then((bests) => {
							  this.portfoliosBest = bests[0]
						  })
		await mainServices.getPostsBest()
						  .then((bests) => {
							  this.postsBest = bests[0]
							  this.postsSecond = bests[1]
						  })
	}
}
</script>
<style>

</style>
