// Activate sidebar nav
const elemens = document.querySelectorAll(".sidenav");
M.Sidenav.init(elemens);

function loadLeftNav() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status != 200) return;

            document.querySelectorAll(".leftnav").forEach(element => {
                element.innerHTML = xhttp.responseText;
            });

            linkListener();
        }
    };
    xhttp.open("GET", "pages/leftnav.html", true);
    xhttp.send();
}

function loadRightNav() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status != 200) return;

            document.querySelectorAll(".rightnav").forEach(element => {
                element.innerHTML = xhttp.responseText;
            });

            linkListener();
        }
    };
    xhttp.open("GET", "pages/rightnav.html", true);
    xhttp.send();
}

function loadNav() {
    loadLeftNav();
    loadRightNav();
}

function loadNavMobile() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status != 200) return;

            document.querySelectorAll(".sidenav").forEach(element => {
                element.innerHTML = xhttp.responseText;
            });

            linkListener();
        }
    };
    xhttp.open("GET", "pages/nav-mobile.html", true);
    xhttp.send();
}

// Load page content
function loadPage(page) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            const content = document.querySelector("#body-content");
            if (this.status == 200) {
                content.innerHTML = xhttp.responseText;
                document.querySelectorAll(".sidenav li, .leftnav li, .rightnav li").forEach(function (element) {
                    if (element.id == `nav-${page}`) {
                        element.classList.add("active");
                    } else {
                        element.className = "";
                    }
                });
                linkListener();
            } else if (this.status == 404) {
                content.innerHTML = "<p>Halaman tidak ditemukan.</p>";
            } else {
                content.innerHTML = "<p>Ups.. halaman tidak dapat diakses.</p>";
            }
        }
    };
    xhttp.open("GET", "pages/" + page + ".html", true);
    xhttp.send();
}

// Load button link
function linkListener() {
    document.querySelectorAll("a").forEach(function (element) {
        element.addEventListener("click", event => {
            const sidenav = document.querySelector(".sidenav");
            M.Sidenav.getInstance(sidenav).close();

            if (event.target.getAttribute("href").substr(0, 1) == "#") {
                const page = event.target.getAttribute("href").substr(1);
                console.log('page :>> ', page);
                loadPage(page);
            }
        });
    });
}

export {
    loadNav,
    loadNavMobile,
    loadPage
};