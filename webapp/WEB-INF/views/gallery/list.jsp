<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/includes/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
					
						<c:if test="${authUser.no != null}"><button id="btnImgUpload">이미지올리기</button></c:if>
						<div class="clear"></div>

			
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${gVo}" var="gList" varStatus="i">
							<li id="v${gList.no}">
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${gList.saveName}">
									<div class="imgWriter">작성자: <strong>${gList.name}</strong></div>
									<input type="hidden" id="viewNo" name="no" value="${gList.no}">
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath }/gallery/save"  enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
							<input type="hidden" name="userNo" value="${authUser.no}">
							
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
						<input type="hidden" id="viewNum" name="no" value="">
					</div>
					
				</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
				
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">

//업로드 버튼
$("#btnImgUpload").on("click",function(){
	console.log("등록 모달창 클릭");
	
	$("#addModal").modal("show");
})


//사진 클릭했을때
$(".view").on("click",".imgItem",function(){
	console.log("뷰 모달창 클릭");
	
	
	//클릭한 친구 불러오기
	var $this = $(this);
	console.log($this);
	
	//이미지 불러와서
	var img = $this.attr("src");
	var Path = img.split("/");
	
	//배열에 찾아서 saveName으로 보내기
	var saveName = Path[3]
	
	
	$.ajax({
		url : "${pageContext.request.contextPath}/api/gallery/read2",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(saveName),
		dataType : "json",
		success : function(gVo){
			console.log(gVo);
			
			//숫자 담아주기
			$("#viewNum").val(gVo.no);
			
			//세션 넘버 가져오기
			var userNo = "${authUser.no}";
			
			//딜리트버튼 숨기기
			if(userNo != gVo.userNo){
				$("#btnDel").hide();
			}else if(userNo == gVo.userNo) {
				$("#btnDel").show();
			}
			
			//사진 담아주기
			$("#viewModelImg").attr("src", "${pageContext.request.contextPath}/upload/"+gVo.saveName);
			
			//컨텐츠 담아주기
			$("#viewModelContent").text(gVo.content);
			
			//창띄우기
			$("#viewModal").modal("show");	
			
			
		},
		error : function(XHR, status, error) {
		console.error(status + " : " + error);
		}
	});
	
});

$("#btnDel").on("click",function(){
	console.log("딜리트 버튼 클릭");
	
	//no값 가져오기
	var no = $("#viewNum").val();
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/gallery/delete",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(no),

		dataType : "json",
		success : function(no){
			/*성공시 처리해야될 코드 작성*/
			console.log("#v"+no);
			
			//배열삭제
			$("#v"+no).remove();
			
			
			//모달창닫기
			$("#viewModal").modal("hide");	
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
	
})

</script>




</html>

