document.getElementById("dateOfWork").addEventListener('change', getAvailableTimeByDate);

let specialtyName = document.getElementById('title').value;

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

    const params = {
        processingDate: processingDate,
        specialtyName: specialtyName
    }    

    /* $.getJSON('/specialists/availableTimeByDate', function(data) {
        console.log(data);
    }); */

    let test = "Test"

    $.ajax({
        type: "Get",
        URL: "/specialists/availableTimeByDate",
        dataType: "json",
        data: JSON.stringify(test),
        success: function(data) {
            console.log(data);
        }
    })
}