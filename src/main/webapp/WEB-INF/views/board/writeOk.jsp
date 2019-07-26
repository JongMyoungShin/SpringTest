<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<c:set var="root" value="${pageContext.request.contextPath}"/>
</head>
<body>
	<c:if test="${check > 0 }">
		<script type="text/javascript">
			alert("글쓰기를 성공하셨습니다.");
			location.href="${root}/board/write.do";
		</script>
	</c:if>
	
	<c:if test="${check == 0 }">
		<script type="text/javascript">
			alert("글쓰기를 실패 하셨습니다.");
			location.href="${root}/board/write.do";
		</script>
	</c:if>
</body>
</html>





