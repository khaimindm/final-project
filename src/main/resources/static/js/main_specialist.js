document.getElementById("elForMainTable").addEventListener('click', userBtn);

var modal = document.getElementById("doctorsAppointmentModal");

var userEl = "";
var userId = "";

var cancelBtn = document.getElementById("cancelBtn");
var applyBtn = document.getElementById("applyBtn");

var doctorsAppointmentModalForm = document.getElementById("doctorsAppointmentModalForm");

var errorMessage = "";

function userBtn(event) {
    userEl = event.target;

    if (userEl.nodeName == "SPAN") {
        clearAreas();        
        modal.style.display = "block";
        userId = userEl.getAttribute("id");        
        document.getElementById('modalHeader').innerHTML = userEl.innerHTML + " User Id: " + userId;
    }
}

var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
    modal.style.display = "none";
    hideMessage();        
}

window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
      hideMessage();
    }
}

cancelBtn.onclick = function() {
    modal.style.display = "none";
    hideMessage();
}

function hideMessage() {
    document.getElementById("message").innerHTML = "";
    document.getElementById("message").style.visibility = "hidden";
}

function clearAreas() {
    doctorsAppointmentModalForm.medicalHistory.value = "";
    doctorsAppointmentModalForm.diagn.value = "";    
}


document.getElementById("doctorsAppointmentModalForm").addEventListener("submit", checkDoctorsAppointmentModalForm);

function checkDoctorsAppointmentModalForm(event) {
    event.preventDefault();

    var medicalHistory = doctorsAppointmentModalForm.medicalHistory.value;
    var diagn = doctorsAppointmentModalForm.diagn.value;

    if (medicalHistory == "" || diagn == "") {
        errorMessage = "Заполните все поля";
        document.getElementById("message").innerHTML = errorMessage;
        document.getElementById("message").style.visibility = "visible";
    } else {
        hideMessage();
    }
}