let header = document.getElementById("h1");

let xhhr = new XMLHttpRequest();

xhhr.open("GET", "welcome", true);

xhhr.onreadystatechange=function(){
    if (xhhr.readyState == 4 && xhhr.status == 200) {
        
        let response = xhhr.responseText;
        response = JSON.parse(response);
        let data = Object.values(response[0]);
        header.textContent += data[0];
    }
}

document.getElementById("logoutsubmitbttn").addEventListener('click', logoutfunc);
function logoutfunc(){
    xhhr.open("GET", "logout");
    xhhr.send();
  
  }

xhhr.send();