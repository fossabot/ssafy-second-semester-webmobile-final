<template>
	<div class="container my-5 py-5 section">
		<Title title="Our Works"></Title>
		<!-- Cards -->
		<div class="row">
		  <div class="mr-4">
		    <Poster class="my-4"
		            width="300px"
		            height="260px"
		            title="card1"
		            content="this is content"
		            body="Im really tired"
		            imgsrc="https://picsum.photos/300/260"></Poster>
		    
		    <Poster class="my-4"
								width="300px"
		        		height="260px"
		        		title="card2"
		        		content="this is content"
		        		body="Im really tired"
		        		imgsrc="https://picsum.photos/300/260"></Poster>
		  </div>
		  <Poster class="my-4 ml-3 d-none d-md-none d-sm-none d-lg-none d-xl-block animatable fadeInUp"
		          width="800px"
		          height="550px"
		          title="card3"
		          content="this is content"
		          body="Im really tired"
		          imgsrc="https://picsum.photos/800/550"></Poster>
		</div>  
	</div>
</template>

<script>
import Title from '@/components/common/Title.vue'
import Poster from '@/components/home/Poster.vue'

export default {
	name : 'PortfolioIntro',
	data() {
		return {

		}
	},
	components : {
		Title,
		Poster
	}
}
</script>
<style>

</style>
