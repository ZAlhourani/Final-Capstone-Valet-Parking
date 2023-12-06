<template>
    <div class="total-cost">
      <h2>Total Cost Calculator</h2>
      <form @submit.prevent="calculateTotal">
        <label for="hours">Total Parking Hours </label>
        <input type="number" id="hours" v-model.number="hours" min="0">
        <button type="submit">Calculate</button>
      </form>
      <div v-if="total !== null">
        <p>The total cost for valet parking is: ${{ total.toFixed(2) }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        hours: 0,
        total: null,
      };
    },
    methods: {
      calculateTotal() {
        const ratePerHour = 5; 
        this.total = this.hours * ratePerHour;
      },
    },
  };
  </script>
  
  <style scoped>
  .total-cost {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .total-cost form {
    margin-bottom: 15px;
  }
  
  .total-cost button {
    margin-top: 10px;
    padding: 5px 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .total-cost button:hover {
    background-color: #45a049;
  }
  
  .total-cost input {
    padding: 5px;
    margin-top: 5px;
  }
  </style>
  