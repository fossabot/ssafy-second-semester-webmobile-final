<template>
  <div class="container-fluid px-0 section" style="height: 100%">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" style="height: 100%">
        <div class="carousel-item active" style="height: 100%">
          <img src="https://source.unsplash.com/random/1600x780" class="d-block w-100" style="height: 100%" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Anyunghaseyou</h5>
            <p>Jeohye nun Bare Bears Team ipnida.</p>
          </div>
        </div>
        <div class="carousel-item" style="height: 100%">
          <img src="https://source.unsplash.com/user/erondu/1600x780" class="d-block w-100" style="height: 100%" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>HanKuk mal mottayou</h5>
            <p>HanKuk mal aouryouwoyou. HanKuk mal jal mottayou.</p>
          </div>
        </div>
        <div class="carousel-item" style="height: 100%">
          <img src="https://source.unsplash.com/collection/190727/1600x780" class="d-block w-100" style="height: 100%" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Sasil Jeonun Ice Bear ka jail joayou</h5>
            <p>Because he's so cute. I love that bear</p>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Carousel',
  methods: {},
  data () {
    return {

    }
  },
  mounted () {

  },
  props: {
    
  }

}
</script>

<style>

</style>
