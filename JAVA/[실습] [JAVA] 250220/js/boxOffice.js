myFunc = () => {
    // jQuery로 AJAX 호출
    // API 파라미터 객체를 JavaScript로 생성한다.
    $.ajax({
        async: true,  // 비동기 방식(기본값)
        url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type: "GET",
        data: {
            key: "77cf700778ce164936dd6939079db46e",
            targetDt: "20250218"
        },
        dataType: "json",  // 기본값
        success: (data) => {
            // JSON 문자열을 객체로 변환시킨 객체가 data 변수에 매핑된다.
            alert("호출이 성공했습니다.");
            $("h1").text(data.boxOfficeResult.dailyBoxOfficeList[0].movieNm);
        },
        error: () => {
            alert("호출이 실패했습니다.");
        }
    })
}