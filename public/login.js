function handleLogin(username, password) {
    const url = 'http://localhost:8080/dsaApp/users/login';
    // Asegúrate de que la URL coincide con el endpoint de tu API
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
        .then(response => {
            if (!response.ok) {
                if (response.status === 401) {
                    throw new Error('Unauthorized - Incorrect username or password');
                } else {
                    throw new Error('Failed to log in - Server responded with status: ' + response.status);
                }
            }
            return response.json();
        })
        .then(response => {
            console.log('Login successful:', response);
            // Aquí podrías guardar datos en el almacenamiento local del navegador y redirigir al usuario
            localStorage.setItem('userData', JSON.stringify(response));
            window.location.href = '/dashboard.html'; // Redirige a la página de inicio del usuario
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Error: " + error.message);
        });
}
