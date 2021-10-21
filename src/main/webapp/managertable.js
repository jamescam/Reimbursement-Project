document.getElementById("submitbuttonfortable").addEventListener('click', manfunc);

let xhhhr = new XMLHttpRequest();
xhhhr.open("GET", 'http://localhost:8082/reimbursement/findallpendingservlet', "true");

function manfunc(){
  
    let response = xhhhr.responseText;
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

        let lastrow = row.insertCell(5);
        lastrow.innerHTML = "<button id='acceptbtn' value='accept'>Accept</button>";
        let lastrow2 = row.insertCell(6);
        lastrow2.innerHTML = "<button id='rejectbtn' value='reject'>Reject</button>";
        
        document.getElementById("acceptbtn").addEventListener('click', tetfunc);
        document.getElementById("rejectbtn").addEventListener('click', newfunc);

        function tetfunc(){
          var status = "accept";
          let userID = data[1];
          let description = data[0];
          let purchase = data[2];
          let amount = data[4];

          xhhhr.open("POST", "http://localhost:8082/reimbursement/pending");
          xhhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          const collect = `userID=${userID}&status=${status}&description=${description}&purchase=${purchase}&amount=${amount}&status=${status}`;
          xhhhr.send(collect);
        }

        function newfunc(){
            var status = "reject";
            let userID = data[1];
            let description = data[0];
            let purchase = data[2];
            let amount = data[4];
            
            xhhhr.open("POST", "http://localhost:8082/reimbursement/pending");
            xhhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            const collect = `userID=${userID}&status=${status}&description=${description}&purchase=${purchase}&amount=${amount}&status=${status}`;
            xhhhr.send(collect);
        }

        }
    }


    xhhhr.send();