
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
        } else {
            if (checkUniqueEmail(email) == false) {
                $('#email').parent().find('span').remove();
                $('#email').addClass('error');
                $('#email').parent().append("<span style=\"color:red;\">Email занят!</span>")
            }
        }
        if (userName == '') {
            $('#username').addClass('error');
        } else {
            if (checkUniqueUserName(userName) == false) {
            debugger;
                $('#username').parent().find('span').remove();
                $('#username').addClass('error')
                $('#username').parent().append("<span style=\"color:red;\">Login занят!</span>")
            }
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

        if ($('fieldset .error').length == 0) {
            var data = {
                "firstName": firsName,
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
                window.location.replace("/");
              },
            failure: function(XMLHttpRequest, textStatus, errorThrown) {
            console.debug(XMLHttpRequest, textStatus, errorThrown)
                    alert("Status: " + textStatus);
                }
            });
        } else {
            return false;
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
    $('#email').empty();
}

function checkUniqueUserName(userName) {
    var data = {
        "userName": userName
    }
    $.ajax({
        url: '/rest/user-by-username',
        data: JSON.stringify(data),
        async: false,
        type: 'POST',
        contentType: "application/json",
        success: function(response) {
        debugger;
            if (response == "")
                return true;
            else
                return false;
        }
    });
}

function checkUniqueEmail(email) {
    var data = {
        "email": email
    }
    $.ajax({
        url: '/rest/user-by-email',
        data: JSON.stringify(data),
        async: false,
        type: 'POST',
        contentType: "application/json",
        success: function(response) {
            if (response == "")
                return true;
            else
                return false;
        }
    });
}