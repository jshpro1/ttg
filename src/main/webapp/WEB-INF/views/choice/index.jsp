<%-- 
    Document   : choice
    Created on : 2024. 4. 2., 오후 7:26:55
    Author     : jshpr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>보드게임 선택</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my_style.css">
    </head>
    <body>
        <%@ include file ="/WEB-INF/jspf/header.jspf"%>
        <div class='center'>
            <!-- 보드게임 장르 선택 콤보박스 -->
            <select id="genreSelect">
                <option value="all">전체</option>
                <option value="strategy">전략성 vs 확률성</option>
                <option value="content">내용물별 분류</option>
                <option value="player">플레이어 기준 분류</option>
                <option value="mechanism">보드게임 메카닉 분류</option>
            </select>
            <!-- 보드게임 세부 분류 선택 콤보박스 -->
            <select id="subCategorySelect" style="display: none;">
                <!-- 이 부분은 JavaScript로 동적으로 변경될 것입니다 -->
            </select>
            <input type="text" name="keyword" placeholder="보드게임 이름을 입력하세요">
            <button id="searchButton">검색</button>
        </div>

        <ul>
            <a href="choice/game/yatch"><img src="${pageContext.request.contextPath}/images/yatchdice.png" alt="yatch" width="300" height="200" border="0"></a>
        </ul>

        <%@ include file ="/WEB-INF/jspf/footer.jspf"%> 
    </body>

    <script>
        // 장르 선택 콤보박스 변경 시
        document.getElementById('genreSelect').addEventListener('change', function (event) {
            var genre = event.target.value;
            var subCategories = [];
            if (genre === 'strategy') {
                subCategories = ['추상전략게임', '유로게임', '워게임', '테마 게임', '사회적 추론 게임'];
            } else if (genre === 'content') {
                subCategories = ['에픽 게임', '몬스터 게임', '마이크로 게임', '잡지 게임'];
            } else if (genre === 'player') {
                subCategories = ['전문가용 게임', '가족 게임', '어린이 게임', '파티 게임'];
            } else if (genre === 'mechanism') {
                subCategories = ['전략', '추상', '테마 게임', '워게임', '커스터마이징 게임', '가족 게임', '파티 게임', '어린이 게임'];
            } else {
                subCategories = []; // 전체 선택 시 세부 분류 없음
            }

            // 세부 분류 콤보박스 업데이트
            var subCategorySelect = document.getElementById('subCategorySelect');
            subCategorySelect.innerHTML = '';
            subCategories.forEach(function (subCategory) {
                var option = document.createElement('option');
                option.value = subCategory;
                option.textContent = subCategory;
                subCategorySelect.appendChild(option);
            });

            // 선택한 장르에 따라 세부 분류 콤보박스 표시 여부 결정
            if (genre === 'all') {
                subCategorySelect.style.display = 'none'; // 전체 선택 시 세부 분류 콤보박스 숨김
            } else {
                subCategorySelect.style.display = 'inline-block';
            }
        });

        // 검색 버튼 클릭 시
        document.getElementById('searchButton').addEventListener('click', function (event) {
            event.preventDefault();
            // 선택한 장르와 세부 분류 가져오기
            var genre = document.getElementById('genreSelect').value;
            var subCategory = document.getElementById('subCategorySelect').value;
            var keyword = document.querySelector('input[name="keyword"]').value;
            // 선택한 장르와 세부 분류에 따른 검색 결과 표시
            fetch('searchBoardGame.jsp?genre=' + genre + '&subCategory=' + subCategory + '&keyword=' + keyword)
                    .then(response => response.text())
                    .then(data => {
                        document.getElementById('searchResults').innerHTML = data;
                    })
                    .catch(error => {
                        console.error('Error fetching search results:', error);
                    });
        });
    </script>
</html>

