import axios from "axios";

export default {

    getAllSlipsList(){

        return axios.get('/slips');
    },

    getSlipBySlipNumber(slipNumber) {
        return axios.get(`/slips/${slipNumber}`);
    },

    getSlipByCarId(carId) {
        return axios.get(`/slips/${carId}`);
    },

    createNewSlip(newSlip){
        return axios.post('/slips', newSlip);
    },

    updateSlip(slipNumber) {
        return axios.put(`/slips/${slipNumber}`);
    }


}