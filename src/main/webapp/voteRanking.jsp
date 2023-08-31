<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.test.test3.model.MemberDAO" %>
<%@ page import="com.test.test3.model.VoteRankingDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "header.jsp" %>
<%@ include file = "nav.jsp" %>
<div id="section">
	<h2>후보자등수</h2><br>
	
	<table>
		<tr>
			<th>후보번호</th>
			<th>성명</th>
			<th>총투표건수</th>
			
		</tr>
		<%
			MemberDAO memberDAO = new MemberDAO();
			List<VoteRankingDTO> rakingDTO= memberDAO.voteRanking();
			for (VoteRankingDTO raking : rakingDTO){
		%>
		<tr>
			<td><%=raking.getM_no() %>
			<td><%=raking.getM_name() %>
			<td><%=raking.getVoteCount() %>
		</tr>
		<%
			}
		%>
	</table>
	
</div>
<%@ include file = "footer.jsp" %>
</body>
</html>