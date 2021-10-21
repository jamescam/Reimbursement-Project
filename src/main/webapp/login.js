const elementEmail = document.getElementById("email");
const elementPassword = document.getElementById("password");
const elementLoginButton = document.getElementById("login");

elementLoginButton.addEventListener("click", loginClick);

xhttp = new XMLHttpRequest();
xhttp.open('POST', "http://localhost:8082/reimbursement/login");

function loginClick(){
    const email = elementEmail.value;
    const password =elementPassword.value;

    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    const collect = `email=${email}&password=${password}`;

    xhttp.send(collect);

}