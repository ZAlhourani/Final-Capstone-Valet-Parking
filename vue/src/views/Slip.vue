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
          <td>{{ slip.carid }}</td>
          <td>{{ formatTime(slip.arrivalTime) }}</td>
          <td>{{ formatTime(slip.departureTime) }}</td>
          <td>{{ slip.hourlyPrice }}</td>
          <td>{{ total(slip) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <router-link v-bind:to="{ name: 'request-pickup' }" v-show="$route.name !== 'login'">Request a Pickup</router-link>
</template>

<script>
import SlipsService from '../services/SlipsService';
import PatronsService from '../services/PatronsService.js'

export default {
  data() {
    return {
      slips: [

      ]
    };
  },
  created() {
    if ( this.$store.state.user.authorities[0].name === 'ROLE_USER') {
      
      PatronsService.getPatronIdByUserId(this.$store.state.user.id).then(data => {
        SlipsService.getSlipByPatronId(data.data.patronId).then(data => {
          this.slips = data.data;
        })
      })
    //  SlipsService.getSlipsByPatronId()
    // } else {
      SlipsService.getAllSlipsList().then(data => {
        this.slips = data.data;
      });
    }
    },
  methods: {

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
}

th {
  background-color: #f4f4f4;
}
</style>

