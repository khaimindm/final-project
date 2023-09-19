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
    var processingDate = document.getElementById('dateOfWork').value;

    console.log("processingDate: " + processingDate);
    console.log("specialtyName: " + specialtyName);

    const params = {
        processingDate: processingDate,
        specialtyName: specialtyName
    }
    
    /* const testJson = {
        "query": "Виктор Иван"
      } */

    $.getJSON('/specialists/availableTimeByDate', params, function(data) {
        let value = JSON.parse(data);
        let select = document.getElementById("timeStartAt");
        select.options.length = 0;        
        for (let i = 0; i < value.length; i++) {
            select[i] = new Option(value[i], value[i]);
            //const element = array[i];            
        }
        console.log(value);
    });

    var test = "Test"

    /* $.ajax({
        type: "Get",
        URL: "/specialists/availableTimeByDate",
        dataType: "json",
        data: JSON.stringify(testJson),
        success: function(data) {
            console.log(data);
        }
    }) */
}