function toggleMenu() {
    const mobileMenu = document.getElementById('mobile-menu');
    if (mobileMenu.style.display === 'flex') {
        mobileMenu.style.display = 'none';
    } else {
        mobileMenu.style.display = 'flex';
    }
}

window.addEventListener('resize', function() {
    const mobileMenu = document.getElementById('mobile-menu');
    const nav = document.getElementById('nav');
    const auth = document.getElementById('auth');
    
    if (window.innerWidth > 768) {
        mobileMenu.style.display = 'none';
        nav.style.display = 'flex';
        auth.style.display = 'flex';
    } else {
        nav.style.display = 'none';
        auth.style.display = 'none';
    }
});

function trainer(){
    location.href = '/reserve/reserve.html'
}

function trainerConfirm() {
    Swal.fire({
        title: "관리자가 훈련사 승인 중입니다.",
        text: "1일 이상 소요될 수 있습니다. 잠시만 기다려 주세요~!",
        width: 600,
        padding: "3em",
        color: "#716add",
        background: "#fff url(https://sweetalert2.github.io/images/trees.png)",
        backdrop: `
          rgba(0,0,123,0.4)
          url("https://sweetalert2.github.io/images/nyan-cat.gif")
          left top
          no-repeat
        `
    });
}