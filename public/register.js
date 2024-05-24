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
            alert('Email, username and password cannot be empty');
            return;
        }

        // Verificar que las contraseñas coincidan
        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }

        const data = {
            email: email,
            userName: username,
            password: password
        };

        fetch('/dsaApp/users/newUser', {
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
                alert('User registered successfully!');
                window.location.href = 'index.html'; // Redirect after successful registration
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error: ' + error.message);
            });
    });
});
