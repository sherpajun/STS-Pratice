<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 읽기</title>
<!--    jsp의 내장객체 -->
<!--    1. ServletContext application -->
<!--    2. HttpSession session -->
<!--    3. HttpServlerRequest reqeust -->

</head>
<body>
      글번호 : ${article.articleNum}<br>
	  아이디 : ${article.id}<br>	  
	  제목 :${article.title}<br>  
	  본문 : ${article.content}<br>
	  날짜 : ${article.writeDate}<br><br>
	  <hr/><br> 
	  
	  <div>
	  	<ul>
	  	<c:forEach var="file" items="${fileList}">
	  	  <li><a href="/fullstack/download.bbs?savedFileName=${file.savedFileName}">${file.originalFileName }</a></li>
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
		<input type="button" value="목록으로"	onclick="document.location.href='/fullstack/list.bbs'">					
	</c:if>

	<c:if test="${id ==null}">		
		<input type="button" value="수정하기" disabled="disabled">
		<input type="button" value="삭제하기" disabled="disabled">
		<input type="button" value="목록으로"	onclick="document.location.href='/fullstack/list.bbs'">		
	</c:if>	
	<br><br>
	<div>
	<textarea rows="5" cols="70" id="commentContent"></textarea><br>
	
	<c:if test="${id ==null}">
	<input type="button" value="comment 작성" id="disablede">
	</c:if>
	<c:if test="${id !=null}">
	<input type="button" value="comment 작성" id="commentWrite">
	</c:if>
	<input type="button" value="comment 읽기(${commentCount})"
	onclick="getComment(1,event)" id="commentRead">
	</div><br>
</body>
</html>






