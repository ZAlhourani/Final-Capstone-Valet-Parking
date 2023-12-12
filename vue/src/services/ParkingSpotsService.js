import axios from "axios";

export default {

    getAllParkingSpots(){
        return axios.get('/parking-spots');
    },

    getParkingSpotBySpotNumber(spotNumber){
        return axios.get(`/parking-spots/${spotNumber}`);
    },

    getParkingSpotsWithCars() {
        return axios.get('/parking-spots/isAvailable/parked-cars');
    },

    getParkingSpotByAvailability(isAvailable) {
        return axios.get('/parking-spots/availability/' + isAvailable);

    },

    updateParkingSpot(spotNumber) {
        return axios.put(`/parking-spots/${spotNumber}`);
    }
}

// methods: {
//     fetchCars() {
//       ParkedCarsInfoService.getParkedCarsInfo()
//         .then(response => {
//           this.cars = response.data.map(car => ({ ...car, isFlipped: false }));
//         })
//         .catch(error => {
//           console.error('Error fetching cars:', error);
//         });
//     },