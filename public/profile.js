// profile.js

// Function to update password
function updatePassword() {
    var idPlayer = document.getElementById('idPlayer').value;
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var data = {
        idPlayer: idPlayer,
        userName: username,
        newPassword: password
    };

    // Send request to update password
    fetch('/players/updateUserPassword', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(function(response) {
            if (response.ok) {
                alert('Password updated successfully!');
            } else {
                alert('Failed to update password.');
            }
        })
        .catch(function(error) {
            console.log('Error:', error);
        });
}

// Function to fetch user data and populate the form
function fetchUserData(username) {
    fetch(`/players/${username}`)
        .then(function(response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(function(data) {
            document.getElementById('idPlayer').value = data.idPlayer;
            document.getElementById('username').value = data.userName;
            document.getElementById('email').value = data.email;
            document.getElementById('currentLevel').value = data.currentLevel;
            document.getElementById('currentMission').value = data.currentMission;
            document.getElementById('money').value = data.money;
        })
        .catch(function(error) {
            console.log('Error:', error);
        });
}

// Fetch user data when the page loads
window.onload = function() {
    // Assuming username is passed as query parameter, you can change this
    var urlParams = new URLSearchParams(window.location.search);
    var username = urlParams.get('username');
    fetchUserData(username);
};
