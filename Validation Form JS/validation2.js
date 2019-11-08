function validate2() {
    
    var image1 = getImage(emailCheck(document.forms["Contact Information"]["email"].value), "email");
    document.getElementById("Email").appendChild(image1);

    var image2 = getImage(telephoneCheck(document.forms["Contact Information"]["telephone"].value),"telephone");
    document.getElementById("Telephone").appendChild(image2);

    var image3 = getImage(addressCheck(document.forms["Contact Information"]["address"].value),"address");
    document.getElementById("Address").appendChild(image3); 

    if (emailCheck(document.forms["Contact Information"]["email"].value) 
        && telephoneCheck(document.forms["Contact Information"]["telephone"].value)
        && addressCheck(document.forms["Contact Information"]["address"].value)) {
        alert ("Form Submitted!")
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

function emailCheck(email) {
    atSplit = email.split('@');
    if (atSplit.length == 2 && alphaNumCheck(atSplit[0])) {
        periodSplit = atSplit[1].split('.')
        if (periodSplit.length == 2 && alphaNumCheck(periodSplit[0] + periodSplit[1])) {
            return true;
        }
    }

    return false;
}


function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function telephoneCheck(number){
    split1 = number.split('-');

    if (split1.length == 1 && !(isNaN(split1[0]))) {
        if(number.length == 10){
            return true;
        }
    }

    if (split1.length == 3 && !(isNaN(split1[0]))) {
        if (!(isNaN(split1[1])) && !(isNaN(split1[2])) && number.length == 12) {
            return true;
        }
    }
return false;
}

function addressCheck(address){
splitAddress = address.split(',');

if (splitAddress.length == 2 && isNaN(splitAddress[0] + splitAddress[1])) {
    return true;
}
return false;
}

