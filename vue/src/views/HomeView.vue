<template>
  <div class="home">
       
    <h1>Servizio di Auto</h1>

    <p>The current temperature in Pittsburgh is {{ current.current.temperature_2m }}{{ current.current_units.temperature_2m }}</p>
    <p class="description">
      Welcome to "Servizio di Auto," Ristorante Stravagante's premier valet service application, where Milanese flair meets modern convenience. Experience the blend of traditional Italian hospitality and modern efficiency.
    </p>
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
p.description {
  color: #e1e1e1; 
  font-size: 1rem;
  margin-top: 1em;
  padding: 0 2em; /* Adds padding on the sides for better readability */
  border-top: 2px solid #bb29eb; /* Adds a stylistic line above the description */
  border-bottom: 2px solid #bb29eb; /* Adds a stylistic line below the description */
  background-color: rgba(187, 41, 235, 0.1); /* Slight purple background for the description box */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
  border-radius: 5px; /* Rounded corners for the description box */
}

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

