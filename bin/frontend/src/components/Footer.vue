<template>
  <footer class="px-5 py-3 font-small blue my-bg">
    <div class='container-fluid'>
      <div class="container">
        <!-- Footer Body -->
        <div class='row justify-content-between'>
          <!-- Weather Forecast -->
          <WeatherWidget class="footer-icon"></WeatherWidget>
          <div class="mr-4 pt-4 mt-1">
            <!-- SNS -->
            <div class="row justify-content-end mr-1">
              <a href='#'><i class="fab fa-facebook fa-2x footer-icon mx-1"></i></a>
              <a href='https://lab.ssafy.com/'><i class="fab fa-github fa-2x footer-icon mx-1"></i></a>
              <a href='#'><i class="fab fa-google-plus footer-icon fa-2x mx-1"></i></a>
            </div>
            <!-- Copyright -->
            <div class="d-block justify-content-end align-items-center">© 2019 Copyright by&nbsp;
             <a href="https://github.com/ksj0206"> SSAFY2</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer> 
</template>

<script>
import WeatherWidget from '../components/WeatherWidget'

export default {
  name: 'Footer',
  components: {
    WeatherWidget
  },
  data () {
    return {
    }
  },
  methods: {
  },
}
</script>

<style>
  .footer-icon {
    color: gray;
  }
  
  .footer-icon:hover {
    text-decoration: none;
    color: black;
  }
  
</style>
