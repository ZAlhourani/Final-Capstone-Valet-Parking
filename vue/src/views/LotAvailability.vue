<template>
  <div class="parking-lot-container">
    <h2>Parking Lot Availability</h2>

    <div v-if="isLotFull" class="lot-full-warning">
      <p><strong>Warning:</strong> Lot is Full!</p>
    </div>

    <div class="parking-lot">
      <div v-for="row in formattedParkingSpots" :key="row[0].id" class="parking-row">
        <div v-for="spot in row" :key="spot.id" class="parking-spot" :class="spotClass(spot)" @mouseover="showDetails(spot)" @mouseleave="hideDetails">
          <i :class="iconClass(spot)"></i>
          <div v-show="selectedSpot.id === spot.id" class="details">
            <p>Location: {{ spot.location }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
export default {
  data() {
    return {
      parkingSpots: [
      { id: 1, isAvailable: true,  location: '1' },
      { id: 2, isAvailable: true,  location: '2' },
      { id: 3, isAvailable: true,  location: '3' },
      { id: 4, isAvailable: true,  location: '4' },
      { id: 5, isAvailable: true,  location: '5' },
      { id: 6, isAvailable: true,  location: '6' },
      { id: 7, isAvailable: true,  location: '7' },
      { id: 8, isAvailable: true,  location: '8' },
      { id: 9, isAvailable: true,  location: '9' },
      { id: 10, isAvailable: true, location: '10'},
      ],
      selectedSpot: {}
    };
  },
  computed: {
    formattedParkingSpots() {
      const spotsPerRow = 5;
    let rows = [];
    for (let i = 0; i < this.parkingSpots.length; i += spotsPerRow) {
      let row = this.parkingSpots.slice(i, i + spotsPerRow);
      rows.push(row);
    }
    return rows;
  },
  isLotFull(){
    return this.parkingSpots.every(spot => !spot.isAvailable);
  }
},

  methods: {
    showDetails(spot) {
      if (spot.isAvailable) {
        this.selectedSpot = spot;
      }
    },
    hideDetails() {
      this.selectedSpot = {};
    },
    spotClass(spot) {
      return {
        'available': spot.isAvailable,
      };
    },
    iconClass(spot) {
    }
  }
};
</script>

<style>
.parking-lot {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}

.parking-row {
  display: flex;
  justify-content: center;
  margin-bottom: 15px; 
}



.parking-spot {
  border: 1px solid #ddd;
  padding: 20px; 
  margin: 10px;
  text-align: center;
  cursor: pointer;
  flex-grow: 1;
  width: 200px; 
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.available {
  background-color: #dff0d8; 
}

.details {
  display: none;
  background-color: #f5f5f5;
  border: 1px solid #ccc;
  padding: 5px;
  position: absolute;
  z-index: 10;
}

.parking-spot:hover .details {
  display: block;
}
</style>
