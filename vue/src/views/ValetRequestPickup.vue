<template>
  <div class="valet-request-pickup-form">
    <h2>Valet Request Car Pickup</h2>
    <form @submit.prevent="submitValetPickupRequest">
      <div class="form-group1">
        <label class="id-label" for="car-id">Car ID:</label>
        <input id="car-id" v-model="pickupRequest.carId" required>
      </div>

      <div class="form-group1">
        <label class="patron-label" for="patron-name">Patron Name:</label>
        <input id="patron-name" v-model="pickupRequest.patronName">
      </div>


      <button type="submit">Request Pickup</button>
    </form>
  </div>
</template>

<script>

import SlipsService from '../services/SlipsService';
import CheckInDtoService from '../services/CheckInDtoService';

export default {

  data() {
    return {
      pickupRequest: {
        carId: '',
        patronName: '' 
      }
    };
  },
  methods: {

    total(slip) {
      const parkingDurationInHours = (new Date(slip.departureTime) - new Date(slip.arrivalTime)) / (1000 * 60 * 60);
      const parkingTotal = parkingDurationInHours * slip.hourlyPrice;

      return parkingTotal;
    },

    async submitValetPickupRequest() {
      try {
        var today = new Date();

        // Convert to ISO 8601 format
        var isoString = today.toISOString();

        // Remove the milliseconds and timezone information
        isoString = isoString.slice(0, -5);

        SlipsService.getSlipByCarId(this.pickupRequest.carId).then(data => {
          let slips = data.data;
          let slipToUpdate = {};
          slips.forEach(slip => {
              if(slip.departureTime === null){
                slipToUpdate = slip;
              }
          });

          slipToUpdate.departureTime = today;
          slipToUpdate.total = this.total(slipToUpdate);

          slipToUpdate.patronId.userId.authorities = toString(slipToUpdate.patronId.userId.authorities);
          slipToUpdate.carId.patronId.userId.authorities = toString(slipToUpdate.carId.patronId.userId.authorities);
          
          CheckInDtoService.checkOut(slipToUpdate).finally(()=> {
            this.pickupData = { location: '', time: '', notes: '' };
            alert('Your pickup request has been submitted.');
          });
        })

      } catch (error) {
        console.error('Error submitting valet pickup request:', error);
      }
    }
  }
};
</script>

<style>

.id-label, .patron-label{
  color: black;
  font-weight: 500;
}
.valet-request-pickup-form {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 30px;
}

.valet-request-pickup-form h2 {
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

button[type="submit"] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.3s;
}

button[type="submit"]:hover {
  background-color: #45a049;
}


</style>
