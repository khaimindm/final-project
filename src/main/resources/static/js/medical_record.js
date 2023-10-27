document.getElementById('medical_record_form').addEventListener("submit", checkForm);

/* todayDate();

function todayDate() {
    let today = new Date();

    let yyyy = today.getFullYear();
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let dd = String(today.getDate()).padStart(2, '0');

    let result = yyyy + '-' + mm + '-' + dd;

    
} */

function checkForm(event) {
    let el = document.getElementById('medical_record_form');

    let examination = el.examination.value;
    let diagnosis = el.diagnosis.value;
    let assignedTherapy = el.assignedTherapy.value;

    let error = "";

    if (examination == "" && diagnosis == "" && assignedTherapy == "") {
        event.preventDefault();
        error = "Все поля пустые. Заполните хотя бы одно поле"
        document.getElementById('message').innerHTML = error;
        document.getElementById('message').style.visibility = "visible";
    } else {
        document.getElementById('message').innerHTML = "";
        document.getElementById('message').style.visibility ="hidden";
    }
}