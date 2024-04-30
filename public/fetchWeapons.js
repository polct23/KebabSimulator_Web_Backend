document.addEventListener('DOMContentLoaded', function() {
    const savedWeapons = localStorage.getItem('weapons');
    if (savedWeapons) {
        displayWeapons(JSON.parse(savedWeapons));
    } else {
        fetchWeapons();
    }
});

function fetchWeapons() {
    fetch('http://localhost:8080/dsaApp/weapons/getWeapons')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            localStorage.setItem('weapons', JSON.stringify(data)); // Guardar en localStorage
            displayWeapons(data);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function displayWeapons(data) {
    const listElement = document.getElementById('weapons-list');
    listElement.innerHTML = ''; // Asegúrate de que esta línea efectivamente está limpiando la lista
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
