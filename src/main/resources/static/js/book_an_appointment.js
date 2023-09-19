document.getElementById("dateOfWork").addEventListener('change', getAvailableTimeByDate);

$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    console.log("Функция сработала: " + "header " + header + " " + "token " + token);
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

function getAvailableTimeByDate() {
    let processingDate = document.getElementById('dateOfWork').value;

    console.log("processingDate: " + processingDate);
    console.log("specialtyName: " + specialtyName);

    let params = {
        processingDate: processingDate,
        specialtyName: specialtyName
    }

    $.getJSON('/specialists/availableTimeByDate', params, function(data) {
        let dataJson = JSON.stringify(data);
        let value = JSON.parse(dataJson);
        let select = document.getElementById("timeStartAt");
        select.options.length = 0;        
        for (let i = 0; i < value.length; i++) {
            select[i] = new Option(value[i], value[i]);
        }
    });
}