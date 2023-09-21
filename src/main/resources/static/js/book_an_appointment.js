document.getElementById("dateOfWork").addEventListener('change', getAvailableTimeByDate);
document.getElementById("dataSelectionForm").addEventListener("submit", checkForm);

todayDate();

function todayDate() {
  let today = new Date();

  let yyyy = today.getFullYear();
  let mm = String(today.getMonth() + 1).padStart(2, '0');
  let dd = String(today.getDate()).padStart(2, '0');

  let result = yyyy + '-' + mm + '-' + dd;
  document.getElementById('dateOfWork').min = result;
}

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

    let processingDate = document.getElementById('dateOfWork').value;

    let params = {
        processingDate: processingDate,
        specialtyName: specialtyName
    }

    $.getJSON('/specialists/availableTimeByDate', params, function(data) {
        let dataJson = JSON.stringify(data);
        let value = JSON.parse(dataJson);
        if (value !=0 ) {
        let select = document.getElementById("timeStartAt");
        select.options.length = 0;
        for (let i = 0; i < value.length; i++) {
            select[i] = new Option(value[i], value[i]);
        }
        } else {
        let select = document.getElementById("timeStartAt");
        select.options.length = 0;
        let message = "Нет записи на выбранную дату"
        document.getElementById("noAvailableTimeMessage").innerHTML = message;
        document.getElementById("noAvailableTimeMessage").style.visibility = "visible";
        }
    });
}

function checkForm(event) {
    let el = document.getElementById("dataSelectionForm");

    let dateOfWork = el.dateOfWork.value;
    let timeStartAt = el.timeStartAt.value;

    let error = "";

    if(dateOfWork == "" || timeStartAt == "") {
        event.preventDefault();
        error = "Заполните все поля";
        document.getElementById('message').innerHTML = error;
        document.getElementById('message').style.visibility = "visible";
    } else {
        document.getElementById('message').innerHTML = "";
        document.getElementById('message').style.visibility ="hidden";
    }
}