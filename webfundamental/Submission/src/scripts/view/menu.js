class Menu {

    constructor(element) {
        this._element = element;
    }

    set menus(menus) {
        this._menus = menus;
        this.render();
    }

    renderError(message) {
        const parent = this._element.parentNode;
        const newChild = document.createElement("p");
        newChild.innerHTML = message;

        parent.replaceChild(newChild, this._element);
    }

    render() {
        this._element.innerHTML = "";
        this._menus.forEach(menu => {
            const menuItemElement = document.createElement("li");
            menuItemElement.innerHTML = menu.name;
            menuItemElement.setAttribute("id", menu.id);
            this._element.appendChild(menuItemElement);
        })
    }
}

export default Menu;