async function handleLogin(username, password) {
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

    try {
        const response = await fetch(url, fetchOptions);
        const result = await response.json();

        if (result.message === "Login Successful") {
            console.log('Login successful');
            // Almacena el estado del usuario como autenticado en localStorage
            localStorage.setItem('userStatus', 'loggedIn');
            localStorage.setItem('username', username);
            localStorage.setItem('password', password);

            // Muestra una alerta y redirige solo si el usuario acepta
            alert("Login successful! You will be redirected.");
            setTimeout(() => {
                window.location.href = '/listaObjetos.html'; // Redirige a la página listaObjetos.html
            }, 0);
        } else {
            // Si el mensaje no indica un inicio de sesión exitoso, lanza un error
            throw new Error(result.message || 'Unknown login error');
        }
    } catch (error) {
        console.error('Login Error:', error);
        // Muestra un mensaje de error si la solicitud falla o si la respuesta no es exitosa
        alert("Error: " + error.message);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var loginForm = document.getElementById('login-form');
    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        var username = document.getElementById('login-username').value;
        var password = document.getElementById('login-password').value;
        handleLogin(username, password);
    });
});
