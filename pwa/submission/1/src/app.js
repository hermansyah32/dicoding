import './assets/styles.css';

import "regenerator-runtime";
import 'materialize-css/dist/css/materialize.min.css';
import 'materialize-css/dist/js/materialize.min.js';
import runtime from 'serviceworker-webpack-plugin/lib/runtime';

import main from "./scripts/view/main.js";

document.addEventListener("DOMContentLoaded", main);
if ('serviceWorker' in navigator) {
    const registration = runtime.register();
}
window.onload = function () {
    document.getElementsByClassName("loader-section")[0].classList.remove("active")
}