<template>
  <div class="availability-container">
    <h2>Parking Lot Availability</h2>
    <p v-if="loading">Loading...</p>
    <div v-else>
  <p v-if="spaces.length > 0">We have {{ spaces.length }} spot{{ spaces.length === 1 ? '' : 's' }} available. <font-awesome-icon :icon="['far', 'face-smile']" /></p>
      <div v-else class="alert alert-full">
        Lot is full
        <font-awesome-icon :icon="['far', 'face-frown']" />
      </div>
    </div>
  </div>
</template>
<script>
import { library } from '@fortawesome/fontawesome-svg-core';
import { far } from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import ParkingSpotsService from '../services/ParkingSpotsService';
library.add(far);
export default {
  components: {
    'font-awesome-icon': FontAwesomeIcon
  },
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
          console.error('Error fetching parking spots:', error);
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
.font-awesome-icon {
  margin-left: 10px;
  color: white; /* Or any color that suits your alert */
}
.availability-container {
  text-align: center;
  margin-top: 20px;
  font-family: 'Roboto', sans-serif;
}
.availability-container h2 {
  color: #BB29EB;
  text-shadow: 0 0 10px #BB29EB;
  font-size: 3rem;
  margin-bottom: 0.5em;
}
.availability-container p {
  color: White;
  font-size: 35px;
}
.alert-full {
  color: white;
  background-color: red;
  padding: 20px;
  margin: 20px 0;
  font-size: 35px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s;
}
.alert-full:hover {
  background-color: darkred;
}
@media (max-width: 768px) {
  .availability-container {
    margin-top: 10px;
  }
  .alert-full {
    padding: 15px;
    font-size: 18px;
  }
}
.spaces {
  font-size: 20px;
}
  </style>