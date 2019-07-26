<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>목록보기</title>
<link rel="stylesheet" type="text/css" href="${root}/resources/css/board/style.css" />
</head>
<body>
	<div align="center">
		<table>
			<tr>
				<td width="530" align="right" bgcolor="D1DBDB">
					<a href="">글쓰기</a>
				</td>
			</tr>
		</table>
		
		<c:if test="${count==0 || boardList.size()==0}">
			<table>
				<tr>
					<td width="530" align="left" bgcolor="D1DBDB">
						<a href="">게시판에 저장된 글이 없습니다.</a>
					</td>
				</tr>
			</table>
		</c:if>
		
		<c:if test="${count > 0}">
			<table border="1">
				<tr>
					<td align="center" width="30">번호</td>
					<td align="center" width="250">제목</td>
					<td align="center" width="70">작성자</td>
					<td align="center" width="150">작성일</td>
					<td align="center" width="50">조회수</td>
				</tr>
				
				<c:forEach var="boardDto" items="${boardList}">
					<tr>
						<td>${boardDto.boardNumber}</td>
						<td>
							<c:if test="${boardDto.sequenceLevel > 0}">
								<c:forEach begin="0" end="${boardDto.sequenceLevel}">
									&nbsp;
								</c:forEach>
								[답글]
							</c:if>
							
							<a href="${root}/board/read.do?boardNumber=${boardDto.boardNumber}&pageNumber=${currentPage}">
								${boardDto.subject}
							</a>
						</td>
						<td>${boardDto.writer}</td>
						<td>
							<fmt:formatDate value="${boardDto.writeDate}" 
							  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>${boardDto.readCount}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div><br/><br/>
	
	<div align="center">
		<c:if test="${count > 0}">
			<c:set var="pageBlock" value="${10}"/>
			 <fmt:parseNumber var="pageCount"  integerOnly="true"
				 value="${count/boardSize+(count%boardSize==0 ? 0:1)}"/>
				 
			<%--
				int startPage=(int)((currentPage-1)/pageBlock)*pageBlock+1;
				                             (3-1)=2/10=0*10=0+1=1
				int endPage=startPage+pageBlock-1;
				                           1+10-1=10
			 --%>	 
			 
			 <fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}"
			    integerOnly="true"/>
			 <c:set var="startPage" value="${result * pageBlock+1 }"/>
			 <c:set var="endPage" value="${startPage+pageBlock-1}"/>
			 
			 <c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}"/>
			</c:if>	
			
			<c:if test="${startPage > pageBlock}">
				<a href="${root}/board/list.do?pageNumber=${startPage-pageBlock}">
					[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="${root}/board/list.do?pageNumber=${i}">[${i}]</a>
			</c:forEach>
			
			<c:if test="${endPage < pageCount}">
				<a href="${root}/board/list.do?pageNumber=${startPage+pageBlock}">
					[다음]</a>
			</c:if>
		</c:if>
	</div>
</body>
</html>








