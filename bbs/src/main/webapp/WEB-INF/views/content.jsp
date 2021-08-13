<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 읽기</title>
<!--    jsp의 내장객체 -->
<!--    1. ServletContext application -->
<!--    2. HttpSession session -->
<!--    3. HttpServlerRequest reqeust -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	글번호 : ${article.articleNum}
	<br> 아이디 : ${article.id}
	<br> 제목 :${article.title}
	<br> 본문 : ${article.content}
	<br> 날짜 : ${article.writeDate}
	<br>
	<br>
	<hr />
	<br>

	<div>
		<ul>
			<c:forEach var="file" items="${fileList}">
				<li><a
					href="/fullstack/download.bbs?savedFileName=${file.savedFileName}">${file.originalFileName }</a></li>
			</c:forEach>
		</ul>
	</div>

	<c:if test="${id !=null}">
		<c:if test="${id ==article.id}">
			<input type="button" value="수정하기"
				onclick="document.location.href='/fullstack/update.bbs?articleNum=${article.articleNum}'">
			<input type="button" value="삭제하기"
				onclick="document.location.href='/fullstack/delete.bbs?articleNum=${article.articleNum}'">
		</c:if>
		<c:if test="${id !=article.id}">
			<input type="button" value="수정하기" disabled="disabled">
			<input type="button" value="삭제하기" disabled="disabled">
		</c:if>
		<input type="button" value="목록으로"
			onclick="document.location.href='/fullstack/list.bbs'">
	</c:if>

	<c:if test="${id ==null}">
		<input type="button" value="수정하기" disabled="disabled">
		<input type="button" value="삭제하기" disabled="disabled">
		<input type="button" value="목록으로"
			onclick="document.location.href='/fullstack/list.bbs'">
	</c:if>
	<br>
	<br>
	<div>
		<textarea rows="5" cols="70" id="commentContent"></textarea>
		<br>

		<c:if test="${id ==null}">
			<input type="button" value="comment 작성" id="disablede">
		</c:if>
		<c:if test="${id !=null}">
			<input type="button" value="comment 작성" id="commentWrite">
		</c:if>
		<input type="button" value="comment 읽기(${commentCount})"
			onclick="getComment(1,event)" id="commentRead">
	</div>
	<br>
	<div id="showComment"></div>

	<!-- 	Jquery 태그 선택방법 -->
	<!-- 	1.$("#human"): 태그의 id 값이 human인 태그를 선택함 -->
	<!-- 	2.$(".human"): 태그의 class 값이 human인 태그를 선택함 -->
	<!-- 	3.$("human"): human인 태그를 선택함 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#commentWrite").on("click", function() {
				$.ajax({
					url : "/fullstack/commentWrite.comment",
					type : "POST",
					async : true,
					dataType : "json",
//					data{}에서는 EL을 ""로 감싸야함.. 그외에는 그냥사용
					data : {
						articleNum : "${article.articleNum}",
						commentContent : $("#commentContent").val()
					},
					beforeSend : function(){
						alert("시작전");
					},
					complete: function(){
						alert("완료후");
					},
					success:function(data){
						showHtml(data,1);
					},
					error :  function(xhr){
						alert("error html = " + xhr.statusText);
					}
				});
			})

		});
		
		function getComment(commPageNum, event){
	 	 	event.preventDefault();
	 	 	event.stopPropagation();
			$.ajax({							
				url:"/fullstack/commentRead.comment",	
				data:{
					articleNum:"${article.articleNum}",
//		 			숫자와 문자연산에서 + 를 제외하고는 숫자 우선
					commentRow:commPageNum*10
				},
				success:function(data){				
					showHtml(data,commPageNum);
				}
			}); 
		}
		
		
		
		function showHtml(data,pageNum){
			let html="<table border='1' width='500' align='center'>";
			$.each(data,function(index,item){
				html +="<tr>";
				html +="<td>"+(index+1)+"</td>";
				html +="<td>"+item.id+"</td>";
				html +="<td>"+item.commentContent+"</td>";
				html +="<td>"+item.commentDate+"</td>";
				html +="<tr>";
			});
			html +="</table>";
			

			if("${commentCount}">pageNum*10){
				nextPageNum=pageNum+1;
				html +="<br /><input type='button' onclick='getComment(nextPageNum,event)' value='다음comment보기'><br>";
			}
			
			$("#showComment").html(html);
//			$("#showComment").text(html);
			$("#commentContent").val("");
			$("#commentContent").focus();
		}
	</script>







</body>
</html>






