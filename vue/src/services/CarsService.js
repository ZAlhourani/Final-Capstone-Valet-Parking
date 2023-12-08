import axios from "axios";

export default{

    getCarById(carId) {
        return axios.get(`/cars/${carId}`);
},

    createNewCar(newCar) {
        return axios.post('/cars', newCar);

    },

    updateCar(carId) {
        return axios.put(`/cars/${carId}`);
    }
}