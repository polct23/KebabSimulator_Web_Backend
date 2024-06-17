// purchaseItem.js
function purchaseItem(abilityId) {
    console.log("Ability ID: ", abilityId); // Verificar el ID de la habilidad

    // Obtener el nombre de usuario del localStorage
    const username = localStorage.getItem('username');

    if (!username) {
        $('#errorModal').modal('show'); // Mostrar modal de error si no hay nombre de usuario
        return;
    }

    // Crear el objeto Player
    const player = {
        userName: username
    };

    // Realizar una solicitud para comprar la habilidad
    fetch(`/dsaApp/players/buyAbility/${abilityId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(player) // Enviar el objeto Player como JSON
    })
        .then(response => {
            if (response.ok) {
                $('#successModal').modal('show'); // Mostrar modal de éxito
            } else if (response.status === 409) {
                $('#alreadyPurchasedModal').modal('show'); // Mostrar modal si la habilidad ya está comprada
            } else {
                $('#errorModal').modal('show'); // Mostrar modal de error si falla la compra
            }
        })
        .catch(error => console.error('Error purchasing ability:', error));
}
