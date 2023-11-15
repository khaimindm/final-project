document.getElementById("dateOfWork").addEventListener('change', getAvailableTimeByDate);
document.getElementById("timeStartAt").addEventListener('change', getAvailableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt);
document.getElementById("dataSelectionForm").addEventListener("submit", checkForm);

$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

viewOfSelectElementFromRole();

function viewOfSelectElementFromRole() {
    let patientSelectElement = document.getElementById("patient");
    patientSelectElement.options.length = 0;

    if (currentUserRole == "ROLE_PATIENT") {
        patientSelectElement.options[0] = new Option(currentUserLastName + " " + currentUserFirstName, currentUserId);
    } else {
        $.getJSON('/patients/all', function (data) {
            let dataJson = JSON.stringify(data);
            let value = JSON.parse(dataJson);

            if (value != 0) {
                patientSelectElement.options[0] = new Option("", "");
                for(index in value) {
                    patientSelectElement.options[patientSelectElement.options.length] = new Option(value[index], index);
                }
            } else {
                patientSelectElement = new Option("Нет ни одного пациента")
            }
        });
    }
}

function getAvailableTimeByDate() {
    document.getElementById("noAvailableTimeMessage").innerHTML = "";
    document.getElementById("noAvailableTimeMessage").style.visibility = "hidden";
    let select = document.getElementById("timeStartAt");
    select.options.length = 0;

    document.getElementById("availableSpecialists").options.length = 0;

    let processingDate = document.getElementById('dateOfWork').value;

    let params = {
        processingDate: processingDate,
        specialtyName: specialtyName
    }

    $.getJSON('/specialists/availableTimeByDate', params, function(data) {
        let dataJson = JSON.stringify(data);
        let value = JSON.parse(dataJson);
        if (value != 0) {
            for (let i = 0; i < value.length; i++) {
                select[i] = new Option(value[i], value[i]);
            }
            getAvailableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt();
        } else {
            let message = "Нет записи на выбранную дату"
            document.getElementById("noAvailableTimeMessage").innerHTML = message;
            document.getElementById("noAvailableTimeMessage").style.visibility = "visible";
        }
    });
}

function getAvailableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt() {
    let date = document.getElementById('dateOfWork').value;
    let time = document.getElementById("timeStartAt").value;

    let params = {
        specialtyName: specialtyName,
        dateOfWork: date,
        workTimeStartAt: time
    }

    $.getJSON('/specialists/availableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt', params, function(data) {
        let dataJson = JSON.stringify(data);
        let value = JSON.parse(dataJson);
        let select = document.getElementById("availableSpecialists");
        select.options.length = 0;
        for (index in value) {
            select.options[select.options.length] = new Option(value[index], index);
        }
    })    
}

function checkForm(event) {
    let el = document.getElementById("dataSelectionForm");

    let dateOfWork = el.dateOfWork.value;
    let timeStartAt = el.timeStartAt.value;
    let availableSpecialists = el.availableSpecialists.value;
    let patient = el.patient.value;

    let error = "";

    if(dateOfWork == "" || timeStartAt == "" || availableSpecialists == "" || patient == "") {
        event.preventDefault();
        error = "Заполните все поля";
        document.getElementById('message').innerHTML = error;
        document.getElementById('message').style.visibility = "visible";
    } else {
        document.getElementById('message').innerHTML = "";
        document.getElementById('message').style.visibility ="hidden";
    }
}