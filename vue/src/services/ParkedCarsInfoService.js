import axios from "axios";

export default {
    getParkedCarsInfo(){
        return axios.get('/car-info');
    }
}