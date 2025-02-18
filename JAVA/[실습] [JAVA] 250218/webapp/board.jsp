<%@ page import="org.example.boardproject.vo.MemberVO" %>
<%@ page import="org.example.boardproject.vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>게시판</title>
</head>
<body>

<%
    MemberVO member = (MemberVO) request.getAttribute("member");
    List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("board");
%>

<div>
    <h1>게시판</h1>
    <h2><%= member.getName() %>님 환영합니다.</h2>
</div>

<a href="createBoard">글 작성하기</a>

<table>
    <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>
    <% if (boardList != null && !boardList.isEmpty()) { %>
    <% for (BoardVO board : boardList) { %>
    <tr>
        <td><%= board.getBoard_id() %></td>
        <td><a href="board?id=<%= board.getBoard_id() %>"><%= board.getTitle() %></a></td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getCreated_at() %></td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="4">등록된 게시글이 없습니다.</td>
    </tr>
    <% } %>
</table>

</body>
</html>
