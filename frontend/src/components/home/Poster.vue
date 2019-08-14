<template>
  <div class="flip-card rounded border-0" :style="{ width:width,height:height }" >
    <div class="flip-card-inner">
      <div class="flip-card-front rounded">
        <img :src="imgsrc" alt="Avatar" style="width:100%; height:100%; background-color: rgb(179, 224, 255)" class="rounded">
      </div>
      <div class="flip-card-back rounded" style="width:100%; height:100%">
        <div class="card" style="width:100%; height:100%">
          <div class="card-body">
            <h5 class="card-title" style="color:black">{{ title }}</h5>
            <h6 class="card-subtitle mb-2 text-muted">by {{ content }}</h6>
            <hr>
            <p class="card-text" style="color: black">{{ body }}</p>
            <div>
              <a target="_blank" :href="cardLink" class="card-link">게시글 보러가기</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Poster",
  props: {
    width: {type:String},
    height: {type:String},
    title: {type:String},
    content: {type:String},
    body: {type:String},
    imgsrc: {type:String},
    cardLink: {type:String}
  }

}
</script>

<style>
.flip-card {
  background-color: transparent;
  width: 300px;
  height: 200px;
  border: 1px solid #f1f1f1;
  perspective: 1000px; /* Remove this if you don't want the 3D effect */
}

/* This container is needed to position the front and back side */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

/* Do an horizontal flip when you move the mouse over the flip box container */
.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

/* Position the front and back side */
.flip-card-front, .flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
}

/* Style the front side (fallback if image is missing) */
.flip-card-front {
  background-color: #bbb;
  color: black;
}

/* Style the back side */
.flip-card-back {
  background-color: whitesmoke;
  color: white;
  transform: rotateY(180deg);
}

</style>
