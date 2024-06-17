document.addEventListener('DOMContentLoaded', function() {
    // Verifica si el usuario está autenticado
    const userStatus = localStorage.getItem('userStatus');
    if (userStatus !== 'loggedIn') {
        alert('You must be logged in to access this page.');
        window.location.href = '/';
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

    // Cargar los enemigos y añadir botones de compra
    fetchEnemies(); // Siempre tratar de obtener la versión más reciente de los enemigos
});

function fetchEnemies() {
    fetch('/dsaApp/enemies/getEnemies', {
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
            displayEnemies(data); // Mostrar los datos directamente obtenidos del servidor
        })
        .catch(error => {
            console.error('Fetch error:', error);
            alert('Failed to load enemies from server. Please try again later.');
        });
}

function displayEnemies(data) {
    const listElement = document.getElementById('enemies-list');
    listElement.innerHTML = '';
    data.forEach(enemy => {
        const card = createEnemyCard(enemy);
        listElement.appendChild(card);
    });
}

function createEnemyCard(enemy) {
    const card = document.createElement('div');
    card.className = 'card mb-3';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    const enemyName = document.createElement('h5');
    enemyName.className = 'card-title';
    enemyName.textContent = enemy.name;

    const enemyId = document.createElement('h6');
    enemyId.className = 'card-subtitle mb-2 text-muted';
    enemyId.textContent = `ID: ${enemy.idEnemy}`;

    const meat = document.createElement('p');
    meat.className = 'card-text';
    meat.textContent = `Meat: ${enemy.meat}`;

    const speed = document.createElement('p');
    speed.className = 'card-text';
    speed.textContent = `Speed: ${enemy.speed}`;

    cardBody.appendChild(enemyName);
    cardBody.appendChild(enemyId);
    cardBody.appendChild(meat);
    cardBody.appendChild(speed);

    card.appendChild(cardBody);
    return card;
}
