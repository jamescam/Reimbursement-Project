document.getElementById("submitbuttonfortable").addEventListener('click', manfunc);

let xhhhr = new XMLHttpRequest();
xhhhr.open("GET", 'findallpendingservlet', "true");

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

      
        let lastrow = row.insertCell(6);
        lastrow.innerHTML = "<input type=submit id='acceptbtn' value='accept'>";
        let lastrow2 = row.insertCell(7);
        lastrow2.innerHTML = "<input type=submit id='rejectbtn' value='reject'>";
        }

        
        document.getElementById("acceptbtn").addEventListener('click', tetfunc);
        document.getElementById("rejectbtn").addEventListener('click', newfunc);

        function tetfunc(){
          var status = "accept";
          let list = JSON.stringify(data[0]);
          
          xhhhr.open("POST", "pending");
          xhhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          const collect = `status=${status}&list=${list}`;
          xhhhr.send(collect);
        }

        function newfunc(){
            var status = "reject";
            let list = JSON.stringify(data[0]);
            
            xhhhr.open("POST", "pending");
            xhhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            const collect = `status=${status}&list=${list}`;
            xhhhr.send(collect);
        }
    }
    xhhhr.send();