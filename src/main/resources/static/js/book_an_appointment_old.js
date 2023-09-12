document.getElementById("dateOfWork").addEventListener('change', getAvailableTimeByDate);

let specialtyName = document.getElementById('title').value;

const httpRequest = new XMLHttpRequest();

$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    console.log("Функция сработала: " + "header " + header + " " + "token " + token);
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

function getAvailableTimeByDate() {
    if (window.jQuery) {
        var vJq = jQuery.fn.jquery;        
        console.log("Версия: " + vJq);
      } else {
        console.log("Error")
      }

    let processingDate = document.getElementById('dateOfWork').value;
    httpRequest.onreadystatechange = timeContents;

    
    httpRequest.open("POST", "/availableTimeByDate", true);    

    const params = {
        processingDate: processingDate,
        specialtyName: specialtyName
    }

    httpRequest.setRequestHeader(
        "Content-Type",
        "application/json"
    );

    httpRequest.send(JSON.stringify(params));
}

function timeContents() {
    if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
        let response = JSON.parse(httpRequest.responseText);

        for (let i = 0; i < response.length; i++) {
            const optn = response[i];
            let el = document.createElement("option");
            el.textContent = optn;
            el.value = optn;
            select.appendChild(el);
        }

        /* let select = document.getElementById('timeStartAt');
        select.options[select.options.length] = new Option('Text 1', 'Value1'); */

        /* const result = [];
        for (let i in response){
            result.push([i, response [i]]);
        } */
    }
}