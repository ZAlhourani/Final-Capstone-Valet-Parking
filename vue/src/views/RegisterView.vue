<template>
  <div id="rv">
  <div class="image-text-container">
  <div class="image-container">
    <img src="Parking Img.png" alt="Parking Valet Logo" :style="{width: '900px', height: '1000px'}">
      </div>
    <div class="text-container">
    </div>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button class="CreateAcctBttn" type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      <div>
      <p><router-link v-bind:to="{ name: 'registervaletview' }">New Valet? Create an account here.</router-link></p>
      </div>
     </form>
    </div>
  </div>
</div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.image-text-container {
  display: flex;
}
.form-input-group {
  margin-bottom: 1rem;
  text-align: center;
}
label {
  margin-right: 0.5rem;
}
#register{
  font-size: 30px;
  font-style: italic;
  /* padding-bottom: 300px; */
  margin-top: 20px;
  color: rgb(130, 2, 251);
  text-shadow: 0 0 10px rgba(130, 2, 251, 1);
  margin-bottom: 20px;
  margin-top: 100px;
}
 .image-container {
  width: 700px;
  height: 800px;
  max-width: 100%;
  max-height: 100%;
} 


</style>
