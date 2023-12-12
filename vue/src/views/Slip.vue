<template>
  <div class="slips-container">
    <h1>Parking Slips</h1>
    <table>
      <thead>
        <tr>
          <th>Slip Number</th>
          <th>Patron Name</th>
          <th>Car</th>
          <th>Arrival Time</th>
          <th>Departure Time</th>
          <th>Hourly Price</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="slip in slips" :key="slip.slip_number">
          <td>{{ slip.slipNumber }}</td>
          <td>{{ slip.patronId.name }}</td>
          <td>{{ slip.carId.carId }}</td>
          <td>{{ slip.arrivalTime }}</td>
          <td>{{ slip.departureTime }}</td>
          <td>{{ slip.hourlyPrice }}</td>
          <td>{{ total(slip) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
   <button type="submit" v-on:click="submitCarPickUp">Request Pickup</button>
  <!-- <router-link v-bind:to="{ name: 'request-pickup' }" v-show="$route.name !== 'login'">Request a Pickup</router-link> -->
</template>

<script>
import SlipsService from '../services/SlipsService';
import CheckInDtoService from '../services/CheckInDtoService';

export default {
  data() {
    return {
      slips: [

      ]
    };
  },
  created() {
      SlipsService.getAllSlipsList().then(data => {
        this.slips = data.data;
      });
    },
  methods: {

    submitCarPickUp(slip) {
      CheckInDtoService.checkOut(slip) 
      .then(response => {
        this.slips = response.data;
      })
    },

    formatTime(time) {
      if (!time) return 'N/A';
    },

    total(slip) {
      
      if (slip.departureTime != null) { // user already picked it up
        return slip.total;
      }

      const assumedDepartureTime = new Date();
      const parkingDurationInHours = (assumedDepartureTime - slip.arrival_time) / (1000 * 60 * 60);
      const parkingTotal = parkingDurationInHours * slip.hourlyPrice;

      // const slipInfo = SlipsService.getSlipBySlipNumber();

      // const parkingDurationInHours = (this.departure_time - this.arrival_time) / (1000 * 60 * 60);

      // const parkingTotal = parkingDurationInHours * this.hourly_price;

      return parkingTotal;
    }
  }
};
</script>

<style>
h1 {
  font-size: 20px;
  text-align: center;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
  color: white;
  font-weight: 500;
}

th {
  background-color: #f4f4f4;
}
</style>

