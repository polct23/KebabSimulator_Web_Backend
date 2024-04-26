function handleLogin(username, password) {
    const url = '/users/newUser';
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
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            console.log('Registro exitoso:', data);
            // Aquí puedes manejar la respuesta exitosa, como redirigir a otra página
        })
        .catch(error => {
            console.error('Error:', error);
            // Aquí puedes manejar errores, como mostrar un mensaje al usuario
        });
}
