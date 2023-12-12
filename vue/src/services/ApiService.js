import axios from 'axios';




export default {

    getWeather() {
        return axios.get('https://api.open-meteo.com/v1/forecast?latitude=40.4406&longitude=-79.9959&current=temperature_2m&temperature_unit=fahrenheit&wind_speed_unit=mph&timezone=America%2FNew_York&forecast_days=1');
    }
}