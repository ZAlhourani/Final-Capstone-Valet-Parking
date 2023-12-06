<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'arrive-time' }" v-if="$store.state.token">Arrival Time</router-link>&nbsp;|&nbsp;
      <!-- Conditionally render the "Total Cost" link based on the current route name -->
      <router-link v-bind:to="{ name: 'total-cost' }" v-show="$route.name !== 'login'">Total Cost</router-link>

    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    // Computed property to determine if the arrival time link should be shown
    showArrivalTime() {
      // Assuming you have a way to determine if the user is on the login page
      // Adjust this condition based on your routing or auth state
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


