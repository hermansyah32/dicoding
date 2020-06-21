import './area-item.js';

class AreaList extends HTMLElement {
    constructor() {
        super();
        this.shadowDOM = this.attachShadow({
            mode: "open"
        });
    }

    set areas(areas) {
        this._areas = areas;
        this.render();
    }

    set clickEvent(event) {
        this._clickEvent = event;
        this.render();
    }

    renderError(message) {
        this.shadowDOM.innerHTML = `
        <style>
            h2 {
                text-align: center;
            }
            placeholder  {
                font-weight: lighter;
                color: rgba(0,0,0,0.5);
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }
        </style>`;
        this.shadowDOM.innerHTML += `<h2 class="placeholder">${message}</h2>`;
    }

    styling() {
        this.shadowDOM.innerHTML = `
        <style>
            area-list {
                padding: 10px;
            }
        </style>`;
    }

    render() {
        this.styling();
        this._areas.forEach(area => {
            const areaItemElement = document.createElement("area-item");
            areaItemElement.area = area;
            this.shadowDOM.appendChild(areaItemElement);
        })
    }
}

customElements.define("area-list", AreaList);