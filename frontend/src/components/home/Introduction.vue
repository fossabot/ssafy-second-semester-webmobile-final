<template>
	<div class="container py-5 my-5 section">
		<div style="height: 10%"></div>
		<Title title="Who we are" class="mb-5"></Title>
		<!-- Description -->
		<div class="row mt-3">
			<div class="col-6" style="height:300px;">
				<h1>Here We are!</h1> <br>
				<p> 정경일 : FullStack G.O.D </p>
				<p> 이기문 : Backend One Top </p>
				<p> 박수빈 : Already AhnLab Empolyee </p>
				<p> 강성진 : Team Reader </p>
				<p> 최재형 : Pro Deploier </p>
			</div>
			<div class="col-6 mr-0 mt-3">
				<ImageOverlay 
							title="팀원들"
			                body="Order by Left"
			                imgSrc="https://i.imgur.com/qvyCoLr.png"
							style="width: 100%; height: 100%">            
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