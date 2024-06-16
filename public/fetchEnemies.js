document.addEventListener('DOMContentLoaded', function() {
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

    const enemyID = document.createElement('h6');
    enemyID.className = 'card-subtitle mb-2 text-muted';
    enemyID.textContent = `ID: ${enemy.id}`;

    // Añade aquí más propiedades del enemigo si las hay

    cardBody.appendChild(enemyName);
    cardBody.appendChild(enemyID);

    card.appendChild(cardBody);
    return card;
}