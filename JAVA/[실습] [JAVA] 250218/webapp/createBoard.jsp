<%@ page import="org.example.boardproject.vo.MemberVO" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
  <title>글 작성하기</title>
</head>
<body>

<%
  MemberVO member = (MemberVO) request.getAttribute("member");
%>

<h1>글 작성하기</h1>
<form action="createBoard" method="POST">
  <input type="hidden" name="member_id" value="<%= member.getMember_id() %>">
  <input type="hidden" name="writer" value="<%= member.getName() %>">

  제목&nbsp;<input type="text" name="title">
  내용&nbsp;<input type="text" name="content">
  <br>
  <input type="submit" value="작성하기">
</form>


</body>
</html>
