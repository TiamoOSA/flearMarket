<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<!-- Include-header.jspf. common include file. -->
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
<div id="registerx">
 <a href="#this" class="btn" id="list" >X</a>
</div>
<div id="registerform">
    <form id="frm">
    <H1>Member Join page</H1>
        <table class="memberjoin_view">
            <colgroup>
                <col width="30%">
                <col width="*"/>
            </colgroup>
            <tbody>
                <tr>
                    <th scope="row">I D : </th>
                    <td><input type="text" id="ID" name="ID"></input></td>
                </tr>
                <tr>
                	<th scope="row">P 	W : </th>
                    <td>
                       <input type="password" id="PASSWORD" name="PASSWORD"></input>
                    </td>
                </tr>
                 <tr>
                    <th scope="row">Name :</th>
                    <td><input type="text" id="NAME" name="NAME"></input></td>
                </tr>
                <tr>
                	<th scope="row">BirthDay : </th>
                    <td>
                       <input type="text" id="BIRTHDAY" name="BIRTHDAY"></input>
                    </td>
                </tr>
                
                <tr>
                    <th scope="row">E-mail address</th>
                    <td><input type="text" id="EMAIL" name="EMAIL"></input></td>
                </tr>               
            </tbody>
        </table>
        <a href="#this" class="btn" id="write" >REGISTER</a>
    </form>
</div> 
    <!-- Include-body.jspf. common include file  -->
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
	$(document).ready(function(){
		$("#list").on("click", function(e){ 	// 1. Goto List function
			e.preventDefault();
			fn_openLoginPage();
		});
		
		$("#write").on("click", function(e){	// 2. Write Content function
			e.preventDefault();
			fn_registerMember();
		});	
	});

	
	function fn_openLoginPage(){
		var comSubmit = new ComSubmit();	// java script object in common.js 
		comSubmit.setUrl("<c:url value ='/login/login.do' />");
		comSubmit.submit();
		
	}
	
	function fn_registerMember(){
		var comSubmit = new ComSubmit("frm"); // form id : frm
		comSubmit.setUrl("<c:url value ='/login/insertmemberJoin.do'/>");
		comSubmit.submit();
	}
	/* 아이디 중복여부 확인, 아이디 길이 확인, 비밀번호 길이와 정책 확인, 생일 형태 확인, 이메일 존재여부 확인, 값이 비어 있는지 확인  */

    </script>
</body>
</html>





