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
          <td>${{ total(slip) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="links">
  <router-link v-bind:to="{ name: 'request-pickup' }" v-show="$route.name !== 'login' && $store.state.user.authorities[0].name === 'ROLE_USER'">Request a Pickup</router-link>
  <router-link v-bind:to="{ name: 'valet-request-pickup' }" v-show="$route.name !== 'login' && $store.state.user.authorities[0].name === 'ROLE_VALET'">Request a Pickup</router-link>
</div>
</template>

<script>
import SlipsService from '../services/SlipsService';
import PatronsService from '../services/PatronsService';

export default {
  data() {
    return {
      slips: [

      ]
    };
  },
  created() {
    console.log(this.$store.state.user)

    if ( this.$store.state.user.authorities[0].name === 'ROLE_USER') {
      
      PatronsService.getPatronIdByUserId(this.$store.state.user.id).then(data => {
        SlipsService.getSlipByPatronId(data.data.patronId).then(data => {
          this.slips = data.data.reverse();
          
        })
      })
    } else {
      SlipsService.getAllSlipsList().then(data => {
        this.slips = data.data.reverse();
      });
    }
    },
  methods: {

    formatTime(time) {
      if (!time) return 'N/A';
    },

    total(slip) {
      
      if (slip.departureTime != null ) { 
        return slip.total;
      }

      const assumedDepartureTime = new Date();
      const parkingDurationInHours = (assumedDepartureTime - new Date(slip.arrivalTime)) / (1000 * 60 * 60);
      const parkingTotal = parkingDurationInHours * slip.hourlyPrice;

      return parkingTotal.toFixed(2);
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

.links {
  font-size: 25px;
}
</style>

