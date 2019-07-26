<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<meta charset="UTF-8">
<title>게시판 읽기</title>
<link rel="stylesheet" type="text/css" href="${root}/resources/css/board/style.css" />
<script type="text/javascript">
	function replyFun(boardNumber, groupNumber, sequenceNumber, 
			sequenceLevel, root, pageNumber){
		
		var url=root+ "/board/write.do?boardNumber=" + boardNumber;
		url += "&groupNumber=" + groupNumber;
		url += "&sequenceNumber=" + sequenceNumber;
		url += "&sequenceLevel=" + sequenceLevel;
		url += "&pageNumber=" + pageNumber;
		// alert(url);
		
		location.href=url;
	}
	
	function upFun(root, boardNumber, pageNumber){
		var url=root+ "/board/update.do?boardNumber=" + boardNumber;
		url += "&pageNumber=" + pageNumber;
		
		alert(url);
	}
</script>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<td align="center" height="20" width="125">글번호</td>
				<td align="center" height="20" width="125">${boardDto.boardNumber}</td>
			
				<td align="center" height="20" width="125">조회수</td>
				<td align="center" height="20" width="125">${boardDto.readCount}</td>
			</tr>
			
			<tr>
				<td align="center" height="20" width="125">작성자</td>
				<td align="center" height="20" width="125">${boardDto.writer}</td>
			
				<td align="center" height="20" width="125">작성일</td>
				<td align="center" height="20" width="125">
					<fmt:formatDate value="${boardDto.writeDate}" 
								  pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			
			<tr>
				<td align="center" height="300" width="125">글내용</td>
				<td valign="top" height="300" colspan="3">
					${boardDto.content}
				</td>
			</tr>
			
			<tr>
				<td align="center" height="30" colspan="4">
					<input type="button" value="글수정" 
							onclick="upFun('${root}', '${boardDto.boardNumber}', 
							                       '${pageNumber}')"/>
					<input type="button" value="글삭제" onclick=""/>
					<input type="button" value="답글" 
					onclick="replyFun('${boardDto.boardNumber}', '${boardDto.groupNumber}', 
					                          '${boardDto.sequenceNumber}', '${boardDto.sequenceLevel}', 
					                          '${root}', '${pageNumber}')"/>
					
					<input type="button" value="글목록" 
						onclick="location.href='${root}/board/list.do?pageNumber=${pageNumber}'"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>







