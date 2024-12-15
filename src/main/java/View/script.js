// JavaScript to handle navigation between views
const registerButton = document.getElementById('registerButton');
const backButton = document.getElementById('backButton');
const menuView = document.getElementById('menu');
const registerView = document.getElementById('registerView');

// Show the registration form when "Register as Administrator" is clicked
registerButton.addEventListener('click', () => {
    menuView.classList.remove('active');
    registerView.classList.add('active');
});

// Go back to the main menu when "Back" is clicked
backButton.addEventListener('click', () => {
    registerView.classList.remove('active');
    menuView.classList.add('active');
});

// Handle form submission
const registerForm = document.getElementById('registerForm');
registerForm.addEventListener('submit', (e) => {
    e.preventDefault(); // Prevent actual form submission
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    alert(`Username: ${username}\nPassword: ${password}`);
});