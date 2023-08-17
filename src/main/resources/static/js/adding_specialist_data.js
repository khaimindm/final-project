document.getElementById('specialist_data_form').addEventListener("submit", checkForm);

todayDate();

function todayDate() {
    let today = new Date();

    let yyyy = today.getFullYear();
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let dd = String(today.getDate()).padStart(2, '0');

    let result = yyyy + '-' + mm + '-' + dd;
    document.getElementById('dateOfEmployment').max = result;
}

function checkForm(event) {
    let el = document.getElementById('specialist_data_form');

    let specialtyName = el.specialtyName.value;
    let degreeCertificate = el.degreeCertificate.value;
    let dateOfEmployment = el.dateOfEmployment.value;

    let error = "";

    if (specialtyName == "" || degreeCertificate == "" || dateOfEmployment == "") {
        event.preventDefault();
        error = "Заполните все поля"
        document.getElementById('message').innerHTML = error;
        document.getElementById('message').style.visibility = "visible";
    } else {
        document.getElementById('message').innerHTML = "";
        document.getElementById('message').style.visibility ="hidden";
    }
}