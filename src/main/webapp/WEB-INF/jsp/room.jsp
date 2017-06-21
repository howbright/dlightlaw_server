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
<form id="usrform" action="/room-search" method="post" >
  <label class='flabel'>검색어:</label><input id="search" type="text" value="" name="searchKey" >
  <button class='btn' id="btn3" value="검색" type="button" onclick="bindData()">검색</button><br>
 
</form>


</div>
<div id="container" style="height:600">
  <table id="jqGrid"></table>
  <div id="jqGridPager"></div>
  <button class='btn' id="btn4" value="검색" type="button" onclick="ban()">방 만든 사람 정지시키기</button>
  <button class='btn' id="btn5" value="검색" type="button" onclick="check()">Just 확인상태로 변경</button>
</div>
 

    <script type="text/javascript"> 
       var ban = function(){
         $.ajax({
                   type : "POST",
                   url: "/ban-room-maker",
                   data: { 
                    "roomIdList": jQuery("#jqGrid").jqGrid('getGridParam','selarrrow')
                     },
                   dataType: "json",
                   success: function(jData){
                        
                         $("#jqGrid").jqGrid('setGridParam', { 
                              postData: {"searchKey":$("#search").val(),
                                        "page" :1, "rowNum" : 100 }
                              }).trigger('reloadGrid'); 
                   }
               });
       }
       var check = function(){
         $.ajax({
                   type : "POST",
                   url: "/check-room",
                   data: { 
                    "roomIdList": jQuery("#jqGrid").jqGrid('getGridParam','selarrrow')
                     },
                   dataType: "json",
                   success: function(jData){
                        
                         $("#jqGrid").jqGrid('setGridParam', { 
                              postData: {"searchKey":$("#search").val(),
                                        "page" :1, "rowNum" : 100 }
                              }).trigger('reloadGrid'); 
                   }
               });
       }

     var bindData = function() {
       $("#jqGrid").jqGrid('setGridParam', { 
            postData: {"searchKey":$("#search").val(),
                      "page" :1, "rowNum" : 100 }
        }).trigger('reloadGrid'); 
            
        }
//, formatoptions: { srcformat: 'u', newformat: "y-m-d H:i:s" }
       $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: "/room-search",
                mtype: "POST",
                postData : { searchKey:$("#search").val(), rowNum: 100},
                datatype: "json",
                id : "roomId",
                colModel: [
                    { label: '방이름', name: 'roomName', key: true, width: 75 },
                    { label: '방 만든사람 id', name: 'makeUserId', key: true, width: 55 },
                    { label: '방 만든사람', name: 'nickname', key: true, width: 55 },
                    { label: 'roomType', name: 'roomType', key: true, width: 45 },
                    { label: 'channelId', name: 'channelId', key: true, width: 45 },
                    { label: '만든시간', name: 'roomCreateTime', key: true, width: 75 },
                     { label: '방 id', name: 'roomId', key: true, width: 55 }
                       
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