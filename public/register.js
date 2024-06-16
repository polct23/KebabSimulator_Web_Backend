document.addEventListener('DOMContentLoaded', function () {
    const registerForm = document.getElementById('register-form');
    registerForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const email = document.getElementById('register-email').value;
        const username = document.getElementById('register-username').value;
        const password = document.getElementById('register-password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        // Validación básica
        if (!email || !username || !password) {
            showMessage('Email, username, and password cannot be empty', 'Error');
            return;
        }

        // Verificar que las contraseñas coincidan
        if (password !== confirmPassword) {
            showMessage('Passwords do not match', 'Error');
            return;
        }

        const data = {
            email: email,
            userName: username,
            password: password
        };

        fetch('/dsaApp/players/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to register');
                }
                return response.json();
            })
            .then(responseData => {
                showMessage('User registered successfully!', 'Success');
                setTimeout(() => {
                    window.location.href = 'index.html'; // Redirect after successful registration
                }, 2000);
            })
            .catch(error => {
                console.error('Error:', error);
                showMessage('Error: ' + error.message, 'Error');
            });
    });
});

function showMessage(message, title) {
    document.getElementById('messageModalLabel').textContent = title;
    document.getElementById('messageModalBody').textContent = message;
    $('#messageModal').modal('show');
}
