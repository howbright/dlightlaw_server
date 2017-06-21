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
<form id="usrform" action="/hist" method="post" >
  <label class='flabel'>검색어:</label><input id="search" type="text" value="" name="searchKey" >
  <button class='btn' id="btn3" value="검색" type="button" onclick="bindData()">검색</button><br>
  <p id="banInfoTxt"></p>
</form>


</div>
<div id="container" style="height:600">
  <table id="jqGrid"></table>
  <div id="jqGridPager"></div>
  <button class='btn' id="btn4" value="검색" type="button" onclick="free()">용서해주기</button>
</div>
 

    <script type="text/javascript"> 
       var free = function(){
         $.ajax({
                   type : "POST",
                   url: "/free",
                   data: { 
                    "nickname": $("#search").val()
                     },
                   dataType: "json",
                   success: function(jData){

                         baninfo();
                        
                         $("#jqGrid").jqGrid('setGridParam', { 
                              postData: {"nickname":$("#search").val(),
                                        "page" :1, "rowNum" : 100 }
                              }).trigger('reloadGrid'); 
                   }
               });
       }

       var baninfo = function() {
           $.ajax({
                   type : "POST",
                   url: "/ban",
                   data: { 
                    "nickname": $("#search").val()
                     },
                   dataType: "json",
                   success: function(jData){
                         $("#banInfoTxt").text(jData['description']);
                   }
               });
       }
       

     var bindData = function() {
       baninfo();
       $("#jqGrid").jqGrid('setGridParam', { 
            postData: {"nickname":$("#search").val(),
                      "page" :1, "rowNum" : 100 }
        }).trigger('reloadGrid'); 
            
        }
//, formatoptions: { srcformat: 'u', newformat: "y-m-d H:i:s" }
       $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: "/hist",
                mtype: "POST",
                postData : { nickname:$("#search").val(), rowNum: 100},
                datatype: "json",
                id : "roomId",
                colModel: [
                    { label: '닉네임', name: 'nickname', key: true, width: 20 },
                    { label: '방이름', name: 'roomname', key: true, width: 55 },
                    { label: '방만든시간', name: 'description', key: true, width: 55 }
                       
                ],
                viewrecords: true,
                multiselect:true,
                width: $("#container").width(),
                height: $("#container").height()-150,
                rowNum: 100,
                pager: "#jqGridPager"
            });
        });

 
   </script>
</body>

</html>