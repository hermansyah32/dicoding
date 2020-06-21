const fetch = require('node-fetch');
const API = require("../scripts/communication/rest");
const Weather = require("../scripts/data/weather");

const callIndonesia = async() => {
    try{
        const result = await API.getIndonesiaWeather();
        console.log(result);
    }catch(message){
        console.log(message);
    }
}

const callWeather = async(keyword) => {
    try{
        const result = await API.getWeather(keyword);
        console.log(result);
    }catch(message){
        console.log(message);
    }
}

const callWeatherByProvince = async(provinceID) => {
    try{
        const result = await API.getWeatherByProvince(provinceID);
        console.log(result);
    }catch(message){
        console.log(message);
    }
}

const callProvince = async() => {
    try{
        const result = await API.getProvince();
        console.log(result);
    }catch(message){
        console.log(message);
    }
}

// callIndonesia();
// callWeather("aceh");
// callWeatherByProvince(1);
// callProvince();

console.log(Weather.getText(80));