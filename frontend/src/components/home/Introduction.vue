<template>
	<div class="container py-5 my-5 section">
		<Title title="Who we are"></Title>
		<!-- Description -->
		<div class="row">
			<div class="col-6" style="height:300px;">
				<h1>Here We are</h1>
				<p> There's nothing to say specially </p>
			</div>
			<div class="col-6 mr-0">
				<ImageOverlay title="Title"
			                body="What is all of it for?"
			                imgSrc="https://picsum.photos/555/500">            
			  </ImageOverlay>
			</div>
		</div>
	</div>
</template>
<script>
import Title from '@/components/common/Title.vue'
import ImageOverlay from '@/components/home/ImageOverlay.vue'

export default {
	name : 'Introduction',
	data() {
		return {

		}
	},
	components : {
		Title,
		ImageOverlay
	}
}
</script>
<style>

</style>