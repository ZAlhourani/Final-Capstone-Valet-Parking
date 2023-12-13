
<template>
  <div>
    <h1>Parked Cars</h1>
    <div class="filters">
      <input v-model="filter" placeholder="Filter cars..." type="text">
    </div>
    <ul class="car-list">
      <li v-for="car in filteredCars" :key="car.id" class="flip-card" @click="flipCard(car)">
        <div class="flip-card-inner" :class="{ flipped: car.isFlipped }">
          <div class="flip-card-front">
              <p>Make: {{ car.make }}</p>
              <p>Model: {{ car.model }}</p>  
              <p>Color: {{ car.color }}</p>  
              <p>License Plate: {{ car.licensePlate }}</p> 
              <p>Spot Number: {{ car.spotNumber }}</p>
          </div>
          <div class="flip-card-back">
            <p>Owner: {{ car.name }}</p>
            <p>Phone Number: {{ car.phoneNumber }}</p>
            <p>Parking Spot: {{ car.spotNumber }}</p>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import ParkedCarsInfoService from '../services/ParkedCarsInfoService';
export default {
  data() {
    return {
      cars: [],
      filter: '',
      sortKey: 'make'
    };
  },
  computed: {
    filteredCars() {
      let filtered = this.cars;
      if (this.filter) {
        const filterLowerCase = this.filter.toLowerCase();
        filtered = filtered.filter((car) => {
           return car.make.toLowerCase().includes(filterLowerCase) || 
              car.model.toLowerCase().includes(filterLowerCase) ||
             (car.color && car.color.toLowerCase().includes(filterLowerCase)) ||
             (car.name && car.name.toLowerCase().includes(filterLowerCase)) ||
             (car.spotNumber && car.spotNumber == filterLowerCase) ||
             (car.phoneNumber && car.phoneNumber.includes(this.filter))
        }
        );
      }
      return filtered.sort((a, b) => (a[this.sortKey] > b[this.sortKey] ? 1 : -1));
    }
  },
  methods: {
    fetchCars() {
      ParkedCarsInfoService.getParkedCarsInfo()
        .then(response => {
          this.cars = response.data.map(car => ({ ...car, isFlipped: false }));
        })
        .catch(error => {
          console.error('Error fetching cars:', error);
        });
    },
    sortCars() {
      this.cars.sort((a, b) => {
        if (a[this.sortKey] < b[this.sortKey]) {
          return -1;
        }
        if (a[this.sortKey] > b[this.sortKey]) {
          return 1;
        }
        return 0;
      });
    },
    flipCard(car) {

      car.isFlipped = !car.isFlipped;
    }
  },
  created() {
    this.fetchCars();
  }
};
</script>

<style scoped>
h1 {
  text-align: center;
  /* color: #333; */
}

.filters {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.filters input,
.filters select {
  padding: 10px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.filters input:focus,
.filters select:focus {
  outline: none;
  border-color: #007BFF;
}

.car-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  gap: 20px;
  padding: 10px;
  list-style-type: none;
  margin: 0;
}

.car-list li {
  flex: 0 0 calc(50% - 40px);
  margin: 20px;
  perspective: 1000px;
  height: 200px;
}

.flip-card-inner {
  transition: transform 0.6s;
  transform-style: preserve-3d;
  position: relative;
  height: 100%;
}

.flip-card .flip-card-inner.flipped {
  transform: rotateY(180deg);
}

.flip-card-front,
.flip-card-back {
  
  backface-visibility: hidden;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  border-radius: 5px;
}

.flip-card-front{
  background-color: #F9F9F9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  color: green;
}

.flip-card-back {
  background-color: #E9ECEF;
  transform: rotateY(180deg);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15);
}

.flip-card-front p,
.flip-card-back p {
  font-size: 1.1em;
  color: black;
}</style>





