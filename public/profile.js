// profile.js

// Function to fetch user data and populate the form
function fetchUserData(username) {
    fetch(`/dsaApp/players/${username}`)
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Failed to fetch user data');
            }
            return response.json();
        })
        .then(function(data) {
            // Populate the form with user data
            document.getElementById('idPlayer').value = data.idPlayer;
            document.getElementById('username').value = data.userName;
            document.getElementById('email').value = data.email;
            document.getElementById('currentLevel').value = data.currentLevel;
            document.getElementById('currentMission').value = data.currentMission;
            document.getElementById('money').value = data.money;
        })
        .catch(function(error) {
            console.error('Error fetching user data:', error.message);
            // Handle the error - Display a message to the user
            var errorMessage = 'Failed to fetch user data.';
            if (error.message === 'Failed to fetch user data') {
                errorMessage = 'Failed to fetch user data. User may not exist.';
            }
            // Display error message to the user
            alert(errorMessage);
        });
}

// Fetch user data when the page loads
window.onload = function() {
    // Get username from localStorage
    var username = localStorage.getItem('username');
    if (username) {
        fetchUserData(username);
    } else {
        console.error('Username not found in localStorage.');
    }
};
function updatePassword() {
    var idPlayer = document.getElementById('idPlayer').value;
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var email = document.getElementById('email').value;
    var currentLevel = document.getElementById('currentLevel').value;
    var currentMission = document.getElementById('currentMission').value;
    var money = document.getElementById('money').value;

    var data = {
        idPlayer: idPlayer,
        userName: username,
        password: password,
        email: email,
        currentLevel: currentLevel,
        currentMission: currentMission,
        money: money,

    };

    // Send request to update password
    fetch('/dsaApp/players/updateUserPassword', {
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
