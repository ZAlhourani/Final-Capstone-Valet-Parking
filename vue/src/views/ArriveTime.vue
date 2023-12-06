<template>
    <div class="arrive-time">
      <h1>Arrival Time</h1>
      <h2>center this and make the box smaller</h2>
      <input type="time" v-model="arrivalTime" @change="updateTime">
      <button @click="submitTime">Set Arrival Time</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        arrivalTime: ''
      };
    },
    methods: {
      updateTime() {
        console.log('Time selected:', this.arrivalTime);
      },
      submitTime() {
        console.log('Time submitted:', this.arrivalTime);
      }
    }
  };
  </script>
  
  <style scoped>
  .arrive-time {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .arrive-time h1 {
    margin-bottom: 20px;
  }
  
  .arrive-time input {
    margin-bottom: 10px;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .arrive-time button {
    padding: 10px 20px;
    border: none;
    background-color: #4CAF50;
    color: white;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .arrive-time button:hover {
    background-color: #45a049;
  }
  </style>
  