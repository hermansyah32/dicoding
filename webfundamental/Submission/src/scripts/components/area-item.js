import Weather from "../data/weather.js";
import moment from "moment";

class AreaItem extends HTMLElement {
    constructor() {
        super();
        this.shadowDOM = this.attachShadow({
            mode: "open"
        });
    }

    set area(area) {
        this._area = area;
        this.render();
    }

    styling() {
        this.shadowDOM.innerHTML = `
        <style>
            h3 {
                text-align: center;
            }
            
            table {
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                border-radius: 5px;
                padding: 20px;
                width: 100%;
                table-layout: fixed;
            }

            .landmark-cell{
                width: min-content;
            }
            
            table tr {
                text-align: center;
                padding: 10px;
            }
            
            table tr.header {
                font-weight: bold;
            }
            
            .forecast-header {
                display: table-cell;
            }
            
            .inactive-col {
                display: table-cell;
            }
            
            .active-col {
                display: table-cell;
            }
            
            .current-col {
                background-color: #ccc;
            }
            
            .area-image { 
                max-height: 300px;
                object-fit: cover;
                object-position: center;
            }
            
            .weather-icon {
                max-height: 50px;
                object-fit: cover;
                object-position: center;
            }

            @media screen and (max-width: 1100px) {

                #content,
                aside {
                    position: inherit;
                    text-align: center;
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                }
                .profile {
                    width: 250px;
                }
            
                .sub-region {
                    display: grid;  
                    grid-gap: 20px;  
                    grid-template-columns: 1fr 1fr; 
                }
                
            }
            
            @media screen and (max-width: 950px) {
                .area-image {
                    display: none;
                }
            }
            
            @media screen and (max-width: 678px) {
            
                .inactive-col {
                    display: none;
                }
                
                .active-col {
                    display: table-cell;
                }
            }
            
            @media screen and (max-width: 530px) {

                .forecast-header {
                    display: none;
                }
            
                .active-col {
                    display: none;
                }
            }
        </style>`;
    }

    activeCol() {
        // set active column in table
        const counts = [0, 6, 12, 18];
        const hour = moment().format("HH");
        
        const closestHour = counts.reduce((prev, curr) => {
            return ((curr - hour) > 0 ? prev : curr);
        });

        const cells = this.shadowDOM.querySelectorAll(`.time-${closestHour}`);
        cells.forEach((cell) => {
            cell.classList.add("current-col");
        })
    }

    responsive() {
        // Responsive in javascript width 678
        const mediaQuery = window.matchMedia("(max-width: 678px)");
        if (mediaQuery.matches) {
            // change row span
            const elements = this.shadowDOM.querySelectorAll(".forecast-header");
            elements.forEach((element) => (
                element.colSpan = 0
            ));
        } else {
            const elements = this.shadowDOM.querySelectorAll(".forecast-header");
            elements.forEach((element) => (
                element.colSpan = 4
            ));
        }
        mediaQuery.addListener((media) => {
            if (media.matches) {
                const elements = this.shadowDOM.querySelectorAll(".forecast-header");
                elements.forEach((element) => (
                    element.colSpan = 0
                ));
            } else {
                const elements = this.shadowDOM.querySelectorAll(".forecast-header");
                elements.forEach((element) => (
                    element.colSpan = 4
                ));
            }
        })
    }

