 <template>
    <div class="request-pickup">
      <h1>Request a Pickup</h1>
      <form @submit.prevent="submitPickupRequest">
        <div class="form-group">
        </div>
  
        <div class="form-group">
          <label for="time">Pickup Time:</label>
          <input type="time" id="time" v-model="pickupData.time" required>
        </div>
  
        <div class="form-group">
          <label for="notes">Additional Notes:</label>
          <textarea id="notes" v-model="pickupData.notes"></textarea>
        </div>
  
        <button type="submit">Request Pickup</button>
        <router-link v-bind:to="{ name: 'slips' }" v-if="$store.state.token">View Parking Slip</router-link>&nbsp;

      </form>
    </div>
  </template>
  
  <script>
  import SlipsService from '../services/SlipsService';
  import PatronsService from '../services/PatronsService';
  import CheckInDtoService from '../services/CheckInDtoService';

  export default {
    data() {
      return {
        pickupData: {
          location: '',
          time: '',
          notes: ''
        }
      };
    },
    methods: {
      total(slip) {
      const parkingDurationInHours = (new Date(slip.departureTime) - new Date(slip.arrivalTime)) / (1000 * 60 * 60);
      const parkingTotal = parkingDurationInHours * slip.hourlyPrice;

      return parkingTotal;
    },
      async submitPickupRequest() {
        var pickupTime = this.pickupData.time
        var today = new Date();
        var [hours, minutes] = pickupTime.split(':');

        // Set the hours and minutes of the Date object
        today.setHours(hours, minutes, 0, 0);
        if (today < new Date()) {
          // If yes, set it to tomorrow
          today.setDate(today.getDate() + 1);
        }

        // Convert to ISO 8601 format
        var isoString = today.toISOString();

        // Remove the milliseconds and timezone information
        isoString = isoString.slice(0, -5);

        
      PatronsService.getPatronIdByUserId(this.$store.state.user.id).then(data => {
        SlipsService.getSlipByPatronId(data.data.patronId).then(data => {
          let slips = data.data;
          let slipToUpdate = {};
          slips.forEach(slip => {
            if(slip.departureTime === null){
              slipToUpdate = slip;
            }
          });

          slipToUpdate.departureTime = today;
          slipToUpdate.total = this.total(slipToUpdate);

          // this is hacky and bad but not as bad as userId actually being a whole user object
          slipToUpdate.patronId.userId.authorities = toString(slipToUpdate.patronId.userId.authorities);
          slipToUpdate.carId.patronId.userId.authorities = toString(slipToUpdate.carId.patronId.userId.authorities);
          
          // SlipsService.updateSlip(slipToUpdate.slipNumber, slipToUpdate).finally(()=> {
          //   this.pickupData = { location: '', time: '', notes: '' };
          //   alert('Your pickup request has been submitted.');
          // });
          CheckInDtoService.checkOut(slipToUpdate).finally(()=> {
            this.pickupData = { location: '', time: '', notes: '' };
            alert('Your pickup request has been submitted.');
          });
          
        })
      })
      }
    }
  };
  </script>
  
  <style scoped>
  .request-pickup {
    
    max-width: 500px;
    margin: 0 auto;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
  }
  
  .request-pickup .form-group {
    margin-bottom: 15px;
  }
  
  .request-pickup .form-group label {
    display: block;
    margin-bottom: 5px;
  }
  
  .request-pickup .form-group input,
  .request-pickup .form-group textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .request-pickup button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .request-pickup button:hover {
    background-color: #45a049;
  }
  </style>
   