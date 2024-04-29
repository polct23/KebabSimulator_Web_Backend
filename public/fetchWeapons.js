document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/dsaApp/weapons/getWeapons')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const listElement = document.getElementById('weapons-list');
            listElement.innerHTML = ''; // Limpiar lista anterior
            data.forEach(weapon => {
                // Crear la estructura de la tarjeta para cada arma
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

                // Añadir los detalles al cuerpo de la tarjeta
                cardBody.appendChild(weaponName);
                cardBody.appendChild(weaponID);
                cardBody.appendChild(description);
                cardBody.appendChild(damage);
                cardBody.appendChild(price);

                card.appendChild(cardBody);
                listElement.appendChild(card);
            });
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
});
