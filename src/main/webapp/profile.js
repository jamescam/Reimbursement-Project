let list = document.getElementById("li1");
let secondList = document.getElementById("li2");
let thirdList = document.getElementById("li3");

let xhhr = new XMLHttpRequest();

xhhr.open("GET", "profiledata", true);

xhhr.onreadystatechange=function(){
    if (xhhr.readyState == 4 && xhhr.status == 200) {
        
        let response = xhhr.responseText;
        response = JSON.parse(response);
        let data = Object.values(response[0]);
        list.textContent += data[0];
        secondList.textContent += data[1];
        thirdList.textContent += data[2];
    }
}
xhhr.send();