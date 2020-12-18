import {
    loadNav,
    loadPage,
    loadNavMobile
} from './nav.js';

const main = () => {
    loadNav();
    loadNavMobile();
    let page = window.location.hash.substr(1);
    if (page === "") page = "home";
    loadPage(page);
}
export default main;