<template>
  <div class="availability-container">
    <h2>Parking Lot Availability</h2>
    <p v-if="loading">Loading...</p>
    <div v-else>
      <p v-if="spaces.length > 0">We have {{ spaces.length }} spots available.</p>
      <div v-else class="alert alert-full">Lot is full</div>
    </div>
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

  
.alert-full {
  color: white;
  background-color: red;
  padding: 20px;
  margin: 20px 0;
  font-size: 20px;
  font-weight: bold;
  border: 1px solid darkred;
}
  </style>
  