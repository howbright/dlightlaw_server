<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
  <h2>Hello! ${name}</h2>
  Password:
<form action="/login" method="post">
   <input id="pwd" type="password" name="pwd" size='35' />
   <input type="submit" value="Submit">
</form>

</body>
</html>