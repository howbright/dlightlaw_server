<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
 
</style>
<script type="text/ecmascript" src="<c:url value='/js/jquery.min.js' />"></script> 
    <!-- This is the Javascript file of jqGrid -->   
    <script type="text/ecmascript" src="<c:url value='/js/trirand/jquery.jqGrid.min.js' />"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc.
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="<c:url value='/js/trirand/i18n/grid.locale-kr.js' />"></script>
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/jquery-ui.css' />" />
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/trirand/ui.jqgrid.css' />" />
</head>
<body>
  <div>
<button class='btn' id="btn3" value="검색" type="button" onclick="transport()">엑셀 to 데이터베이스</button>
<form id="usrform" action="/room-search" method="post" >
 <!--  <label class='flabel'>검색어:</label><input id="search" type="text" value="" name="searchKey" >
  <button class='btn' id="btn3" value="검색" type="button" onclick="bindData()">검색</button><br> -->
 
</form>


</div>
<div id="container" style="height:600">
  <table id="jqGrid"></table>
  <div id="jqGridPager"></div>
</div>
    <script type="text/javascript"> 
      var transport = function(){
         $.ajax({
                   type : "GET",
                   url: "/transport-user-excel-to-db",
                   data: { 
                     },
                   dataType: "json",
                   success: function(jData){
                   }
               });
       }
     var bindData = function() {
       $("#jqGrid").jqGrid('setGridParam', { 
            postData: {
                      "page" :1, "rowNum" : 100 }
        }).trigger('reloadGrid'); 
            
        }
//, formatoptions: { srcformat: 'u', newformat: "y-m-d H:i:s" }
       $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: "/user-list",
                mtype: "POST",
                postData : { rowNum: 100},
                datatype: "json",
                id : "userId",
                colModel: [
                    { label: '아이디', name: 'userId', key: true, width: 75 },
                    { label: '이름', name: 'name', key: false, width: 75 },
                    { label: '전화번호', name: 'phoneNumber', key: false, width: 55 },
                    { label: '회사', name: 'company', key: false, width: 55 },
                    { label: '부서', name: 'department', key: false, width: 45 },
                    { label: '직책', name: 'position', key: false, width: 45 },
                    { label: '이메일', name: 'email', key: false, width: 75 },
                    { label: '회사 전화번호', name: 'companyPhoneNumber', key: false, width: 55 },
                    { label: '팩스', name: 'companyFax', key: false, width: 55 },
                    { label: '회사 주소', name: 'companyAddress', key: false, width: 55 },
                    { label: '이메일 수신여부', name: 'allowMessageFlag', key: false, width: 55 },
                    { label: '관심분야', name: 'interestIndustry', key: false, width: 55 }

  //                     private int userId;
  // private String name;
  // private String phoneNumber;
  // private String company;
  // private String department;
  // private String position;
  // private String email;
  // private String companyPhoneNumber;
  // private String companyFax;
  // private String companyAddress;
  // private boolean allowMessageFlag;
  // private long interestIndustry;
                       
                ],
                viewrecords: true,
                multiselect:true,
                width: $("#container").width(),
                height: $("#container").height(),
                rowNum: 100,
                pager: "#jqGridPager"
            });
        });

 
   </script>
</body>

</html>