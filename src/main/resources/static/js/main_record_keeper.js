var addModal = document.getElementById("addModal");
var deleteModal = document.getElementById("deleteModal");

var addSpecBtn = document.getElementById("addSpecBtn");
var deleteSpecBtn = document.getElementById("deleteSpecBtn");

var addSpan = document.getElementsByClassName("addClose")[0];
var deleteSpan = document.getElementsByClassName("deleteClose")[0];

var cancelBtnAddModal = document.getElementById("cancelBtnAddModal");
var cancelBtnDeleteModal = document.getElementById("cancelBtnDeleteModal");

var errorMessageAddModal = "";
var errorMessageDeleteModal = "";

addSpecBtn.onclick = function() {
    addModal.style.display = "block";
}

deleteSpecBtn.onclick = function() {
    deleteModal.style.display = "block";
}

addSpan.onclick = function() {
    addModal.style.display = "none";
    hideMessageAddModal();
}

deleteSpan.onclick = function() {
    deleteModal.style.display = "none";
    hideMessageDeleteModal();
}

window.onclick = function(event) {
    if (event.target == addModal) {
        addModal.style.display = "none";
        hideMessageAddModal();
    }

    if (event.target == deleteModal) {
        deleteModal.style.display = "none"
        hideMessageDeleteModal();
    }
}

cancelBtnAddModal.onclick = function() {
    addModal.style.display = "none";
    hideMessageAddModal();
}

cancelBtnDeleteModal.onclick = function() {
    deleteModal.style.display = "none";
    hideMessageDeleteModal();
}

document.getElementById('addNewSpecForm').addEventListener("submit", checkAddForm);
document.getElementById('deleteSpecForm').addEventListener("submit", checkDeleteForm);

function checkAddForm(event) {
    event.preventDefault();
    var addForm = document.getElementById('addNewSpecForm');

    var addValue = addForm.nameOfTheSpecialist.value;

    if (addValue == "") {
        errorMessageAddModal = "Заполните поле";
        document.getElementById('messageAddNewSpecForm').innerHTML = errorMessageAddModal;
        document.getElementById('messageAddNewSpecForm').style.visibility = "visible";
    } else {
        hideMessageAddModal();
    }
}

function checkDeleteForm(event) {
    event.preventDefault();
    var deleteForm = document.getElementById('deleteSpecForm');

    var deleteValue = deleteForm.nameOfTheSpecToBeDeleted.value;
    
    if (deleteValue == "") {
        errorMessageDeleteModal = "Выберите специалиста";
        document.getElementById('messageDeleteSpecForm').innerHTML = errorMessageDeleteModal;
        document.getElementById('messageDeleteSpecForm').style.visibility = "visible";
    } else {
        hideMessageDeleteModal();
    }
}

function hideMessageAddModal() {
    document.getElementById('messageAddNewSpecForm').innerHTML = "";
    document.getElementById('messageAddNewSpecForm').style.visibility = "hidden";
}

function hideMessageDeleteModal() {
    document.getElementById('messageDeleteSpecForm').innerHTML = "";
    document.getElementById('messageDeleteSpecForm').style.visibility = "hidden";
}