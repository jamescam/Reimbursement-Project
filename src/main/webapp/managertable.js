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
        lastrow.innerHTML = "<button id='acceptbtn'class=bttnclass value='accept'>accept</button";
        let lastrow2 = row.insertCell(7);
        lastrow2.innerHTML = "<button id='rejectbtn' class=bttnclass value='reject'>reject</button>";
  }

  document.addEventListener('click',function(e){
    if(e.target && e.target.id == 'acceptbtn'){
      var status = "accept";
      var button = e.target;
      var cell = button.parentNode;
      var row = cell.parentNode;
      var rowValues = row.querySelectorAll('th');
      var description = rowValues[0].innerHTML;
      var id = rowValues[1].innerHTML;
      var UserID = rowValues[2].innerHTML;
      var purchaseDate = rowValues[3].innerHTML;
      var totalAmount = rowValues[5].innerHTML;

      xhhhr.open("POST", "pending");
      xhhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      const collect = `status=${status}&description=${description}&id=${id}&UserID=${UserID}&purchaseDate=${purchaseDate}&totalAmount=${totalAmount}`;
      xhhhr.send(collect);
    
     }else if(e.target && e.target.id == 'rejectbtn'){
      var status = "reject";
      var button = e.target;
      var cell = button.parentNode;
      var row = cell.parentNode;
      var rowValues = row.querySelectorAll('th');
      var description = rowValues[0].innerHTML;
      var id = rowValues[1].innerHTML;
      var UserID = rowValues[2].innerHTML;
      var purchaseDate = rowValues[3].innerHTML;
      var totalAmount = rowValues[5].innerHTML;
      
      xhhhr.open("POST", "pending");
      xhhhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      const collect = `status=${status}&description=${description}&id=${id}&UserID=${UserID}&purchaseDate=${purchaseDate}&totalAmount=${totalAmount}`;
      xhhhr.send(collect);
     }
  });

}

    xhhhr.send();