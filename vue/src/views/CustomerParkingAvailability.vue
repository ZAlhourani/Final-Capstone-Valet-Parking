<template>
    <div class="availability-container">
      <h2>Parking Lot Availability</h2>
      <p v-if="loading">Loading...</p>
      <p v-else>We have {{ spaces.length }} spots available.</p>
    </div>
  </template>
  
  <script>
  import ParkingSpotsService from '../services/ParkingSpotsService';

  
  export default {
    data() {
      return {
        loading: true,
        spaces: []
      };
    },
    methods: {
        
    fetchAvailableParkingSpots() {
      ParkingSpotsService.getParkingSpotByAvailability(true)
        
        .then(response => {
          this.spaces = response.data;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
        });
    }
  },

  created() {
    this.fetchAvailableParkingSpots();
  }
};
</script>
  
  <style>
  .availability-container {
    text-align: center;
    margin-top: 20px;
  }
  </style>
  