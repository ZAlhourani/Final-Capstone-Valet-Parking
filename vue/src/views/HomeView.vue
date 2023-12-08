<template>
  <div class="home">
       
    <h1>Servizio di Auto</h1>
  </div>
</template>

<script>
export default {
  computed: {
    showHomeLink() {
      return this.$route.name !== 'login';
    },
    showLotAvailabilityLink() {
      return true;
    },
    showSlipsLink() {
      return this.$store.state.token;
    },
    showLogoutLink() {
      return this.$store.state.token;
    },
  },
  
}; 

</script>

<style>
.home {
  height: 100vh;
  width: 100vw;
  background: #0a0a0a; 
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
#userNav {
  visibility: visible;
  color: azure; 
  font-size: 25px;
  border: 2px solid #BB29EB;
  background-color: rgb(3, 3, 3);
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

