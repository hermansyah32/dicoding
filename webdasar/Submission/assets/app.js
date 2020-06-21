function getDay(date, locale) {
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return date.toLocaleDateString(locale,options);
}

function updateDate() {
    var date = new Date();
    document.querySelector("#clock").innerText = `${getDay(date, 'id-ID')}`;
}

updateDate();