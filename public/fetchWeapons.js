document.addEventListener('DOMContentLoaded', function() {
    const userStatus = localStorage.getItem('userStatus');
    if (userStatus !== 'loggedIn') {
        showMessage('You must be logged in to access this page.', 'Error');
        setTimeout(() => {
            window.location.href = '/';
        }, 2000);
        return;
    }

    // Verifica si hay un inicio de sesión automático
    const autoLogin = localStorage.getItem('autoLogin');
    if (autoLogin === 'true') {
        // Muestra el mensaje de inicio de sesión automático
        const message = document.getElementById('message');
        message.style.display = 'block';
        // Limpia el indicador de inicio de sesión automático
        localStorage.removeItem('autoLogin');
    }

    fetchWeapons(); // Siempre tratar de obtener la versión más reciente de las armas
});

function fetchWeapons() {
    fetch('/dsaApp/weapons/getWeapons', {
        headers: {
            'Cache-Control': 'no-cache', // Indica al navegador no almacenar la respuesta en caché
            'Pragma': 'no-cache' // Compatibilidad con HTTP/1.0
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            displayWeapons(data); // Mostrar los datos directamente obtenidos del servidor
        })
        .catch(error => {
            console.error('Fetch error:', error);
            showMessage('Failed to load weapons from server. Please try again later.', 'Error');
        });
}

function displayWeapons(data) {
    const listElement = document.getElementById('weapons-list');
    listElement.innerHTML = '';
    data.forEach(weapon => {
        const card = createWeaponCard(weapon);
        listElement.appendChild(card);
    });
}

function createWeaponCard(weapon) {
    const card = document.createElement('div');
    card.className = 'card mb-3';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    const weaponName = document.createElement('h5');
    weaponName.className = 'card-title';
    weaponName.textContent = weapon.weaponName;

    const weaponID = document.createElement('h6');
    weaponID.className = 'card-subtitle mb-2 text-muted';
    weaponID.textContent = `ID: ${weapon.idWeapon}`;

    const description = document.createElement('p');
    description.className = 'card-text';
    description.textContent = `Description: ${weapon.description}`;

    const damage = document.createElement('p');
    damage.className = 'card-text';
    damage.textContent = `Damage: ${weapon.damage}`;

    const price = document.createElement('p');
    price.className = 'card-text';
    price.textContent = `Price: $${weapon.price}`;

    cardBody.appendChild(weaponName);
    cardBody.appendChild(weaponID);
    cardBody.appendChild(description);
    cardBody.appendChild(damage);
    cardBody.appendChild(price);

    card.appendChild(cardBody);
    return card;
}

function showMessage(message, title) {
    document.getElementById('messageModalLabel').textContent = title;
    document.getElementById('messageModalBody').textContent = message;
    $('#messageModal').modal('show');
}
