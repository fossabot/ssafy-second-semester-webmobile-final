<template>
	<div class="container my-5 py-5 section">
		<div style="height: 4%"></div>
		<Title title="Our Works"></Title>
		<!-- Cards -->
		<div class="row" style="height: 70%">
		  <div class="col-4">
		    <Poster class="my-4"
		            width="100%"
		            height="50%"
		            title="card1"
		            content="this is content"
		            body="Im really tired"
		            imgsrc="https://picsum.photos/300/260"></Poster>
		    
		    <Poster class="my-4"
						width="100%"
		        		height="50%"
		        		title="card2"
		        		content="this is content"
		        		body="Im really tired"
		        		imgsrc="https://picsum.photos/300/260"></Poster>
		  </div>
		  <div class="col-8 mt-4">
			<Poster width="100%"
					height="110%"
					title="card3"
					content="this is content"
					body="Im really tired"
					imgsrc="https://picsum.photos/800/550"></Poster>
		  </div>
		</div>  
	</div>
</template>

<script>
import Title from '@/components/common/Title.vue'
import Poster from '@/components/home/Poster.vue'

export default {
	name : 'PortfolioIntro',
	components : {
		Title,
		Poster
	}
}
</script>
<style>

</style>
