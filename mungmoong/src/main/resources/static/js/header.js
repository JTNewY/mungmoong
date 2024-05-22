function toggleMenu() {
    const auth = document.getElementById('auth');

    if (auth.style.display === 'flex' || auth.style.display === '') {
        auth.style.display = 'none';
    } else {
        auth.style.display = 'flex';
    }
}
