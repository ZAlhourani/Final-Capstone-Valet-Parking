import axios from "axios";

export default{
    checkIn(carCheckIn){
        return axios.post(`/check-in`,carCheckIn)
    },
}