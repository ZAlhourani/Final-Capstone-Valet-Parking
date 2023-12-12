<template>
  <div class="home">
       
    <h1>Servizio di Auto</h1>

    <p>The current temperature in Pittsburgh is {{ current.current.temperature_2m }}{{ current.current_units.temperature_2m }}</p>
  </div>
</template>

<script>
import ApiService from '../services/ApiService';

export default {

  data() {
    return {

      
      latitude: '',
      longitude: '',
      generationtime_ms: '',
      utc_offset_seconds: '',
      timezone: '',
      timezone_abbreviation: '',
      elevation: '',

    current_units: {
        time: '',
        interval: '',
        temperature_2m: ''
    },

    current: {
        time: '',
        interval: '',
        temperature_2m: ''
    }
  };
  },

  methods: {

    getCurrentWeather() {

      // const weatherData = {
      //   ...this.current_units,
      //   ...this.current
      // }

      ApiService.getWeather()
      .then(response => {
        this.current = response.data;
        this.isLoading = false;
      })
    }

  },

  computed: {
    showHomeLink() {
      return this.$route.name !== 'login';
    },
    showLotAvailabilityLink() {
      return true;
    },
    showSlipsLink() {
      return this.$store.state.token;
    },
    showLogoutLink() {
      return this.$store.state.token;
    },
  },

  created() {
    this.getCurrentWeather();
  }
  
}; 

</script>

<style>
.home {
  height: 100vh;
  width: 100vw;
  background: #0a0a0a; 
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
#userNav {
  visibility: visible;
  color: azure; 
  font-size: 25px;
  border: 2px solid #BB29EB;
  background-color: rgb(3, 3, 3);
}

h1 {
  color: #bb29eb; 
  text-shadow: 0 0 10px #bb29eb; 
  font-size: 3rem;
  margin-bottom: 0.5em;
}

p {
  color: #c1c1c1; 
  font-size: 1.5rem;
  margin-top: 0;
}
</style>

