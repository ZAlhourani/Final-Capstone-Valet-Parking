<template>
  <div class="home">
    <div class="image-text-container">
    <div class="image-container">
      <img src="Parking Img.png" alt="Parking Valet Logo" :style="{width: '800px', height: '780px'}">
    </div>
  <div class="text-container">
       
    <h1>Servizio di Auto</h1>

    <p class="description">Welcome to Servizio di Auto, where unparalleled luxury meets culinary excellence. As an integral part of our Michelin-starred Italian haven, our bespoke valet service is designed to elevate every aspect of your dining experience.</p>
<p class="p2">Step into a world of sophistication and seamless hospitality as our valet attendants cater to your every need, ensuring a smooth and elegant arrival. Our commitment to excellence extends beyond the culinary realm, reflecting the same dedication to refinement that has earned our restaurant its prestigious Michelin star.</p>
<p class="p3">Indulge in the convenience of our valet services, where attention to detail is paramount. From the moment you entrust us with your vehicle, rest assured that it will be handled with the utmost care and professionalism. Our valet team is here to create a seamless transition from the bustling world outside to the tranquil ambiance within.</p>
<p class="p4">At Servizio di Auto, we believe that every element contributes to a truly extraordinary dining experience. Allow us to set the stage for an evening of gastronomic delight, complemented by the luxury and sophistication that define our Michelin-starred Italian restaurant.</p>
<p class="p5">Experience the epitome of service and style – both on your plate and in our valet services. Welcome to Servizio di Auto, where luxury is not just a service; it's an integral part of our culinary narrative.</p>
<p class="temp">The current temperature in Pittsburgh is {{ current.current.temperature_2m }}{{ current.current_units.temperature_2m }}</p>
  </div>
</div>
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
.image-text-container {
  display: flex;
}
.image-container {
  width: 800px;
  height: 800px;
  max-width: 100%;
  max-height: 100%;
}
.text-container {
  flex: 1;
  padding-left: 20px;
}
.home {
  height: 100vh;
  width: 100vw;
  background: 0a0a; 
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
/* #userNav {
  visibility: visible;
  color: azure; 
  font-size: 25px;
  border: 2px solid #BB29EB;
  background-color: rgb(3, 3, 3);
} */

/* #nav {
  background-color: #333; 
  padding: 10px 20px; 
  text-align: center; 
} */

/* #nav a {
  color: #fff; 
  text-decoration: none; 
  padding: 5px 10px; 
  transition: background-color 0.3s, color 0.3s; 
}

#nav a:hover {
  background-color: #bb29eb; 
   color: lightpink;  
  border-radius: 4px; 
} */

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

.description{
  margin: 40px;
  font-size: 20px;
  line-height: 1.5;
  color: white; 
  margin-bottom: 15px; 
  font-style: italic;
}

.p2, .p3, .p4, .p5{
  margin: 40px;
  font-size: 20px;
  line-height: 1.5;
  color: white; 
  margin-bottom: 15px; 
  font-style: italic;
}

.temp{
  margin: 40px;
  font-size: 40px;
  line-height: 1.5;
  color: #bb29eb; 
  margin-bottom: 15px; 
  font-style: italic;
  text-shadow: 0 0 10px blue;
}
</style>

