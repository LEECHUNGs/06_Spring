// 쿠키에서 key가 일치하는 value를 얻어오기 함수

// 쿠키는 "K=V"; "K=V"; .....

// 배열.map(함수) : 배열의 각 요소를 이용해 함수 수행 후 결과값으로 새로운 배열을 만들어 반환

const getCookie = (key) => {
    const cookies = document.cookie;

    //console.log(cookies);
    // cookies 문자열을 배열 형태로 변경
    const cookieList = cookies.split("; ") // ["K=V", "K=V"]
                    .map(el => el.split("="));

    //console.log(cookieList);

    // 배열 -> 객체로 변환 (그래야 다루기 쉽다)
    const obj = {}; // 비어있는 객체 선언
    
    for(let i = 0; i<cookieList.length; i++) {
        const k = cookieList[i][0];
        const v = cookieList[i][1];

        obj[k] = v; // 객체 추가
    }
    //console.log(obj);

    return obj[key]; // 매개변수로 전달 받은 key와
                     // obj 객체에 저장된 키가 일치하는 요소의 value 리턴
};

const loginEmail = document.querySelector("#loginForm input[name='memberEmail']");

// 로그인 안된 상태인 경우에 수행
if(loginEmail != null){ // 로그인 창에 이메일 입력 부분이 화면에 있을 때

    // 쿠키 중 key 값이 "saveId"인 요소의 value 얻어오기
    const saveId = getCookie("savaId");  // undefined 또는 이메일

    // saveId 값이 있을 경우
    if(saveId != undefined) {
        loginEmail.value = saveId; // 쿠키에서 얻어온 값을 input 에 value로 세팅

        // 아이디 저장 체크박스에 체크 해두기
        document.querySelector("input[name='saveId']").checked = true;
    }
}


// 이메일, 비밀번호 미작성 시 로그인 막기
const loginForm = document.querySelector("#loginForm");

const loginPw = document.querySelector("#loginForm input[name='memberPw']");
//console.log(loginPw);
// #loginForm 이 화면에 존재할 때(== 로그인 상태가 아닐 떄)
if(loginForm != null) {
    // 제출 이벤트 발생 시

    loginForm.addEventListener("submit", e => {

        // 이메일 미작성
        if(loginEmail.value.trim().length === 0) {
            alert("이메일을 작성해주세요!");
            e.preventDefault(); // 기본 이벤트(제출) 막기
            loginEmail.focus();
            return;
        }

        // 비밀번호 미작성
        if(loginPw.value.trim().length === 0) {
            alert("비밀번호를 작성해주세요!");
            e.preventDefault(); // 기본 이벤트(제출) 막기
            loginPw.focus();
            return;
        }
    });
}