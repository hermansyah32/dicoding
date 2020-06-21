function getDay(date, locale) {
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return date.toLocaleDateString(locale,options);
}

function updateDate() {
    var date = new Date();
    document.querySelector("#clock").innerText = `${getDay(date, 'id-ID')}`;
}

function setShownColTable(){
    var counts = [0, 6, 12, 18];
    var today = new Date();
    // var hour = today.getHours();
    var hour = 4;
    var closestHour = counts.reduce((prev, curr, index) => {
        return ((curr - hour) < (prev - hour) ? curr : prev);
    });

    var cells = document.querySelectorAll(`.time-${closestHour}`);
    cells.forEach((cell) => {
        cell.classList.add("current-col");
    })
}

document.addEventListener("DOMContentLoaded", () => {
    // Change bacground navigation menu
    var navMenu = document.querySelector(".menu");
    var navMenuOffsetTop = navMenu.offsetTop - 50;
    document.addEventListener("scroll", (event) => {
        if (event.target.scrollingElement.scrollTop > navMenuOffsetTop){
            navMenu.style.backgroundColor =  "white";
        }else {
            navMenu.style.backgroundColor = "transparent";
        }
    })

    // Responsive in javascript width 678
    var mediaQuery = window.matchMedia("(max-width: 678px)");
    if (mediaQuery.matches){
        // change row span
        document.querySelector(".forecast-header").colSpan = 0;
    }else {
        document.querySelector(".forecast-header").colSpan = 4;
    }
    mediaQuery.addListener((media) => {
        if (media.matches){
            document.querySelector(".forecast-header").colSpan = 0;
        }else {
            document.querySelector(".forecast-header").colSpan = 4;
        }
    })
    
    // Modal
    var searchButton = document.getElementById("search_button");
    var searchModal = document.getElementById("search-modal");
    var closeButtonModal = document.querySelector(".modal-close-button");
    console.log(closeButtonModal);

    searchButton.addEventListener("click", () => {
        searchModal.style.display = "block";
        document.querySelector(".menu").style.position = "static";
    });

    closeButtonModal.addEventListener("click", () => {
        searchModal.style.display = "none";
        document.querySelector(".menu").style.position = "sticky";
    });


    window.onclick = function(event) {
        if (event.target == searchModal) {
            searchModal.style.display = "none";
            document.querySelector(".menu").style.position = "sticky";
        }
      }
});

 
updateDate();
setShownColTable();