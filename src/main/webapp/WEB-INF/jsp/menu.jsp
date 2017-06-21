<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<style>
body {
    margin: 0;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 10%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
</style>
</head>
<body>
<ul>
  <li><a href="#" onclick="go('view-user')">고객 명단</a></li>
</ul>
<div style="margin-left:10%;padding:1px 1px;height:100%;">
  <iframe id="frame" src="/view-user" height="100%" width="100%"></iframe>
</div>
 <script>
  function go(loc) {
    document.getElementById('frame').src = loc;
  }
  </script>
</body>
</html>