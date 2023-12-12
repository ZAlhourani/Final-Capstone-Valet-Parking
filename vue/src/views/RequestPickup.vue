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
      async submitPickupRequest() {
        console.log('Pickup request submitted:', this.pickupData);

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

          data.data.userId.authorities = toString(data.data.userId.authorities);

          console.log(pickupTime);
            const slip = {
            patronId: data.data,
            arrivalTime: isoString,
            departure_time : '',
            hourly_price: 5,
            total: 0
          };
        
          SlipsService.createNewSlip(slip);
        })
        .finally(() => {
            this.pickupData = { location: '', time: '', notes: '' };
            alert('Your pickup request has been submitted.');
          }
        );
      
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
   