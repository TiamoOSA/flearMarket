<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
	<title> SringBoard</title>
	<script type="text/javascript"> 
		function doLogin() {
			if(frm.j_username.value == "") {
				alert("Insert the ID.");
				return;
			}
			if(frm.j_password.value == "") {
				alert("Insert the PW.");
				return;
			}
			frm.submit();
		}
	</script>	
</head>

<body>
<div id="login_logo">
<p><h1>
FlearMarket  Test</h1>
</p>
</div>
<div id="login">

	<form name="frm" action="/flearmarket/j_spring_security_check" method="post">
	<table>
	<tr>
		<td> I&nbsp;&nbsp;D  :</td>
		<td> <input type="text" name="j_username"> </td>
	</tr>
	<tr>
		<td> P&nbsp;W :</td>
		<td> <input type="password"  name="j_password"> </td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" class="btn"  value="Sign in" onclick="doLogin()"></td>
	</tr>

	</table>
 	<input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</div>

<div id="memberjoin">
	<a href="#this" class="btn" id="memberjoin">Sign up for flearmarket</a>
</div>

<div id="registerpage">
</div>

<%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#memberjoin").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openMemberJoinPage();
            });
        });
         
        function fn_openMemberJoinPage(){
        	
        	$.ajax({
		        type: "POST",
		        url: "/flearmarket/login/openmemberJoin.do",
		        success: function(response) {
		            $("#registerpage").html( response );
		        }
		 	   });
        }
         
    </script>
    
</body>

 </html>
