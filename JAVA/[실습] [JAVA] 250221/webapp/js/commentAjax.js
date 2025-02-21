const createCommentByPostId = (postId) => {
    let content = $("#commentContext").val().trim();

    if (!content) {
        alert("댓글 내용을 입력해주세요.");
        return;
    }

    $.ajax({
        async: true,
        url: "http://localhost:8080/PostProject/commentCreate",
        type: "POST",
        data: {
            postId: postId,
            content: content,
        },
        success: (data) => {
            location.reload();
        },
        error: () => {
            alert("createCommentByPostId 호출이 실패했습니다.");
        }
    })
}

const deleteCommentByUserId = (commentId) => {
    $.ajax({
        async: true,
        url: "http://localhost:8080/PostProject/commentDelete",
        type: "POST",
        data: {
            commentId: commentId,
        },
        success: (data) => {
            location.reload();
        },
        error: () => {
            alert("deleteCommentByUserId 호출이 실패했습니다.");
        }
    })
}