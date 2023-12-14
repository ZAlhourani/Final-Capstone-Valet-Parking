<template>
  <div id="rv">
  <div class="image-text-container">
  <div class="image-container">
  <img src="Parking Img.png" alt="Parking Valet Logo" :style="{width: '900px', height: '1000px'}">
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
      <div class="form-input-group">
        <label for="code">Code</label>
        <input type="password" id="code" v-model="user.code" required />
      </div>
      <button type="submit">Create Valet Account</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
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
        code: '',
        role: 'valet',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this valet user.',
    };
  },
  methods: {

    clearSecretCode(){
      this.user.code = '';
    },
    register() {
      if ((this.user.password != this.user.confirmPassword) && (this.user.code == '1234')) {
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
      this.registrationErrorMsg = 'There were problems registering this admin.';
    },
  },
};
</script>

<style>
.form-input-group {
  margin-bottom: 1rem;
  text-align: center;
}
#userNav, #AdminNav {
  visibility: visible;
  color: azure;
  font-size: 25px;
  border: 2px solid #BB29EB;
  background-color: rgba(3, 3, 3, 0.9); 
  backdrop-filter: blur(5px); 
  padding: 10px 20px; 
  margin: 10px; 
  border-radius: 5px; 
  box-shadow: 0 0 10px #BB29EB; 
  text-shadow: 0 0 5px #BB29EB; 
  transition: all 0.3s ease; 
}

#userNav:hover, #AdminNav:hover {
  box-shadow: 0 0 15px #BB29EB, 0 0 25px #BB29EB; 
  transform: translateY(-2px);
}

label {
  margin-right: 0.5rem;
}
#register{
  font-size: 30px;
  font-style: italic;
  margin-top: 20px;
  color: rgb(130, 2, 251);
  text-shadow: 0 0 10px rgba(130, 2, 251, 1);
  margin-bottom: 20px;
  margin-top: 100px;
}
</style>
