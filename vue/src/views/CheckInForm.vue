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
      <div class="form-group">
        <label for="spot-number">Spot Number:</label>
        <input id="spot-number" v-model="spotNumber" required>
      </div>
      <button type="submit">Check-In</button>
    </form>
  </div>
</template>
<script>
import CheckInDtoService from '../services/CheckInDtoService';
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
      arrivalTime: '',
      spotNumber: '',
    };
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        alert('Please fill all the fields correctly.');
        return;
      }
      const carCheckInData = {
        ...this.car,
        ...this.patron,
        arrivalTime: this.arrivalTime,
        spotNumber: this.spotNumber
      };
      CheckInDtoService.checkIn(carCheckInData)
        .then(response => {
          console.log('Check-in successful', response);
          alert('Check-in successful');
          this.clearForm();
        })
        .catch(error => {
          console.error('An error occurred during form submission', error);
          alert('An error occurred during form submission');
        });
    },
    clearForm() {
      this.car = {
        make: '',
        model: '',
        color: '',
        licensePlate: '',
        vinNumber: ''
      };
      this.patron = {
        name: '',
        phoneNumber: ''
      };
      this.arrivalTime = '';
      this.spotNumber = '';
    },
    validateForm() {
      return this.car.make && this.car.model && this.car.color &&
             this.car.licensePlate && this.car.vinNumber &&
             this.patron.name && this.patron.phoneNumber &&
             this.arrivalTime && this.spotNumber;
    }
  }
};
</script>
  <style>
label {
  font-size: 1.2rem;
  color: #fff;
  margin-bottom: 0.5rem;
  display: block;
}
 h2 {
  font-size: 2.5rem;
  text-align: center;
  color: #fff;
  margin-bottom: 1.5rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}
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












