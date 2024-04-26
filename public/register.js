document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('register-form').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission
        var username = document.getElementById('register-username').value;
        var password = document.getElementById('register-password').value;

        // Basic validation
        if (!username || !password) {
            alert('Username and password cannot be empty');
            return;
        }

        var data = {
            userName: username,
            password: password
        };

        fetch('http://localhost:8080/dsaApp/users/newUser', {
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
                return response.json(); // Assuming the server responds with JSON
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
