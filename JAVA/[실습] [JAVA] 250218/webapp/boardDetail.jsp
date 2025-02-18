<%@ page import="org.example.boardproject.vo.BoardVO" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>게시글 상세보기</title>
</head>
<body>

<%
    BoardVO board = (BoardVO) request.getAttribute("boardDetail");
%>

<div>
    <h1><%= board.getTitle() %></h1>

    <div>
        <p>작성자: <%= board.getWriter() %></p>
        <p>작성일: <%= board.getCreated_at() %></p>
    </div>

    <div>
        <p><%= board.getContent() %></p>
    </div>
</div>

</body>
</html>
