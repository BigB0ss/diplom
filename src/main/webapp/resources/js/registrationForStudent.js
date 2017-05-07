
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

        if (password.localeCompare(passwordRepeat) != 0) {
           $('#password').addClass('error');
           $('#passwordR').addClass('error');
           $('#myModal').css('top', $('#passwordR').offset().top)
           $('#checkPasswordError').append("<span style=\"color:red;\">Введенные пароли не совпадают</span>")
        }
        if ($('fieldset .error').length > 0) {

        } else {
            var data = {
                "firsName": firsName,
                "lastName": lastName,
                "patronymic": patronymic,
                "email": email,
                "userName": userName,
                "password": password,
                "group": group
            }
            console.log(data);
            $.ajax({
              url: "/rest/student-registration",
              type: "POST",
              dataType: 'json',
              contentType: "application/json",
              data: JSON.stringify(data),
              success: function(response){
                alert(response);
              }

            });
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
    $('#checkPasswordError').empty();
}