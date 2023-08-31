<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.test.test3.model.MemberDAO" %>
<%@ page import="com.test.test3.model.VoteDTO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String v_jumin = request.getParameter("v_jumin");
		String v_name = request.getParameter("v_name");
		String m_no = request.getParameter("m_no");
		String v_time = request.getParameter("v_time");
		String v_area = request.getParameter("v_area");
		String v_confirm = request.getParameter("v_confirm");
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.saveVote(v_jumin, v_name, m_no, v_time, v_area, v_confirm);
		System.out.println("투표확인 : "+result);
		if (result>0){
		%>
			<script>
				alert("투표 완료");
				location.href= "index.jsp";
			</script>
		<% 
		} else{
		%>
			<script>
				alert("투표 실패")
			</script>
		<%
		}
		%>
		
</body>
</html>