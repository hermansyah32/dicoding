import './assets/styles.css';

import "regenerator-runtime";
import 'materialize-css/dist/css/materialize.min.css';
import 'materialize-css/dist/js/materialize.min.js';

import main from "./scripts/view/main.js";

document.addEventListener("DOMContentLoaded", main);
window.onload = function () { 
    document.getElementsByClassName("loader-section")[0].classList.remove("active")
}