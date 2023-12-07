import axios from "axios";

export default {

    getCarDetails(carId) {
        return axios.get(`/car-details/${carId}`);
    }
}