document.getElementById('form').addEventListener("submit", checkForm);

todayDate();

function todayDate() {
    let todayDate = new Date();
  
    let yyyy = todayDate.getFullYear();
    let mm = String(todayDate.getMonth() + 1).padStart(2, '0');
    let dd = String(todayDate.getDate()).padStart(2, '0');

    let today = yyyy + '-' + mm + '-' + dd;
    document.getElementById("dateOfWork").min = today;
}

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