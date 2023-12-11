<template>
  <div class="slips-container">
    <h1>Parking Slips</h1>
    <table>
      <thead>
        <tr>
          <th>Slip Number</th>
          <th>Patron Name</th>
          <!-- <th>Car</th> -->
          <th>Arrival Time</th>
          <th>Departure Time</th>
          <th>Hourly Price</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="slip in slips" :key="slip.slip_number">
          <td>{{ slip.slip_number }}</td>
          <td>{{ slip.patron_id }}</td>
          <!-- <td>{{ slip.car_id }}</td> -->
          <td>{{ formatTime(slip.arrival_time) }}</td>
          <td>{{ formatTime(slip.departure_time) }}</td>
          <td>{{ slip.hourly_price = 5.00 }}</td>
          <td>{{ slip.total }}</td>
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
  methods: {
    formatTime(time) {
      if (!time) return 'N/A';
    },

    total() {
      const slipInfo = SlipsService.getSlipBySlipNumber();

      const parkingDurationInHours = (this.departure_time - this.arrival_time) / (1000 * 60 * 60);

      const parkingTotal = parkingDurationInHours * this.hourly_price;

      return parkingTotal;
    },
    created() {
      let patronID = -1;
      PatronsService.getPatronIdByUserId(this.$store.state.user.id)
      .then
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

