document.addEventListener("DOMContentLoaded", function() {
    const itemList = document.getElementById('weapons-list');
    itemList.addEventListener('click', function(e) {
        if (e.target && e.target.classList.contains('buy-btn')) {
            const itemId = e.target.getAttribute('data-id');
            buyItem(itemId);
        }
    });
});

function buyItem(itemId) {
    // Aquí deberías integrar la lógica para interactuar con tu backend
    fetch('/path/to/api/purchase', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ itemId: itemId })
    })
        .then(response => response.json())
        .then(data => {
            displayMessage('Compra realizada con éxito.', 'success');
        })
        .catch(error => {
            displayMessage('Error al realizar la compra.', 'danger');
        });
}

function displayMessage(message, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.className = `alert alert-${type}`;
    messageDiv.textContent = message;
    messageDiv.style.display = 'block';
}
