$(document).ready(function() {
    $('#loginForm').submit(function(event) {
        // Prevent the form from submitting
        event.preventDefault();

        // Get username and password values
        var username = $('#username').val();
        var password = $('#password').val();

        // Validate username and password
        if (validateCredentials(username, password)) {
            // If credentials are valid, redirect the user
            window.location.href = 'http://localhost:8080/access';
        } else {
            // If credentials are not valid, display an error message
            $('#apiResponse').text('Invalid username or password. Please try again.');
        }
    });

    // Function to validate username and password
    function validateCredentials(username, password) {
        // Check if password is the first four letters of the username
        var firstFourLetters = username.substring(0, 4);
        return password === firstFourLetters;
    }
});
