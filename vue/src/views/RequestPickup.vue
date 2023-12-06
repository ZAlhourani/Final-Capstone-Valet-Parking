 <template>
    <div class="request-pickup">
      <h1>Request a Pickup</h1>
      <form @submit.prevent="submitPickupRequest">
        <!-- <div class="form-group">
          <label for="location">Pickup Location:</label>
          <input type="text" id="location" v-model="pickupData.location" required>
        </div> -->
  
        <div class="form-group">
          <label for="time">Pickup Time:</label>
          <input type="time" id="time" v-model="pickupData.time" required>
        </div>
  
        <div class="form-group">
          <label for="notes">Additional Notes:</label>
          <textarea id="notes" v-model="pickupData.notes"></textarea>
        </div>
  
        <button type="submit">Request Pickup</button>
      </form>
    </div>
  </template>
  
  <script>
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
      submitPickupRequest() {
        // Replace with actual API call or Vuex action
        console.log('Pickup request submitted:', this.pickupData);
        // Clear form after submission for demonstration purposes
        this.pickupData = { location: '', time: '', notes: '' };
        // Inform the user of a successful request (you could replace this with proper user feedback)
        alert('Your pickup request has been submitted.');
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
   