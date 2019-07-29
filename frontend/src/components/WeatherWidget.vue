<template>
  <div class="mt-3">
    
      <span class="mx-1 mt-3" v-html="weather.icon"></span>
      <font size="7" class="mx-1">{{ weather.temperature }}&#8451; </font>
    
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "WeatherWidget",
  data() {
    return {
      appId: "d464f37e86ecd7f3d24e55a69968761f",
      weatherUrl: "https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=",
      weatherIconUrl: "https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/",
      weather: {
        description: "",
        icon: "",
        temperature: "",
        location: "Seoul",
        id: ""
      }
    }
  },
  methods: {
    getWeather() {
      axios.get(`${this.weatherUrl}${this.appId}`)
          .then((res)=>{
            console.log(res.data)
            this.weather.description = res.data.weather[0].description
            this.weather.temperature = parseInt(res.data.main.temp - 273.15)
            this.weather.id = res.data.weather[0].id

            this.mapWeather()
            console.log(this.weather)

          })
    },
    mapWeather() {

      let code = String(this.weather.id)
      
      if (code[0] === "2") {
        this.weather.description = "천둥번개"
        this.weather.icon = '<i class="fas fa-bolt fa-3x"></i>'
      } else if (code[0] === "3") {
        this.weather.description = "이슬비"
        this.weather.icon = '<i class="fas fa-cloud-sun-rain fa-3x"></i>'
      } else if (code[0] === "5") {
        this.weather.description = "비"
        this.weather.icon = '<i class="fas fa-cloud-showers-heavy fa-3x"></i>'
      } else if (code[0] === "6") {
        this.weather.description = "눈"
        this.weather.icon = '<i class="far fa-snowflake fa-3x"></i>'
      } else if (code[0] === "7") {
        this.weather.description = "미세먼지"
        this.weather.icon = '<i class="fas fa-smog fa-3x"></i>'
      } else if (code === "800") {
        this.weather.description = "맑음"
        this.weather.icon = '<i class="fas fa-sun fa-3x"></i>'
      } else {
        this.weather.description = "구름"
        this.weather.icon = '<i class="fas fa-cloud fa-3x"></i>'
      }

    },

    
  },
  async mounted() {
    await this.getWeather()

  }
}
</script>

<style>

</style>
