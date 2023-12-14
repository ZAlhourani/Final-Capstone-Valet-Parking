<template>
  <div class="check-in-form">
    <h2>Car Check-In</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="make">Car Make:</label>
        <select v-model="car.make" id="car-dropdown" class="dropdown-boxes">
          <option v-for="carMake in carMakeList" :key="carMake.indexOf" :value="carMake">
            {{ carMake }}
          </option>
        </select>
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
        <select @change="setPatronPhoneNumber" v-model="patron.name" id="name-dropdown" class="dropdown-boxes">
          <option v-for="onePatron in patronDropdown" :key="onePatron.patronId" :value="onePatron.name">
            {{ onePatron.name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="patron-phone">Patron Phone Number:</label>
        <select @change="setPatronName" v-model="patron.phoneNumber" id="phone-dropdown" class="dropdown-boxes">
          <option v-for="onePatron in patronDropdown" :key="onePatron.patronId" :value="onePatron.phoneNumber">
            {{ onePatron.phoneNumber }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="arrival-time">Arrival Time:</label>
        <input id="arrival-time" v-model="arrivalTime" type="datetime-local" required>
      </div>
      <div class="form-group">
        <label for="spot-number">Spot Number:</label>
        <select v-model="spotNumber" id="spots-dropdown" class="dropdown-boxes">
          <option v-for="oneSpot in parkingSpotsDropdown" :key="oneSpot.spotNumber" :value="oneSpot.spotNumber">
            {{ oneSpot.spotNumber }}
          </option>
        </select>
      </div>
      <button type="submit">Check-In</button>
    </form>
  </div>
</template>
<script>
import CheckInDtoService from '../services/CheckInDtoService';
import PatronsService from '../services/PatronsService';
import ParkingSpotsService from '../services/ParkingSpotsService';

export default {
  data() {
    return {
      carMakeList: [
        'Alfa Romeo',
        'Aston Martin',
        'Audi',
        'BMW',
        'Bently',
        'Bugatti',
        'Buick',
        'Cadillac',
        'Chevrolet',
        'Chrysler',
        'Dodge',
        'Ferrari',
        'Ford',
        'GM',
        'GEM',
        'Genesis',
        'GMC',
        'Honda',
        'Hummer',
        'Hyundai',
        'Infiniti',
        'Isuzu',
        'Jaguar',
        'Jeep',
        'Kia',
        'Lamborghini',
        'Land Rover',
        'Lexus',
        'Lincoln',
        'Lotus',
        'Lucid',
        'Maserati',
        'Mazda',
        'McLaren',
        'Mercedes-Benz',
        'Mercury',
        'Mitsubishi',
        'Nissan',
        'Oldsmobile',
        'Peugeot',
        'Polestar',
        'Pontiac',
        'Porsche',
        'Regal',
        'Rivian',
        'Rolls-Royce',
        'Saab',
        'Saturn',
        'Subaru',
        'Suzuki',
        'Tesla',
        'Toyota',
        'Volkswagen',
        'Volvo'
      ],
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
      patronDropdown: [],
      parkingSpotsDropdown: [],
      selectedPatron: null,
    };
  },
  methods: {
    getAvailableSpots() {
      ParkingSpotsService.getParkingSpotByAvailability(true)
        .then(response => {
          this.parkingSpotsDropdown = response.data;
        })
        .catch(error => {
          console.error('Error getting available parking spots;', error);
        });
    },
    setPatronName() {
      let foundPatron = this.patronDropdown.find(item => item.phoneNumber.trim() == this.patron.phoneNumber.trim());
      this.patron.name = foundPatron.name;
    },

    setPatronPhoneNumber() {
      let foundPatron = this.patronDropdown.find(item => item.name.trim() == this.patron.name.trim());
      this.patron.phoneNumber = foundPatron.phoneNumber;
    },

    
    getPatronDropdown() {

      PatronsService.getAllPatrons()
        .then(response => {
          this.patronDropdown = response.data;
        })
        .catch(error => {
          console.error('Error getting customer dropdown options:', error);
        });
    },


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
          location.reload();
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
  },
  mounted() {
    this.getPatronDropdown();
    this.getAvailableSpots();
  },
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

.form-group label {
  margin-bottom: 15px;
  color: white;
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

option {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

select.dropdown-boxes {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
