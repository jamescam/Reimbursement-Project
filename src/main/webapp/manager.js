document.getElementById("submitbuttonfortable").addEventListener('click', manfunc);

let xhhr = new XMLHttpRequest();
xhhr.open("GET", "http://localhost:8082/reimbursement/manager", true);


function manfunc(){
    let response = xhhr.responseText;
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

  xhhr.send();