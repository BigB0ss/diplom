/**
 * Created by Kirill on 21.03.2017.
 */

var claims = [];
$(function() {
    $(document).on('click', '.btn-addPoint', function(e) {
        e.preventDefault();
        var claim = "<div id=\"first-claim\" class=\"claim-template\" style=\"margin-top:20px;\">" +
            "<label>Пунк требований</label>" +
            "<br>" +
            "<input  class=\"form-control\"  style=\"width: 90%;float: left;\" type=\"text\" />" +
            "<div>" +
            "<button class=\"btn btn-addPoint\" style=\"float:right;\" id=\"buttonPoint\">" +
            "<span class=\"glyphicon glyphicon-plus\"></span></button>" +
            "</div>" +
            "<br>" +
            "<label>Описание</label>" +
            "<br>" +
            "<div style=\"width: 90%; float: left;\">" +
            "<textarea class=\"form-control\" type=\"text\"></textarea>" +
            "</div>" +
            "<div>" +
            "<button class=\"btn btn-secondary btn-add\" style=\"float:right\" id=\"remove-button\"><span class=\"glyphicon glyphicon-plus\"></span></button>" +
            "</div>"
        "</div>"
        $('#custom-claim-container').append(claim);

        var controlForm = $('#custom-claim-container');

        controlForm.find('.claim-template:not(:last) .btn-addPoint')
            .removeClass('btn-addPoint').addClass('btn-removePoint')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-removePoint', function(e) {
        $(this).parent().parent().remove();
        e.preventDefault();
        return false;
    });

    $(document).on('click', '.btn-add', function(e) {
        e.preventDefault();
        var textArea =
        "<div style=\"width: 90%; float: left;\">" +
            "<textarea class=\"form-control\" type=\"text\"></textarea>" +
            "</div>" +
            "<div>" +
            "<button class=\"btn btn-add\" style=\"float:right\" id=\"buttonDescription\"><span class=\"glyphicon glyphicon-plus\"></span></button>" +
            "</div>"
        $('#mainTaskBody').append(textArea);
        $(this).parent().append(textArea);
        $(this).parent().find('.btn-add:not(:last)')
            .removeClass('btn-add').addClass('btn-remove')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    });


    $(document).on('click', '.btn-addTask', function(e) {
        e.preventDefault();
        var task = "<tr><td style=\"border: none;\"><textarea class=\"form-control\" type=\"text\"></textarea></td><td style=\"width:50px; border: none;\"><button class=\"btn btn-addTask\" style=\"margin-top: 15px;float:left;\" id=\"buttonTask\"><span class=\"glyphicon glyphicon-plus\"></span></button></td></tr>";
        $('#mainTaskBody').append(task);
        var controlForm = $('#tasksContainer');
        controlForm.find('.btn-addTask:not(:last)')
            .removeClass('btn-addTask').addClass('btn-removeTask')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-removeTask', function(e) {
        var result = confirm("Вы точно хотите удалить Пункт ТЗ?");
        if (result) {
            $(this).parent().parent().remove();
            e.preventDefault();
        }
    });



    //save technical-task
    $(document).on('click', '#submit', function(e) {
        $('#name').removeClass('error');
        $('#target').removeClass('error');

        $('.custom-claim').contents().find('input').each(function() {
            $(this).removeClass('error');
        });

        $('.custom-claim').contents().find('textarea').each(function() {
            $(this).removeClass('error');
        });

        var name = $('#name').val().trim();
        var target = $('#target').val().trim();
        var type = $('#type').val();
        var discipline = $('#discipline').val();

        if (name == '') { $('#name').addClass('error') }
        if (target == '') { $('#target').addClass('error') }

        var inputs = new Array();
        $('.custom-claim').contents().find('input').each(function() {
            if ($(this).val().trim() == '') {
                $(this).addClass('error');
            }
            inputs.push($(this).val().trim());
        });

        var textAreas = new Array();
        $('.custom-claim').contents().find('textarea').each(function() {
            if ($(this).val().trim() == '') {
                $(this).addClass('error');
            }
            textAreas.push($(this).val());
        });

        var softwares = new Array();
        $('#software').contents().find('input').each(function() {
            if ($(this).is(":checked")) {
                softwares.push($(this).attr("id"));
            }
        });

        var hardwares = new Array();
        $('#hardware').contents().find('input').each(function() {
            if ($(this).is(":checked")) {
                hardwares.push($(this).attr("id"));
            }
        });

        if ($('#container .error').length > 0) {

        } else {
            var technicalTask = {
                "name": name,
                "target": target,
                "type": type,
                "discipline": discipline,
                "dateCreated": new Date(),
                "demand": inputs,
                "demandDescription": textAreas,
                "softwares": softwares,
                "hardwares": hardwares
            }
            console.log(technicalTask);
            saveTechnicalTask(technicalTask);
        }
    });

    var recipient;
    $('#exampleModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        recipient = button.data('whatever') // Extract info from data-* attributes
            // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New ' + recipient)
        modal.find('.modal-body input').val(recipient)
    }).on('click', '.btn-primary', function(e) {
        if ($('#message-text').val() != '') {
            saveModule($('#message-text').val(), recipient);
            $('#exampleModal').modal('toggle');
            $('#message-text').val('');
            location.reload();
        } else {}
    });

    function saveModule(module, recipient) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/rest/module/" + recipient,
            data: JSON.stringify(module),
            dataType: 'json',
            timeout: 600000,
            failure: function(result) {
                alert("Упс ошибка! Что то пошло не так!")
            }
        });
    }

    function saveTechnicalTask(technicalTask) {
        var sstt = $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/rest/technical-task",
            data: JSON.stringify(technicalTask),
            dataType: 'json',
            success: function() {
                location.reload();
            }
        });
        console.log(sstt);
    }

});
$(document).ready(function(e) {
    $('.btn-removeTask').trigger('click');
});