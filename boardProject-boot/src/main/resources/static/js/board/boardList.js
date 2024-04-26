/* 글쓰기 버튼 클릭 시 */
const insertBtn = document.getElementById("insertBtn");

// 글 쓰기 버튼이 존재시 (로그인 상태인 경우)
if(insertBtn != null) {
    insertBtn.addEventListener("click", () => {

        // get 방식 요청
        // /editBoard/1/insert
        location.href = `/editBoard/${boardCode}/insert`;
    });
}