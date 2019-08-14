<template>     
  <div class="imagebody rounded">
    <img :src="imgSrc" alt="Avatar" class="image rounded" style=" height:100%">
    <div class="middle">
      <div class="text">
        <font size="6">{{ title }}</font>
        <br>
        <br>
        {{ body }}
        </div>
    </div>
</div>
  
</template>

<script>
export default {
  name: "ImageOverlay",
  props: {
    imgSrc: {type: String},
    title: {type: String},
    body: {type: String}
  }
}
</script>

<style>
.imagebody {
  position: relative;
  width: 100%;
}

.image {
  opacity: 1;
  display: block;
  width: 100%;
  height: auto;
  transition: .5s ease;
  backface-visibility: hidden;
}

.middle {
  transition: .5s ease;
  opacity: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  text-align: center;
}

.imagebody:hover .image {
  opacity: 0.3;
}

.imagebody:hover .middle {
  opacity: 1;
}

.text {
  /* background-color: #4CAF50; */
  color: black;
  font-size: 20px;
  padding: 16px 32px;
}

</style>
