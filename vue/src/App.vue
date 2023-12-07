<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-show="$route.name !== 'login'">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'arrive-time' }" v-if="$store.state.token">Arrival Time</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'total-cost' }" v-show="$route.name !== 'login'">Total Cost</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'request-pickup' }" v-show="$route.name !== 'login'">Request a Pickup</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'lot-availability'}">lot Availability</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'slips' }" v-if="$store.state.token">Parking Slip</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'check-in' }" v-if="$store.state.token">Check In</router-link>

    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    showArrivalTime() {
      return this.$route.name !== 'login';
    }
  }
};
</script>
<style>

#nav {
  background-color: #333; 
  padding: 10px 20px; 
  text-align: center; 
}

#nav a {
  color: #fff; 
  text-decoration: none; 
  padding: 5px 10px; 
  transition: background-color 0.3s, color 0.3s; 
}

#nav a:hover {
  background-color: #bb29eb; 
   color: lightpink;  
  border-radius: 4px; 
}

</style>


