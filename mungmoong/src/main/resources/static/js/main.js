// script.js

// 버튼 요소를 가져옵니다.
const scrollToTopBtn = document.getElementById("scrollToTopBtn");

// 스크롤할 때 실행되는 함수
window.onscroll = function() {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        scrollToTopBtn.style.display = "block";
    } else {
        scrollToTopBtn.style.display = "none";
    }
}

// 버튼을 클릭했을 때 실행되는 함수
scrollToTopBtn.onclick = function() {
    scrollToTop();
};

function scrollToTop() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

