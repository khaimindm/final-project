document.getElementById('form').addEventListener("submit", checkForm);

function checkForm(event) {
    let el = document.getElementById('form')
    let dateOfWork = el.dateOfWork.value;
    let checkboxError = "";
    
    if (!document.getElementById('workTimeMorning').checked && !document.getElementById('workTimeAfternoon').checked) {
        event.preventDefault();
        checkboxError = "Выберите одно или два значения";
        document.getElementById('checkboxMessage').innerHTML = checkboxError;
        document.getElementById('checkboxMessage').style.visibility = "visible";
    } else {
        document.getElementById('checkboxMessage').innerHTML = "";
        document.getElementById('checkboxMessage').style.visibility = "hidden";
    }
    
    let error = "";
    
    if (dateOfWork == "") {
        event.preventDefault();
        error = "Укажите дату";
        document.getElementById('message').innerHTML = error;
        document.getElementById('message').style.visibility = "visible";
    } else {
        document.getElementById('message').innerHTML = "";
        document.getElementById('message').style.visibility ="hidden";
    }
}