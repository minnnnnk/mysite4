<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

	
	
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="guestbook">
						<table id="guestDelete">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password" value=""></td>
								<td class="text-left"><button type="submit" id="btnDelete">삭제</button></td>
								<td><a href="${pageContext.request.contextPath}/api/guestbook/addList">[메인으로 돌아가기]</a></td>
							</tr>
						</table>
						<input type ="hidden" name="no" value="${gVo.no}">
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
$("#btnDelete").on("click",function(){
	
	var password = $("[name='password']").val();
	var no = $("[name='no']").val();
	
	console.log(password);
	console.log(no);
	
	var GuestBookVo = {
		password:password,
		no:no
	}
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/delete",		
		type : "post",
		//contentType : "application/json",
		data : GuestBookVo,

		dataType : "json",
		success : function(gVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(gVo);
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});

</script>
</html>
