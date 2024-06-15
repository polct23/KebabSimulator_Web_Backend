// purchaseItem.js
function purchaseItem(abilityId) {
    // Obtener el ID del jugador (suponiendo que se almacena en el localStorage)
    const username = localStorage.getItem('username');

    if (!username) {
        alert('Username not found. Please log in.');
        return;
    }

    // Realizar una solicitud para comprar la habilidad
    fetch(`/api/players/${username}/abilities/${abilityId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert('Ability purchased successfully!');
            } else {
                alert('Failed to purchase ability.');
            }
        })
        .catch(error => console.error('Error purchasing ability:', error));
}
