function showForm(formId) {
    document.querySelectorAll('.login-form').forEach(form => form.style.display = 'none');
    document.getElementById(formId).style.display = 'block';

    document.querySelectorAll('.tabs button').forEach(button => button.classList.remove('active'));
    document.querySelector(`.tabs button[onclick="showForm('${formId}')"]`).classList.add('active');
}