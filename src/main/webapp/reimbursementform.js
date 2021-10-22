let xhhr = new XMLHttpRequest();
xhhr.open("POST", "reimbursementformservlet");

document.getElementById("submitform").addEventListener('click', formfunc);
document.getElementById("logoutsubmitbttn").addEventListener('click', logoutfunc);

function formfunc(){
    let response = xhhr.responseText;
    
    let formgroup0 = document.getElementsByClassName("form-group")[0];

    let UserID = formgroup0[0].value;
    let purchase = formgroup0[1].value;
    let description = formgroup0[2].value;
    let total = formgroup0[3].value;


    xhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      const collect = `userID=${UserID}&purchase=${purchase}&description=${description}&total=${total}`;
      xhhr.send(collect);
}

function logoutfunc(){
  xhhr.open("GET", "logout");
  xhhr.send();

}