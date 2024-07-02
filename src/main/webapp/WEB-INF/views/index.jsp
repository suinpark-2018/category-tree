<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시판 카테고리</title>
</head>

<style>
    body {
        font-family: 'Arial', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
    }
    .wrapper {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
        background-color: #82b4c4;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
    }
    .wrapper h1 {
        font-size: 30px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #343a40;
    }
    .category-item, .subcategory-item {
        padding: 10px;
        margin-bottom: 10px;
        border: 3px solid #dee2e6;
        border-radius: 5px;
        background-color: #f1f3f5;
        font-size: 18px;
        cursor: pointer;
        transition: background-color 0.3s, font-weight 0.3s;
    }
    .category-item:hover, .subcategory-item:hover {
        background-color: #e9ecef;
    }
    .bold {
        font-weight: bold !important;
    }
</style>

<body>
    <div class="wrapper">
        <h1>게시판 카테고리</h1>
        <div id="categoryList"></div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // 페이지 로드 시 함수 실행
    $(document).ready(function() {

        // 첫 페이지에서는 최상위 카테고리인 성별(남자, 여자)만 출력
        const initialCategories = [
            { cno: 1, name: "남자" },
            { cno: 2, name: "여자" }
        ];

        displayCategories(initialCategories, $("#categoryList"), 1);

        // 클릭된 카테고리 함수 실행
        // 서버로 cno, name 데이터 전송
        // 응답 데이터 타입 json 으로 설정
        function displayCategories(categories, parentElement, level) {
            categories.forEach(category => {
                const div = $("<div>")
                    .addClass(`level-${level}`)
                    .addClass(`category-item`)
                    .data("cno", category.cno)
                    .data("name", category.name)
                    .text(category.name)
                    .on("click", function(e) {
                        e.stopPropagation();
                        const cno = $(this).data("cno");
                        const name = $(this).data("name");
                        $(this).addClass('bold');
                        fetchAndDisplaySubcategories(cno, name, $(this), level + 1);
                    });
                parentElement.append(div);
            });
        }

        // 카테고리 리스트, 부모요소, 현재 카테고리
        function fetchAndDisplaySubcategories(cno, name, parentElement, level) {
            $.ajax({
                url: `/category/list`,
                method: "GET",
                data: { cno: cno, name: name },
                dataType: "json",
                // 데이터 검증 유효 시 하위 카테고리 출력 위해 displayCategories 함수 실행
                success: function(data) {
                    if (data) {
                        data.forEach(category => {
                            if (category.children) {
                                displayCategories(category.children, parentElement, level);
                            }
                        });
                    }
                },
                error: function(error) {
                    console.log("Error fetching category:", error);
                }
            });
        }
    });
</script>

</html>