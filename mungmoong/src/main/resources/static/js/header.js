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

