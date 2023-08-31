<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.test.test3.model.MemberDAO" %>
<%@ page import = "com.test.test3.model.VoteDTO" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표검수조회</title>
<style>
	* {
		padding : 0;
		margin : 0;
	}
	#section {
		text-align : center;
		padding : 20px;
		background-color : burlywood;
	}
	table {

		margin : auto;
		
	}
	table, tr, th, td{
		border : solid 1px black;
		border-collapse : collapse;
		text-align : center;
	
	}
	
</style>
</head>
<body>

<%@ include file="header.jsp" %>
<%@ include file="nav.jsp" %>

<div id="section">
	<h2>투표검수 조회</h2>
	<table>
		<tr>
			<th>성명</th>
			<th>생년월일</th>
			<th>나이</th>
			<th>성별</th>
			<th>후보번호</th>
			<th>투표시간</th>
			<th>유권자확인</th>
		</tr>
		<%
			MemberDAO memberDAO = new MemberDAO();
			List<VoteDTO> mList = memberDAO.voteList();
			for(VoteDTO v : mList){
		%>
		<tr>
			<td><%=v.getV_name() %></td>
			<td><%=v.getV_birth() %></td>
			<td><%=v.getV_age() %></td>
			<td><%=v.getV_gender() %></td>
			<td><%=v.getM_no() %></td>
			<td><%=v.getV_time() %></td>
			<td><%=v.getV_confirm() %></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>