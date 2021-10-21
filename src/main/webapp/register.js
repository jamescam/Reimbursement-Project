const element = document.getElementById('register-button');
element.addEventListener('click', regfunc);

xhttp = new XMLHttpRequest();
xhttp.open('POST', "http://localhost:8082/reimbursement/register");

  function regfunc(){

      const email = document.getElementById("exampleInputEmail1").value;
      const password = document.getElementById("exampleInputPassword1").value;

      xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      const collect = `email=${email}&password=${password}`;
      xhttp.send(collect);
      }