    render() {
        moment.locale("id");
        this.styling();
        this.shadowDOM.innerHTML += `
        <h3>${this._area.name}</h3>
        <div class="sub-area">
            <article class="area-card">
                <table style="border-collapse: collapse;" >
                    <tr class="header">
                        <td class="landmark-cell" rowspan="5">
                            <img class="area-image" src="assets/img/landmark/${this._area.domain}.svg" alt="${this._area.domain}-icon">
                        </td>
                        <td colspan="4">${moment().format('dddd')}<br>${moment().format('L')}</td>
                        <td class="forecast-header" colspan="4">${moment().add(1, 'days').format('dddd')}<br>${moment().add(1, 'days').format('L')}</td>
                        <td class="forecast-header" colspan="4">${moment().add(2, 'days').format('dddd')}<br>${moment().add(2, 'days').format('L')}</td>
                    </tr>
                    <tr class="header">
                        <td class="time-0"><p>00:00</p></td>
                        <td class="time-6"><p>06:00</p></td>
                        <td class="time-12"><p>12:00</p></td>
                        <td class="time-18"><p>18:00</p></td>
                        <td class="active-col"><p>00:00</p></td>
                        <td class="inactive-col"><p>06:00</p></td>
                        <td class="inactive-col"><p>12:00</p></td>
                        <td class="inactive-col"><p>18:00</p></td>
                        <td class="active-col"><p>00:00</p></td>
                        <td class="inactive-col"><p>06:00</p></td>
                        <td class="inactive-col"><p>12:00</p></td>
                        <td class="inactive-col"><p>18:00</p></td>
                    </tr>
                    <tr>
                        <td class="time-0"><p>${Weather.getText(this._area.weather._0).ID}</p></td>
                        <td class="time-6"><p>${Weather.getText(this._area.weather._6).ID}</p></td>
                        <td class="time-12"><p>${Weather.getText(this._area.weather._12).ID}</p></td>
                        <td class="time-18"><p>${Weather.getText(this._area.weather._18).ID}</p></td>
                        <td class="active-col"><p>${Weather.getText(this._area.weather._24).ID}</p></td>
                        <td class="inactive-col"><p>${Weather.getText(this._area.weather._30).ID}</p></td>
                        <td class="inactive-col"><p>${Weather.getText(this._area.weather._36).ID}</p></td>
                        <td class="inactive-col"><p>${Weather.getText(this._area.weather._42).ID}</p></td>
                        <td class="active-col"><p>${Weather.getText(this._area.weather._48).ID}</p></td>
                        <td class="inactive-col"><p>${Weather.getText(this._area.weather._54).ID}</p></td>
                        <td class="inactive-col"><p>${Weather.getText(this._area.weather._60).ID}</p></td>
                        <td class="inactive-col"><p>${Weather.getText(this._area.weather._66).ID}</p></td>
                    </tr>
                    <tr>
                        <td class="time-0"><p>${this._area.temperature._0}&deg;</p></td>
                        <td class="time-6"><p>${this._area.temperature._6}&deg;</p></td>
                        <td class="time-12"><p>${this._area.temperature._12}&deg;</p></td>
                        <td class="time-18"><p>${this._area.temperature._18}&deg;</p></td>
                        <td class="active-col"><p>${this._area.temperature._24}&deg;</p></td>
                        <td class="inactive-col"><p>${this._area.temperature._30}&deg;</p></td>
                        <td class="inactive-col"><p>${this._area.temperature._36}&deg;</p></td>
                        <td class="inactive-col"><p>${this._area.temperature._42}&deg;</p></td>
                        <td class="active-col"><p>${this._area.temperature._48}&deg;</p></td>
                        <td class="inactive-col"><p>${this._area.temperature._54}&deg;</p></td>
                        <td class="inactive-col"><p>${this._area.temperature._60}&deg;</p></td>
                        <td class="inactive-col"><p>${this._area.temperature._66}&deg;</p></td>
                    </tr>
                    <tr>
                        <td class="time-0"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._0).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="time-6"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._6).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="time-12"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._12).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="time-18"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._18).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="active-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._24).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="inactive-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._30).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="inactive-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._36).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="inactive-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._42).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="active-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._48).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="inactive-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._54).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="inactive-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._60).value}.svg" alt="weather-illustration"/></p></td>
                        <td class="inactive-col"><p><img class="weather-icon" src="assets/img/weather/${Weather.getText(this._area.weather._66).value}.svg" alt="weather-illustration"/></p></td>
                    </tr>
                </table>
            </article>
        </div>`;

        this.responsive();
        this.activeCol();
    }
}

customElements.define("area-item", AreaItem);