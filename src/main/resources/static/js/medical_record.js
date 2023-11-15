document.getElementById('medical_record_form').addEventListener("submit", checkForm);

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