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

            // Muestra un mensaje de alerta indicando que el inicio de sesión fue exitoso
            alert("Logged in successfully");

            // Almacena el estado del usuario como autenticado en localStorage
            localStorage.setItem('userStatus', 'loggedIn');
            localStorage.setItem('username', username);

            // Redirige a la página deseada solo después de que el usuario haya aceptado la alerta
            window.location.href = '/listaObjetos.html';
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
