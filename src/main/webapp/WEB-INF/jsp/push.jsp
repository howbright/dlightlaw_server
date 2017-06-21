<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <style>
  .{text-decoration: none;}
.flabel{
  display: inline-block;
   
    font-size: 17px;
    width: 180px;
   }
 .btn {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    padding : 5px;
    margin: 3px

}
input[type=text], select {
   
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    padding : 5px;
    float:right;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
    width:380px;
    margin: 5px
}

input[type=number]{
    width: 255px;
} 

input[type=date]{
    width: 255px;
} 
input[type=time]{
    width: 255px;
} 
textarea{
    margin: 5;
    width: 350px;
} 
</style>
</head>
<body>
<div>
<h2>광역푸시</h2>
<form id="pushForm" action="push" method="post" onsubmit="return confirm('정말로 푸시 할꺼임???????? 5분정도 걸림');" >
  <label class='flabel'>channel_ids(콤마로 구분):</label><input type="text" name="channelIds" size='35' ><br>
  <label class='flabel'>message:</label><textarea name="msg" form="pushForm" rows="10" cols="100"></textarea><br>
  <input class='btn' type="submit" onclick="onSubmit()" value="광역푸시 실행하기">
</form>
</div>
<br>


<button type="button" onclick="loadResult()" class='btn'>결과보기</button><br>

<div id="cntResult"><h2></h2></div>

<br>
<button type="button" onclick="init()" class='btn'>푸시서버 초기화 </button>
<br><br><br><br><br>
<div>
<h3>구글 테스트하기</h3>
<form id="gTestform" action="testGoogle" method="post" >
   <label class='flabel'>channel_ids(콤마로 구분):</label><input type="text" name="channelIds" size='35' ><br>
  <label class='flabel'>tocken:</label><input type="text" name="tocken" size='35' ><br>
  <label class='flabel'>message:</label><textarea name="msg" form="gTestform" rows="10" cols="100"></textarea><br>
  <input class='btn' type="submit" onclick="onSubmit()" value="google에 특정 토큰 푸시 테스트">
</form>
</div>
<div>
<h3>아이폰 테스트하기</h3>
<form id="iTestform" action="testIphone" method="post" >
   <label class='flabel'>channel_ids(콤마로 구분):</label><input type="text" name="channelIds" size='35' ><br>
  <label class='flabel'>tocken:</label><input type="text" name="tocken" size='35'><br>
  <label class='flabel'>message:</label><textarea name="msg" form="iTestform" rows="10" cols="100"></textarea><br>
  <input class='btn' type="submit" onclick="onSubmit()" value="iphone 특정 토큰 푸시 테스트">
</form>
</div>

<script>
function loadDoc() {

 var x;
    if (confirm("정말로 푸시 할꺼임???????? 4분정도 걸림 ") == true) {
       var msg = document.getElementById("message").value;
       var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
          if (xhttp.readyState == 4 && xhttp.status == 200) {
                 document.getElementById("result").innerHTML = 'xhttp.status : ' + xhttp.status;
           }
           else
           {
           	     document.getElementById("result").innerHTML = 'xhttp.status : ' + xhttp.status;
           }
       };
   // xhttp.open("GET", 'http://'+ip+':'+port+'/push?message=' + msg, true);
   xhttp.open("GET", '/push?message=' + msg, true);
    xhttp.send();
  } else {
       
    }
  
}

function loadResult() {
    var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
          if (xhttp.readyState == 4 && xhttp.status == 200) {
              document.getElementById("cntResult").innerHTML = xhttp.responseText;
           }
           else
           {
               document.getElementById("cntResult").innerHTML = 'xhttp.status : ' + xhttp.status;
           }
       };
   // xhttp.open("GET", 'http://'+ip+':'+port+'/push?message=' + msg, true);
   xhttp.open("GET", '/count', true);
    xhttp.send();

}

function init() {
    var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
          if (xhttp.readyState == 4 && xhttp.status == 200) {
              document.getElementById("cntResult").innerHTML = xhttp.responseText;
           }
           else
           {
               document.getElementById("cntResult").innerHTML = 'xhttp.status : ' + xhttp.status;
           }
       };
   // xhttp.open("GET", 'http://'+ip+':'+port+'/push?message=' + msg, true);
   xhttp.open("GET", '/init', true);
    xhttp.send();

}
</script>

</body>
</html>