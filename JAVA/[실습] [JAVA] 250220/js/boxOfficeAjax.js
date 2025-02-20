$(document).ready(() => {
    let yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    let targetDate = yesterday.toISOString().split("T")[0];
    $("#targetDate").val(targetDate);

    fetchMovieData();

    $("#targetDate").on("change", () => {
        fetchMovieData();
    });
});

fetchMovieData = () => {
    $("tbody").empty();

    let targetDate = $("#targetDate").val().replace(/-/g, "");

    $.ajax({
        async: true,
        url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type: "GET",
        data: {
            key: "77cf700778ce164936dd6939079db46e",
            targetDt: targetDate
        },
        success: (data) => {
            let movies = data.boxOfficeResult.dailyBoxOfficeList;

            movies.forEach(movie => {
                trTag = $("<tr></tr>");

                let rank = $("<td></td>").text(movie.rank);
                let img = $("<td></td>");
                let title = $("<td></td>").text(movie.movieNm);
                let acc = $("<td></td>").text(Number(movie.audiAcc).toLocaleString());
                let date = $("<td></td>").text(movie.movieCd.slice(0, 4) + "-" + movie.movieCd.slice(4, 6) + "-" + movie.movieCd.slice(6, 8));

                $.ajax({
                    async: true,
                    url: "https://dapi.kakao.com/v2/search/image",
                    type: "GET",
                    headers: {
                        Authorization: "KakaoAK a30fc4f9946db3d0e7a86a27a729caae"
                    },
                    data: {
                        query: movie.movieNm
                    },
                    success: (data) => {
                        img.append($("<img />").attr("src", data.documents[0].image_url).attr("width", "100px").attr("height", "100px"));

                    },
                    error: () => {
                        alert("영화 포스터 호출이 실패했습니다.")
                    }
                })

                let deleteBtn = $("<td></td>").append($("<input>").attr("type", "button").attr("value", "삭제"));
                deleteBtn.click(function() {
                    $(this).parent().remove();
                })

                trTag.append(rank)
                trTag.append(img)
                trTag.append(title)
                trTag.append(acc)
                trTag.append(date)
                trTag.append(deleteBtn)

                $("tbody").append(trTag);
            })
        },
        error: () => {
            alert("영화 데이터 호출이 실패했습니다.");
        }
    })
}