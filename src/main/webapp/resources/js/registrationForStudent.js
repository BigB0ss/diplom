
$( document ).ready(function() {
    $('#submit').click(function() {
        removeClassError();
        var firsName = $('#fn').val().trim();
        var lastName = $('#ln').val().trim();
        var patronymic = $('#patronymic').val().trim();
        var email = $('#email').val().trim();
        var userName = $('#username').val().trim();
        var password = $('#password').val().trim();
        var passwordRepeat = $('#passwordR').val().trim();
        var group = $('#group').val().trim();
        debugger;
        if (firsName == '') {
             $('#fn').addClass('error');
        }
        if (lastName == '') {
            $('#ln').addClass('error');
        }
        if (patronymic == '') {
            $('#patronymic').addClass('error');
        }
        if (email == '') {
            $('#email').addClass('error');
        }
        if (userName == '') {
            $('#username').addClass('error');
        }
        if (password == '') {
            $('#password').addClass('error');
        }
        if (passwordRepeat == '') {
            $('#passwordR').addClass('error');
        }
        if (group == '') {
            $('#group').addClass('error');
        }

        if ($('fieldset .error').length > 0) {
            console.log(('fieldset .error').length)
        } else {

        }
    });
});

function removeClassError () {
    $('#fn').removeClass('error');
    $('#ln').removeClass('error');
    $('#patronymic').removeClass('error');
    $('#email').removeClass('error');
    $('#username').removeClass('error');
    $('#password').removeClass('error');
    $('#group').removeClass('error');
    $('#passwordR').removeClass('error');
}