document.getElementById("submitbuttonforapprovedtable").addEventListener('click', aprovedfunc);

let xhhr2 = new XMLHttpRequest();
xhhr2.open("GET", "http://localhost:8082/reimbursement/approvedreimbursementsdataservlet");

function aprovedfunc(){

    let response = xhhr2.responseText;
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
xhhr2.send();
