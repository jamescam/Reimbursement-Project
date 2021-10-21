document.getElementById("submitbuttonforapprovedtable").addEventListener('click', allApproved);

let xttp = new XMLHttpRequest();
xttp.open("GET", 'http://localhost:8082/reimbursement/ApprovedReimbursementsServlet');

function allApproved(){
    let response = xttp.responseText;
    response = JSON.parse(response);

    let table = document.querySelector("table");
    let data = Object.values(response);
    for(i=0; i < data.length; i++){
        let data = Object.values(response[i]);
        let thead = table.createTHead();
        let row = thead.insertRow();
        for (let key of data) {
          let th = document.createElement("th");
          let text = document.createTextNode(key);
          th.appendChild(text);
          row.appendChild(th);
        }
    }

  }
  xttp.send();
