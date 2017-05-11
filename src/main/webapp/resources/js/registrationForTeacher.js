$( document ).ready(function() {
    $('#submit').click(function(e) {
        removeClassError();
        var firsName = $('#fn').val().trim();
        var lastName = $('#ln').val().trim();
        var patronymic = $('#patronymic').val().trim();
        var email = $('#email').val().trim();
        var userName = $('#username').val().trim();
        var password = $('#password').val().trim();
        var passwordRepeat = $('#passwordR').val().trim();
        var post = $('#post').val().trim();
        var academicDegree = $('#academic-degree').val().trim();
        var academicTitle = $('#academic-title').val().trim();
        var cathedra = $('#cathedra').val().trim();
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
                $('#username').parent().find('span').remove();
                $('#username').addClass('error')
                $('#username').parent().append("<span style=\"color:red;\">Login занят!</span>")
            } else {
                if (!isValidUserName(userName)) {
                    $('#username').parent().find('span').remove();
                    $('#username').addClass('error')
                    $('#username').parent().append("<span style=\"color:red;\">Логин должен быть не меньше 6 символов!</span>")
                }
            }
        }
        if (password == '') {
            $('#password').addClass('error');
        } else {
            if (!isValidPassword(password)) {
                $('#password').addClass('error');
                $('#checkPasswordError').append("<span style=\"color:red;\">Длина пароля меньше 6 знаков! В пароле должна быть хотя бы 1 цифра и 1 буква!</span>")
            }
        }
        if (passwordRepeat == '') {
            $('#passwordR').addClass('error');
        }

        if (password.localeCompare(passwordRepeat) != 0) {
           $('#password').addClass('error');
           $('#passwordR').addClass('error');
           $('#myModal').css('top', $('#passwordR').offset().top)
           $('#checkRepeatPasswordError').append("<span style=\"color:red;\"> Введенные пароли не совпадают</span>")
        }
        if (post == '') {
            $('#post').addClass('error');
        }
        if(academicDegree == '') {
            $('#academic-degree').addClass('error');
        }
        if(academicTitle == '') {
            $('#academic-title').addClass('error');
        }
        if(cathedra == '') {
            $('#cathedra').addClass('error');
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
    $('#checkRepeatPasswordError').empty();
}

function checkUniqueUserName(userName) {
    var uniqueUserName = false;
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
            if (response == "")
                uniqueUserName = true;
        }
    });
    return uniqueUserName;
}

function checkUniqueEmail(email) {
    var uniqueEmail = false;
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
                uniqueEmail = true;
        }
    });
    return uniqueEmail;
}

function isValidUserName(userName) {
    if (userName.length < 6) {
        return false;
    } else {
        return true;
    }
}
function isValidPassword(password) {
    if (password.length < 6) {
        return false;
    } else {
        var digit = new RegExp("\\d")
        var letter = new RegExp("\\w")
        if (password.search(digit) == -1) {
            return false;
        } else {
            if (password.search(letter) == -1 ){
                return false;
            }
            else {
                return true;
            }
        }
    }
}