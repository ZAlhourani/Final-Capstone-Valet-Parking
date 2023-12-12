import axios from "axios";
import SlipsService from '../services/SlipsService.js'

export default {

    getAllSlipsList(){
        return axios.get('/slips');
    },

    getSlipByPatronId(patronId) {
        return axios.get(`/slips/patrons/${patronId}`);
    },

    getSlipBySlipNumber(slipNumber) {
        return axios.get(`/slips/${slipNumber}`);
    },
    
    getSlipByCarId(carId) {
        return axios.get(`/slips/cars/${carId}`);
    },

    createNewSlip(newSlip){
        return axios.post('/slips', newSlip);
    },

    updateSlip(slipNumber) {
        return axios.put(`/slips/${slipNumber}`);
    }


}