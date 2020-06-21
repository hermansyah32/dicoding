import {
    loadNav,
    loadPage
} from './nav.js';
// import runtime from 'serviceworker-webpack-plugin/runtime';

const main = () => {
    loadNav();
    let page = window.location.hash.substr(1);
    if (page == "") page = "home";
    loadPage(page);

    //register service worker
    // if ('serviceWorker' in navigator) {
    //     const registration = runtime.register();
    //     registration
    //         .then(function () {
    //             console.log("Pendaftaran ServiceWorker berhasil");
    //         })
    //         .catch(function () {
    //             console.log("Pendaftaran ServiceWorker gagal");
    //         });
    // } else {
    //     console.log("ServiceWorker belum didukung browser ini.");
    // }
}

export default main;