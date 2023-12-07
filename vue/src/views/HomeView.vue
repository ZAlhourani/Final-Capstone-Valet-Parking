<template>
  <div class="home">
    <nav id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-show="$route.name !== 'login'">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'lot-availability'}">lot Availability</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'slips' }" v-if="$store.state.token">Parking Slip</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>&nbsp;|&nbsp;

    </nav>
    <h1>Servizio di Auto</h1>
    
    <p>You must be authenticated to see this</p>
    
  
  </div>
</template>

<script>
export default {
};
</script>

<style scoped>
.home {
  height: 100vh;
  background: #0a0a0a; 
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
#nav {
  visibility: visible;
}

h1 {
  color: #bb29eb; 
  text-shadow: 0 0 10px #bb29eb; 
  font-size: 3rem;
  margin-bottom: 0.5em;
}

p {
  color: #c1c1c1; 
  font-size: 1.5rem;
  margin-top: 0;
}
</style>

