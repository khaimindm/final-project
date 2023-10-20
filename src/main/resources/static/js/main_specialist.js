document.getElementById("date").addEventListener('change', getPatientListByDate);

let today;

todayDate();

function todayDate() {
    let todayDate = new Date();
  
    let yyyy = todayDate.getFullYear();
    let mm = String(todayDate.getMonth() + 1).padStart(2, '0');
    let dd = String(todayDate.getDate()).padStart(2, '0');
  
    today = yyyy + '-' + mm + '-' + dd;
    
    document.getElementById('date').value = today;
    document.getElementById('date').min = today;
}

$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

getPatientListByDate();

function getPatientListByDate() {
    let processingDate = document.getElementById('date').value;

    let param = {
        processingDate: processingDate
    }

    $.getJSON('/patientListByDate', param, function(data){
        let dataJson = JSON.stringify(data);
        let patientList = JSON.parse(dataJson);
        let tableContent = "";
        if (patientList != 0) {
            for(let i in patientList) {
                let patientId = patientList[i].patientId;
                let patientLinkById = "/appointment/" + patientId;
                tableContent += "<tr>";
                tableContent += "<td>" + "<a href=" + "'" + patientLinkById + "'" + ">" + patientList[i].lastName + " " + patientList[i].firstName + "</a>" + "</td>"
                + "<td>" + patientList[i].time + "</td>";
                tableContent += "</tr>";
            }
        document.getElementById("patientList").innerHTML = tableContent;
        } else {
            document.getElementById("patientList").innerHTML = "";
        }
    });
}

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