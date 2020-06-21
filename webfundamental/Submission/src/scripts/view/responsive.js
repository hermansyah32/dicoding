const renderPage = () => {
    // Change bacground navigation menu
    const navMenu = document.querySelector(".menu");
    const navMenuOffsetTop = navMenu.offsetTop - 50;
    document.addEventListener("scroll", (event) => {
        if (event.target.scrollingElement.scrollTop > navMenuOffsetTop) {
            navMenu.style.backgroundColor = "white";
        } else {
            navMenu.style.backgroundColor = "transparent";
        }
    })

    // Modal
    const searchButton = document.getElementById("button-search-modal");
    const searchModal = document.getElementById("search-modal");
    const closeButtonModal = document.querySelector(".modal-close-button");

    searchButton.addEventListener("click", () => {
        searchModal.style.display = "block";
        document.querySelector(".menu").style.position = "static";
    });

    closeButtonModal.addEventListener("click", () => {
        searchModal.style.display = "none";
        document.querySelector(".menu").style.position = "sticky";
    });


    window.onclick = function (event) {
        if (event.target == searchModal) {
            searchModal.style.display = "none";
            document.querySelector(".menu").style.position = "sticky";
        }
    }
}

export default renderPage;