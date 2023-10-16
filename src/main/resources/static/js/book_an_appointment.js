document.getElementById("dateOfWork").addEventListener('change', getAvailableTimeByDate);
document.getElementById("timeStartAt").addEventListener('change', getAvailableSpecialistsBySpecialtyNameAndDateOfWorkAndWorkTimeStartAt);
document.getElementById("dataSelectionForm").addEventListener("submit", checkForm);

/* todayDate();

function todayDate() {
  let today = new Date();

  let yyyy = today.getFullYear();
  let mm = String(today.getMonth() + 1).padStart(2, '0');
  let dd = String(today.getDate()).padStart(2, '0');

  let result = yyyy + '-' + mm + '-' + dd;
  document.getElementById('dateOfWork').min = result;
} */

//let dateToday = new Date();

$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

function getAvailableTimeByDate() {
    document.getElementById("noAvailableTimeMessage").innerHTML = "";
    document.getElementById("noAvailableTimeMessage").style.visibility = "hidden";
    let select = document.getElementById("timeStartAt");
    select.options.length = 0;

    document.getElementById("availableSpecialists").options.length = 0;

    let processingDate = document.getElementById('dateOfWork').value;
    //console.log(processingDate);

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

    let error = "";

    if(dateOfWork == "" || timeStartAt == "" || availableSpecialists == "") {
        event.preventDefault();
        error = "Заполните все поля";
        document.getElementById('message').innerHTML = error;
        document.getElementById('message').style.visibility = "visible";
    } else {
        document.getElementById('message').innerHTML = "";
        document.getElementById('message').style.visibility ="hidden";
    }
}