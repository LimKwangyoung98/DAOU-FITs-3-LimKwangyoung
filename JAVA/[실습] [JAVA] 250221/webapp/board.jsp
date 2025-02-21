<%@ page import="org.example.postproject.vo.UserVO" %>
<%@ page import="org.example.postproject.vo.PostVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            background-color: #f5f7fa;
            font-family: "Arial", sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }
        .container {
            width: 900px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 22px;
            color: #333;
            margin-bottom: 15px;
        }
        .search-form {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .search-form select,
        .search-form input[type="text"],
        .search-form input[type="submit"] {
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .search-form input[type="text"] {
            width: 60%;
        }
        .search-form input[type="submit"] {
            background: #0078d7;
            color: white;
            border: none;
            cursor: pointer;
        }
        .search-form input[type="submit"]:hover {
            background: #005fa3;
        }
        .post-list {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        .post-list th, .post-list td {
            border-bottom: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        .post-list th {
            background: #0078d7;
            color: white;
        }
        .post-list tr:hover {
            background: #f1f1f1;
        }
        .new-post-btn {
            display: block;
            width: 100%;
            background: #28a745;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
        }
        .new-post-btn:hover {
            background: #218838;
        }
    </style>
</head>
<body>

<%
    UserVO user = (UserVO) request.getAttribute("user");
    List<PostVO> posts = (List<PostVO>) request.getAttribute("posts");
%>

<div class="container">
    <h1><%= user.getName() %>님 환영합니다.</h1>

    <form action="posts" method="POST" class="search-form">
        <select name="searchType">
            <option selected>제목</option>
            <option>내용</option>
            <option>제목+내용</option>
        </select>
        <input type="text" name="keyword" placeholder="검색어 입력">
        <input type="submit" value="검색">
    </form>

    <table class="post-list">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>좋아요</th>
            <th>댓글 수</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody>
        <% int index = posts.size(); %>
        <% for (PostVO post : posts) { %>
        <tr>
            <td><%= index-- %></td>
            <td style="text-align: left;">
                <a href="detail?postId=<%= post.getPostId() %>"><%= post.getTitle() %></a>
            </td>
            <td><%= post.getName() %></td>
            <td><%= post.getCreatedAt() %></td>
            <td><%= post.getLikeCount() %></td>
            <td><%= post.getCommentCount() %></td>
            <td><%= post.getViewCount() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <a href="postCreate.html" class="new-post-btn">새 글 작성</a>
</div>

<script>
    $(document).ready(() => {
        if (!sessionStorage.getItem("pageReloaded")) {
            sessionStorage.setItem("pageReloaded", "true");
            location.reload();
        }
    });
</script>


</body>
</html>
