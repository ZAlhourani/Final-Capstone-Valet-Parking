<template>
    <div class="check-in-form">
      <h2>Car Check-In</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="make">Car Make:</label>
          <input id="make" v-model="car.make" required>
        </div>
  
        <div class="form-group">
          <label for="model">Car Model:</label>
          <input id="model" v-model="car.model" required>
        </div>
  
        <div class="form-group">
          <label for="color">Car Color:</label>
          <input id="color" v-model="car.color" required>
        </div>
  
        <div class="form-group">
          <label for="license">License Plate:</label>
          <input id="license" v-model="car.licensePlate" required>
        </div>
  
        <div class="form-group">
          <label for="vin">VIN Number:</label>
          <input id="vin" v-model="car.vinNumber" required>
        </div>
  
        <div class="form-group">
          <label for="patron-name">Patron Name:</label>
          <input id="patron-name" v-model="patron.name" required>
        </div>
  
        <div class="form-group">
          <label for="patron-phone">Patron Phone Number:</label>
          <input id="patron-phone" v-model="patron.phoneNumber" required type="tel">
        </div>
  
        <div class="form-group">
          <label for="arrival-time">Arrival Time:</label>
          <input id="arrival-time" v-model="arrivalTime" type="datetime-local" required>
        </div>
  
        <button type="submit">Check-In</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        car: {
          make: '',
          model: '',
          color: '',
          licensePlate: '',
          vinNumber: ''
        },
        patron: {
          name: '',
          phoneNumber: ''
        },
        arrivalTime: ''
      };
    },
    methods: {
      submitForm() {
        console.log('Form data:', this.car, this.patron, this.arrivalTime);
      }
    }
  };
  </script>
  
  <style>
 
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  </style>
  