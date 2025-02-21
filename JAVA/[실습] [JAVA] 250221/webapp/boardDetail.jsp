<%@ page import="org.example.postproject.vo.UserVO" %>
<%@ page import="org.example.postproject.vo.PostVO" %>
<%@ page import="org.example.postproject.vo.CommentVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <script src="js/postLikeAjax.js"></script>
    <script src="js/commentAjax.js"></script>
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
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .container {
            width: 800px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .top-bar {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 15px;
        }
        .back-btn {
            background: #6c757d;
            color: white;
            padding: 8px 12px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .back-btn:hover {
            background: #545b62;
        }
        .title-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #0078d7;
            padding-bottom: 10px;
            margin-bottom: 15px;
        }
        .post-title {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }
        /* 좋아요 버튼과 카운트 간격 조절 */
        .like-container {
            display: flex;
            align-items: center;
            gap: 8px; /* 하트와 좋아요 수 간격 */
        }
        .like-btn {
            background: none;
            border: none;
            font-size: 24px;
            cursor: pointer;
            padding: 5px;
            width: 40px; /* 버튼 크기 조정 */
            height: 40px;
            text-align: center;
        }
        .like-count {
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }
        .post-info {
            display: flex;
            justify-content: space-between;
            font-size: 14px;
            color: #555;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
            margin-bottom: 15px;
        }
        .post-content {
            padding: 15px;
            font-size: 16px;
            color: #333;
            line-height: 1.6;
            border-bottom: 2px solid #ddd;
            margin-bottom: 15px;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            font-size: 14px;
            font-weight: bold;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-top: 10px;
        }
        .btn-edit { background: #0078d7; }
        .btn-edit:hover { background: #005fa3; }
        .btn-delete { background: #d32f2f; }
        .btn-delete:hover { background: #b71c1c; }
    </style>
</head>
<body>

<%
    UserVO user = (UserVO) request.getAttribute("user");
    PostVO post = (PostVO) request.getAttribute("post");
    List<CommentVO> comments = (List<CommentVO>) request.getAttribute("comments");

    boolean isUser = user != null && post.getUserId().equals(user.getUserId());
%>

<div class="container">
    <div class="top-bar">
        <a href="posts" class="back-btn">← 뒤로가기</a>
    </div>

    <div class="title-container">
        <span class="post-title"><%= post.getTitle() %></span>
        <% if (!isUser) { %>
        <div class="like-container">
            <input id="likeBtn" class="like-btn" type="button" value="❤️"
                   onclick="toggleLikeBtn('<%= user.getUserId() %>', <%= post.getPostId() %>)">
            <span id="likeCount" class="like-count"><%= post.getLikeCount() %></span>
        </div>
        <% } %>
    </div>

    <div class="post-info">
        <span>작성자: <%= post.getName() %></span>
        <span>작성일: <%= post.getCreatedAt() %></span>
        <span>조회수: <%= post.getViewCount() %></span>
    </div>

    <div class="post-content">
        <%= post.getContent() %>
    </div>

    <% if (isUser) { %>
    <a href="update?postId=<%= post.getPostId() %>" class="btn btn-edit">수정</a>
    <a href="delete?postId=<%= post.getPostId() %>" class="btn btn-delete">삭제</a>
    <% } %>

</div>

<script>
    $(document).ready(() => fetchIsLike("<%= user.getUserId() %>", <%= post.getPostId() %>));
</script>

</body>
</html>
