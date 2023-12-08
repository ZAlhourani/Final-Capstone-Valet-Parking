<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-show="$route.name !== 'login'">Home</router-link>
      <span v-show="$route.name !== 'login'">&nbsp;|&nbsp;</span>

      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>
      <span v-if="$store.state.token">&nbsp;|&nbsp;</span>


      <!-- Customer-links -->
      <template v-if="isCustomer">
        <router-link v-bind:to="{ name: 'request-pickup' }">Request a Pickup</router-link>&nbsp;|&nbsp;

      </template>

      <!--Valet and Customer -->
      <router-link v-bind:to="{ name: 'lot-availability'}">Lot Availability</router-link>

      <span>&nbsp;|&nbsp;</span>

      <!-- Valet-links -->
      <template v-if="isValet">
        <router-link v-bind:to="{ name: 'arrive-time' }">Arrival Time</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'valet-request-pickup' }">Valet Request Pickup</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'check-in' }">Check In</router-link>
        <router-link v-bind:to="{ name: 'total-cost' }">Total Cost</router-link>&nbsp;|&nbsp;
        <span v-if="isCustomer">&nbsp;|&nbsp;</span>
      </template>

      <!-- Customer-specific link again, only if the user is not a valet -->
      <router-link v-if="isCustomer && !isValet" v-bind:to="{ name: 'slips' }">Parking Slips</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    showArrivalTime() {
      return this.$route.name !== 'login';
    },
    isCustomer(){
      return this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_USER');
    },
    // isValet(){
    //   return this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_VALET');
    // },
    showNavBar(){
      const noNavbarRoutes = ['login', 'register'];
      return !noNavbarRoutes.includes(this.$route.name);
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


