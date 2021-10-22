document.getElementById("submitbuttonforRejectedtable").addEventListener('click', findrejectedfunc);

let xhhr3 = new XMLHttpRequest();
xhhr3.open("GET", "rejectedreimbursementsdataservlet");

function findrejectedfunc(){

  let response = xhhr3.responseText;
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
xhhr3.send();
