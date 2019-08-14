<template>
  <div class="container-fluid">
    <div>
      <img src="https://media1.tenor.com/images/2bf5e7237248ae65f31c85eba6ce3539/tenor.gif?itemid=13956215" style="width: 20%; position:absolute; top:45%; left: 38%;">
      <transition
        @before-enter="grizzlyBounce"
        @enter="enter"
        @after-enter="curtainCall"
        @leave="grizzlyBye"
      >
        <div v-show="grizzlyShow" style="position:absolute; top:34%; z-index:3">
          <img style="width:5%" src="https://i.imgur.com/flNOAc7.png"/>
        </div>
      </transition>
      <transition
        @before-enter="pandaBounce"
        @enter="enter"
        @after-enter="callGrizzly"
        @leave="pandaBye"
      >
        <div v-show="pandaShow" style="position:absolute; top:40%; z-index:2">
          <img style="width:5%" src="https://i.imgur.com/lzceZSq.png"/>
        </div>
      </transition>
      <transition
        @before-enter="icebearBounce"
        @enter="enter"
        @after-enter="callPanda"
        @leave="icebearBye"
      >
        <div v-show="icebearShow" style="position:absolute; top:45%; z-index:1">
          <img style="width:5%" src="https://i.imgur.com/jRpUDID.png"/>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Loading',
  data() {
    return {
      icebearShow: false,
      pandaShow: false,
      grizzlyShow: false,
      
    }
  },
  methods: {
    enter(el,done) {
      setTimeout( function() {
        done()
      },700)
      
    },
    icebearBounce(el) {
      el.classList.add("animated");
      el.classList.add("bounceInDown");
    },
    callPanda(el) {
      this.pandaShow = true
    },
    icebearBye(el) {
      el.classList.add("animated");
      el.classList.add("bounceOutUp");
    },
    pandaBounce(el) {
      el.classList.add("animated");
      el.classList.add("bounceInDown");
    },
    callGrizzly(el) {
      this.grizzlyShow = true
    },
    pandaBye(el) {
      el.classList.add("animated");
      el.classList.add("bounceOutUp");
    },
    grizzlyBounce(el) {
      el.classList.add("animated");
      el.classList.add("bounceInDown");
    },
    curtainCall(el) {
      setTimeout(function() {
        this.icebearShow = false
        this.pandaShow = false
        this.grizzlyShow = false
      }.bind(this), 500);
      
    },
    grizzlyBye(el) {
      el.classList.add("animated");
      el.classList.add("bounceOutUp");
    }

  },
  mounted() {
    this.icebearShow = true
  }
}
</script>

<style>

</style>