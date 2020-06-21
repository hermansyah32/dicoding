const _weatherName = [{
        ID: "Cerah",
        EN: "Clear Skies",
        value: 0
    },
    {
        ID: "Cerah",
        EN: "Clear Skies",
        value: 100
    },
    {
        ID: "Cerah Berawan",
        EN: "Partly Cloudy",
        value: 1
    },
    {
        ID: "Cerah Berawan",
        EN: "Partly Cloudy",
        value: 101
    },
    {
        ID: "Cerah Berawan",
        EN: "Partly Cloudy",
        value: 2
    },
    {
        ID: "Cerah Berawan",
        EN: "Partly Cloudy",
        value: 102
    },
    {
        ID: "Berawan",
        EN: "Mostly Cloudy",
        value: 3
    },
    {
        ID: "Berawan",
        EN: "Mostly Cloudy",
        value: 103
    },
    {
        ID: "Berawan Tebal",
        EN: "Overcast",
        value: 4
    },
    {
        ID: "Berawan Tebal",
        EN: "Overcast",
        value: 104
    },
    {
        ID: "Udara Kabut",
        EN: "Haze",
        value: 5
    },
    {
        ID: "Asap",
        EN: "Smoke",
        value: 10
    },
    {
        ID: "Kabut",
        EN: "Fog",
        value: 45
    },
    {
        ID: "Hujan Ringan",
        EN: "Light Rain",
        value: 60
    },
    {
        ID: "Hujan Sedang",
        EN: "Rain",
        value: 61
    },
    {
        ID: "Hujan Sedang",
        EN: "Rain",
        value: 61
    },
    {
        ID: "Hujan Lebat",
        EN: "Heavy Rain",
        value: 63
    },
    {
        ID: "Hujan Lokal",
        EN: "Isolated Rain",
        value: 80
    },
    {
        ID: "Hujan Petir",
        EN: "Severe Thunderstorm",
        value: 95
    },
    {
        ID: "Hujan Petir",
        EN: "Severe Thunderstorm",
        value: 97
    },
    {
        ID: "Unknown",
        EN: "Unknown",
        value: 1000
    },


];

class Weather {
    static getText(id) {
        for (const weather of _weatherName) {
            if (weather.value == id) {
                return weather;
            }
        }

        return _weatherName[_weatherName.length - 1];
    }
}

// module.exports = Weather; //work with node.js
export default Weather;