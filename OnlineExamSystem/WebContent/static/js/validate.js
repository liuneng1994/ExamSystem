function isEmpty(data) {
    if(data == '' || data == undefined) {
        return true;
    }
    return false;
}

function outOfLengthRange(data, minLength, maxLength) {
    if (minLength != undifine && maxLength != undefined) {
        return data.length < minLength || data.length > maxLength;
    }
    if (minLength != undifine && maxLength == undefined) {
        return data.length < minLength; 
    }
    if (minLength == undifine && maxLength != undefined) {
        return data.length > maxLength
    }
    return ture;
}

function isIllegalEmail(data) {
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    return !reg.test(data);
}