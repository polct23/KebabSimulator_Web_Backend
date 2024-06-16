// purchaseItem.js
function purchaseItem(abilityId) {
    console.log("Ability ID: ", abilityId); // Verificar el ID de la habilidad

    // Obtener el nombre de usuario del localStorage
    const username = localStorage.getItem('username');

    if (!username) {
        alert('Username not found. Please log in.');
        return;
    }

    // Crear el objeto Player
    const player = {
        userName: username
    };

    // Realizar una solicitud para comprar la habilidad
    fetch(`http://localhost:8080/dsaApp/players/buyAbility/${abilityId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(player) // Enviar el objeto Player como JSON
    })
        .then(response => {
            if (response.ok) {
                alert('Ability purchased successfully!');
            } else if (response.status === 409) {
                alert('Ability already purchased.');
            } else {
                alert('Failed to purchase ability.');
            }
        })
        .catch(error => console.error('Error purchasing ability:', error));
}
