function validate() {
    
    var isValid = false;

    var image1 = getImage(alphaNumCheck(document.forms["Validation information"]["first name"].value),"first name");
    document.getElementById("First Name").appendChild(image1);

    var image2 = getImage(alphaNumCheck(document.forms["Validation information"]["last name"].value),"last name");
    document.getElementById("Last Name").appendChild(image2);

    var image3 = getImage(emptyCheck(document.forms["Validation information"]["gender"].value),"gender");
    document.getElementById("Gender").appendChild(image3);

    var image4 = getImage(emptyCheck(document.forms["Validation information"]["state"].value),"state");
    document.getElementById("State").appendChild(image4);

    if (alphaNumCheck(document.forms["Validation information"]["first name"].value ) 
        && alphaNumCheck(document.forms["Validation information"]["last name"].value)
        && emptyCheck(document.forms["Validation information"]["gender"].value)
        && emptyCheck(document.forms["Validation information"]["state"].value)) {
    window.location = './validation2.html';
    }

}

function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

function emptyCheck(option) {
    if (option == "empty"){
        return false;
    }
    else
        return true;
}


function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}


