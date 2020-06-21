const renderAreaList = (element, result) => {
    element.areas = result;
};

const renderErrorElement = (element, message) => {
    element.renderError(message);
}

export {
    renderAreaList,
    renderErrorElement
};