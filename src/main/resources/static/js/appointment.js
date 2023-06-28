var modal = document.getElementById("confirmationModal");

var btn = document.getElementById("applyBtn");

var span = document.getElementsByClassName("close")[0];

var date = document.getElementById("date");

var cancelBtn = document.getElementById("cancelBtn");

var patientFName;
var patientLName;
var selectedDate;
var selectedTime

document.addEventListener("DOMContentLoaded", function(){
    var d = new Date();
    
    var day = d.getDate();
    if (day < 10) {
        day = "0" + day;
    }

    var month = d.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }

    var year = d.getFullYear();
    var name_input = document.getElementById("date");    
    var currentDate = year + "-" + month + "-" + day;
    name_input.value = currentDate;
    selectedDate = currentDate;     
});

btn.onclick = function() {    
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    if (fname == "" || lname == "") {
       var message = "Заполните все поля";
        document.getElementById("message").innerHTML = message;
        document.getElementById("message").style.visibility = "visible";
        return;       
    } else {
        document.getElementById("message").innerHTML = "";
        document.getElementById("message").style.visibility = "hidden";
        modal.style.display = "block";
        patientFName = fname;
        patientLName = lname;        
        document.getElementById("fnameConfirmation").value = patientFName;
        document.getElementById("lnameConfirmation").value = patientLName;
        document.getElementById("dateConfirmation").value = selectedDate;
        selectedTime = document.getElementById("availableTime").value;        
        document.getElementById("timeConfirmation").value = selectedTime;
    }
}

span.onclick = function() {
    modal.style.display = "none";
}

date.onchange = function() {
    selectedDate = date.value;    
}

window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
}

cancelBtn.onclick = function() {
    modal.style.display = "none";
}