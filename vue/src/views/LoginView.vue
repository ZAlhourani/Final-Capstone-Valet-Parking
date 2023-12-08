<template>
  <div class="image-text-container">
    <div class="image-container">
      <img src="Parking Img.png" alt="Parking Valet Logo" :style="{width: '900px', height: '1000px'}">
    </div>
    <div class="text-container">
    </div>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
     </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            

            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
.image-text-container {
  display: flex;
}
.image-container {
  width: 700px;
  height: 800px;
  max-width: 100%;
  max-height: 100%;
}
.text-container {
  flex: 1;
  padding-left: 20px;
}
#login{
  font-size: 30px;
  font-style: italic;
  /* padding-bottom: 300px; */
  margin-top: 20px;
  color: rgb(130, 2, 251);
  text-shadow: 0 0 10px rgba(130, 2, 251, 1);
  margin-top: 100px; 
}
.form-input-group {
  margin-bottom: 1rem;
  text-align: center;
}
label {
  margin-right: 0.5rem;
}
html{
   background-color: rgb(21, 9, 38);  
  text-align: center;
}
body{
  /* color: azure; */
  display: flex;
  justify-content: center;
}
</style>
