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
    width: 120px;
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
  <h2>기간제 아이템 입력 (일부유저) </h2>

user 뽑는 쿼리 : (결과는 user id의 list여야함.) <br> ex) select id from USER<br>
<textarea id="query" form="usrform" name="query" rows="10" cols="100" >${query}</textarea><br>
<form id="usrform" action="/limited-item-insert" method="post" >
  <label class='flabel'>itemCode:</label><input type="number" name="itemCode" min=0 ><br>
  <label class='flabel'>amount:</label><input type="number" name="amount" min = 0><br>
  <label class='flabel'>closingDate:</label><input type="date" name="closingDate"><br>
  <label class='flabel'></label><input type="time" name="closingTime"><br>
  <input class='btn' type="submit" onclick="onSubmit()" value="해당유저들에게 줄 기간제 아이템 DB에 입력">
</form>
</div>
</body>
</html>