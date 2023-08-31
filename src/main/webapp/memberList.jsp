<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.test.test3.model.MemberDAO" %>
<%@ page import="com.test.test3.model.MemberDTO" %>

<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        * {
            padding: 0;
            margin: 0;
        }
        #section {
            padding: 20px;
            background-color: burlywood;
            text-align: center;
        }
        table {
            margin-left: auto;
            margin-right: auto;
        }
        table, tr, th, td {
            border: 1px black solid;
            padding: 5px;
            text-align: center;
            border-collapse : collapse;
        }
    </style>
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="nav.jsp"%>
	<div id="section">
		
		<h2>후보조회</h2><br>
		<table>
			<tr>
				<th>후보번호</th>
				<th>성명</th>
				<th>소속정당</th>
				<th>학력</th>
				<th>주민번호</th>
				<th>지역구</th>
				<th>대표전화</th>
			</tr>
			<%
				request.setCharacterEncoding("utf-8");
				MemberDAO memberDAO = new MemberDAO();
				List<MemberDTO> mList = memberDAO.memberList();
				System.out.println(mList);
				for (MemberDTO m : mList){
			%>
			<tr>
				<td><%=m.getM_no() %></td>
				<td><%=m.getM_name() %></td>
				<td><%=m.getP_name() %></td>
				<td><%=m.getP_school() %></td>
				<td><%=m.getM_jumin() %></td>
				<td><%=m.getM_city() %></td>
				<td><%=m.getP_tel() %></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>