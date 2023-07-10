document.getElementById('authorization-form').addEventListener("submit", checkForm);

function checkForm(event) {
  let el = document.getElementById('authorization-form');

  let username = el.username.value;
  let password = el.password.value;

  let error = "";

  if (username == "" || password == "") {
  error = "Заполните все поля";
  document.getElementById('message').innerHTML = error;
  document.getElementById('message').style.visibility = "visible";
  event.preventDefault();
  } else {
  document.getElementById('message').innerHTML = "";
  document.getElementById('message').style.visibility ="hidden";
  }
}