import axios from "axios";

export default {

    getAllPatrons() {
        return axios.get('/patrons');
    },

    getPatronByPatronId(patronId){
        return axios.get(`/patrons/${patronId}`);
    },

    createNewPatron(newPatron) {
        return axios.post('/patrons', newPatron);
    },

    updatePatron(patronId) {
        return axios.put(`/patrons/${patronId}`);

    },

}