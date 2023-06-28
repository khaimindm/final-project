document.getElementById('registration-form').addEventListener("submit", checkForm);

todayDate();

function todayDate() {
  let today = new Date();

  let yyyy = today.getFullYear();
  let mm = String(today.getMonth() + 1).padStart(2, '0');
  let dd = String(today.getDate()).padStart(2, '0');

  let result = yyyy + '-' + mm + '-' + dd;
  document.getElementById('birthday').max = result;
}

function checkForm(event) {
  event.preventDefault();
  let el = document.getElementById('registration-form');

  let login = el.login.value;
  let password = el.password.value;
  let fname = el.fname.value;
  let lname = el.lname.value;
  let birthday = el.birthday.value;

  let error = "";

  if(login == "" || password == "" || fname == "" || lname == "" || birthday == "") {
  error = "Заполните все поля";
  document.getElementById('message').innerHTML = error;
  document.getElementById('message').style.visibility = "visible";
  } else {
  document.getElementById('message').innerHTML = "";
  document.getElementById('message').style.visibility ="hidden";
  }
}