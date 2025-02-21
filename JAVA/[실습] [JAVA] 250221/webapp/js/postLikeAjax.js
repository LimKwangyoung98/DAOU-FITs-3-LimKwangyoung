const fetchIsLike = (userId, postId) => {
    $.ajax({
        async: true,
        url: "http://localhost:8080/PostProject/like",
        type: "GET",
        data: {
            userId: userId,
            postId: postId
        },
        success: (data) => {
            let isLike = data.isLike;
            if (isLike === true) {
                $("#likeBtn").val("❤️")
            } else {
                $("#likeBtn").val("🩶")
            }
        },
        error: () => {
            alert("fetchIsLike 호출이 실패했습니다.");
        }
    })
}

const toggleLikeBtn = (userId, postId) => {
    let likeRequest;
    if ($("#likeBtn").val() == "❤️") {
        likeRequest = "false";
    } else {
        likeRequest = "true";
    }

    $.ajax({
        async: true,
        url: "http://localhost:8080/PostProject/like",
        type: "POST",
        data: {
            postId: postId,
            userId: userId,
            likeRequest: likeRequest
        },
        success: (data) => {
            let likeResponse = data.likeResponse;
            let likeCount = data.likeCount;

            if (likeResponse == true) {
                $("#likeBtn").val("❤️")
            } else {
                $("#likeBtn").val("🩶")
            }
            $("#likeCount").text(likeCount)
        },
        error: () => {
            alert("toggleLikeBtn 호출이 실패했습니다.");
        }
    })
}