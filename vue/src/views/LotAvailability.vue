<template>
  <div class="parking-lot-container">
    <h2>Parking Lot Availability</h2>
    <div v-if="isLotFull" class="lot-full-warning">
      <p><strong>Warning:</strong> Lot is Full!</p>
    </div>
    <div class="parking-lot">
      <div v-for="row in formattedParkingSpots" :key="row[0].spotNumber" class="parking-row">
        <div v-for="spot in row" :key="spot.spotNumber" class="parking-spot" :class="{ 'available': spot.isAvailable, 'occupied': !spot.isAvailable }">
          <p>Spot {{ spot.spotNumber }}</p>
          <p v-if="!spot.isAvailable">Occupied</p>
          <p v-else>Available</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ParkingSpotService from '../services/ParkingSpotsService';

export default {
  data() {
    return {
      parkingSpots: []
    };
  },
  computed: {
    formattedParkingSpots() {
    const spotsPerRow = 5;
    let rows = [];
    let sortedSpots = [...this.parkingSpots].sort((a, b) => a.spotNumber - b.spotNumber);
    for (let i = 0; i < sortedSpots.length; i += spotsPerRow) {
      let row = sortedSpots.slice(i, i + spotsPerRow);
      rows.push(row);
    }
    return rows;
  },
    isLotFull() {
      if (this.parkingSpots.length === 0) {
        return false; 
      }
      this.parkingSpots.forEach((spot, index) => {
        console.log(`Spot ${index + 1}: Available - ${spot.isAvailable}`);
      });

      const isFull = !this.parkingSpots.some(spot => spot.isAvailable);
      console.log('Computed lot full status:', isFull);
      return isFull;
    }
  },
  methods: {
    fetchParkingSpots() {
      ParkingSpotService.getAllParkingSpots()
        .then(response => {
          console.log('Parking spots data:', response.data);

          this.parkingSpots = response.data.map(spot => ({
            spotNumber: spot.spotNumber, // Make sure this matches the key in the response
            isAvailable: spot.available // Make sure this matches the key in the response and is a boolean
          }));

          this.parkingSpots.forEach((spot, index) => {
            console.log(`Spot ${index + 1}: Available - ${spot.isAvailable}`);
          });

          console.log('Computed lot full status:', this.isLotFull);
        })
        .catch(error => {
          console.error('Error fetching parking spots:', error);
        });
    }
  },
  created() {
    this.fetchParkingSpots();
  }
};
</script>

<style scoped>
.parking-lot-container {
  max-width: 800px;
  margin: auto;
  text-align: center;
}

.lot-full-warning {
  background-color: #ffcccc;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
}

.parking-lot {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.parking-row {
  display: flex;
  justify-content: center;
  flex-basis: 100%;
  margin-bottom: 10px;
}

.parking-spot {
  border: 1px solid #ddd;
  padding: 20px;
  margin: 5px;
  text-align: center;
  width: 150px;
  height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border-radius: 5px;
}

.parking-spot.available {
  background-color: #ccffcc;
}

.parking-spot.occupied {
  background-color: #ffcccc;
}

.parking-spot p {
  margin: 5px 0;
  font-weight: bold;
  color: #333;
}
</style>
