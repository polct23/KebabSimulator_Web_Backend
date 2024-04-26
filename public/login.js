function handleLogin(username, password) {
    const url = 'http://localhost:8080/dsaApp/users/login';
    const data = {
        userName: username,
        password: password
    };

    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    fetch(url, fetchOptions)
        .then(response => response.json())
        .then(data => {
            if (data.message === "Login Successful") {
                console.log('Login successful');
                alert("Loged successfully");
                localStorage.setItem('userStatus', 'loggedIn');
            } else {
                throw new Error(data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Error: " + error.message);
        });
}
