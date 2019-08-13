<template>
  <div class="container my-5 py-5 row justify-content-center">
    <div class="scene scene--page" :style="{'z-index': zflip}">
      <div class="page__face" style="color: black">{{ pages[pageIndex-3] }}</div>
      <div class="page" :class="{ 'is-flipped': isFlipped }">
        <div class="page__face page__face--front" @click="goPrev">{{ pages[pageIndex-1] }}</div>
        <div class="page__face page__face--back">{{ pages[pageIndex-2] }}</div>
      </div>
    </div>
    <div class="scene scene--page2">
      <div class="page__face" style="color: black">{{ pages[pageIndex+2] }}</div>
      <div class="page2" :class="{ 'is-flipped': isFlipped2 }">
        <div class="page2__face page2__face--front" @click="goNext">{{ pages[pageIndex] }}</div>
        <div class="page2__face page2__face--back">{{ pages[pageIndex+1] }}</div>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'TestPage',
  data() {
    return {
      isFlipped: false,
      isFlipped2: false,
      zflip: 0,
      pages: [-1,0,1,2,3,4,5,6,7,8,9,10,11,12],
      pageIndex: 3
    }
  },
  methods: {
    goPrev() {
      if (this.pageIndex == 3) {
        alert('첫 페이지 입니다.')
      } else {
        this.zflip = 5
        this.isFlipped = true
        setTimeout(function () {
          this.isFlipped = false
          this.pageIndex -= 2
          this.zflip = 0
        }.bind(this), 1000);
      }
    },
    goNext() {
      if (this.pageIndex == this.pages.length-1) {
        alert('마지막 페이지 입니다.')
      } else {

        this.isFlipped2 = true
        setTimeout(function () {
          this.isFlipped2 = false
          this.pageIndex += 2
        }.bind(this), 1000);
      }
    }
  }
}
</script>

<style>

.scene {
  width: 200px;
  height: 260px;
  border: 1px solid #CCC;
  margin: 40px 0;
  perspective: 600px;
}



.page {
  position: relative;
  width: 100%;
  height: 100%;
  cursor: pointer;
  transform-style: preserve-3d;
  transform-origin: center right;
}

.page.is-flipped {
  transition: transform 1s;
  transform: rotateY(180deg);
  transform-origin: right;
}



.page__face {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: 260px;
  color: white;
  text-align: center;
  font-weight: bold;
  font-size: 40px;
  backface-visibility: hidden;
}

.page__face--front {
  background: red;
}

.page__face--back {
  background: blue;
  transform: rotateY(180deg);
}


.page2 {
  position: relative;
  width: 100%;
  height: 100%;
  cursor: pointer;
  transform-style: preserve-3d;
  transform-origin: center right;
}

.page2.is-flipped {
  transform: rotateY(-180deg);
  transform-origin: left;
  transition: transform 1s; 
}

.page2__face {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: 260px;
  color: white;
  text-align: center;
  font-weight: bold;
  font-size: 40px;
  backface-visibility: hidden;
}

.page2__face--front {
  background: red;
}

.page2__face--back {
  background: blue;
  transform: rotateY(180deg);
}


</style>