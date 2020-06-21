import Menu from "./menu.js";
import "../components/area-list.js";
import {
    renderAreaList,
    renderErrorElement
} from './renderer.js';
import renderPage from "./responsive.js";
import API from "../communication/rest.js";
import moment from "moment";


const main = function () {

    const menuElements = document.querySelector("ul");
    const buttonSearchElement = document.querySelector("#button-search");
    const inputSearchElement = document.querySelector("#input-search");
    const buttonSearchModalElement = document.querySelector("#button-search-modal");
    const inputSearchModalElement = document.querySelector("#input-search-modal");
    const areaListElements = document.querySelector("area-list");

    const getNavMenu = async () => {
        const menu = new Menu(menuElements);
        try {
            const response = await API.getProvince();
            menu.menus = response.data;
            menu.render();
        } catch (error) {
            menu.renderError(error);
        }
    };

    const getIndonesiaArea = async () => {
        try {
            const response = await API.getIndonesiaWeather();
            renderAreaList(areaListElements, response.data);
            for (let index = 0; index < menuElements.children.length; index++) {
                const element = menuElements.children[index];
                element.addEventListener("click", () => {
                    getWeatherByProvince(element.id);
                });
            };
        } catch (error) {
            renderErrorElement(areaListElements, error);
        }
    };

    const getWeather = async (keyword) => {
        try {
            const response = await API.getWeather(keyword);
            console.log(response)
            renderAreaList(areaListElements, response.data);
        } catch (error) {
            renderErrorElement(areaListElements, error);
        }
    };

    const getWeatherByProvince = async (id) => {
        try {
            const response = await API.getWeatherByProvince(id);
            renderAreaList(areaListElements, response.data);
        } catch (error) {
            renderErrorElement(areaListElements, error);
        }
    };

    buttonSearchElement.addEventListener("click", () => {
        getWeather(inputSearchElement.value);
    });

    buttonSearchModalElement.addEventListener("click", () => {
        getWeather(inputSearchModalElement.value);
    });

    inputSearchElement.addEventListener("keydown", (event) => {
        if (event.keyCode == 13){
            getWeather(inputSearchElement.value);
        }
    });

    const updateDate = () => {
        moment.locale("id");
        document.querySelector("#clock").innerText = `${moment().format('dddd[,] Do MMM YYYY')}`;
    }

    getNavMenu();
    getIndonesiaArea();
    renderPage();
    updateDate();

}

export default main;