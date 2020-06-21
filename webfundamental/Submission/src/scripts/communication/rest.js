// const fetch = require('node-fetch');
const baseUrl = "https://getnesia.com/api/weather"
// const baseUrl = "http://127.0.0.1/getnesia/api/weather"

class API {
    static getProvince() {
        return fetch(`${baseUrl}/province`)
            .then(response => response.json())
            .then(responseJSON => {
                if (responseJSON.success) {
                    return Promise.resolve(responseJSON);
                } else {
                    return Promise.reject("Something wrong happened")
                }
            })
    }

    static getIndonesiaWeather() {
        return fetch(`${baseUrl}/indonesia`)
            .then(response => response.json())
            .then(responseJSON => {
                if (responseJSON.success) {
                    return Promise.resolve(responseJSON);
                } else {
                    return Promise.reject("Something wrong happened")
                }
            })
    }

    static getWeatherByProvince(provinceID) {
        return fetch(`${baseUrl}/province/id/${provinceID}`)
            .then(response => response.json())
            .then(responseJSON => {
                if (responseJSON.success) {
                    return Promise.resolve(responseJSON);
                } else {
                    return Promise.reject("Something wrong happened.")
                }
            })
    }

    static getWeather(keyword) {
        return fetch(`${baseUrl}/search?keyword=${keyword}`)
            .then(response => response.json())
            .then(responseJSON => {
                if (responseJSON.success) {
                    return Promise.resolve(responseJSON);
                } else {
                    return Promise.reject(`The city with keyword (${keyword}) is not found.`)
                }
            })
    }
}

// module.exports = API; // works with node js
export default API;