document.getElementById('authorization-form').addEventListener("submit", checkForm);

function checkForm(event) {
  event.preventDefault();
  let el = document.getElementById('authorization-form');

  let login = el.username.value;
  let password = el.password.value;

  let error = "";

  if (login == "" || password == "") {
  error = "Заполните все поля";
  document.getElementById('message').innerHTML = error;
  document.getElementById('message').style.visibility = "visible";
  } else {
  document.getElementById('message').innerHTML = "";
  document.getElementById('message').style.visibility ="hidden";
  }
}