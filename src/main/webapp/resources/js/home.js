$(function() {
/*     $(document).on('click','.updateTechTask', function(e) {
        var id ={
            "id": $(this).val()
        }
*//*        var sstt = $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/home/update-technical-task",
            data: JSON.stringify(id),
            dataType: 'json',
            success: function() {
                window.location.href = "/home/update-technical-task";
            }
        });
     });*/
     var idTechnicalTask;
    $(document).on('click','.addTTForStudent', function(e) {
    var appoints = $('#myModal').find('.appoint');
        $.each(appoints, function(index, elem){
            $(elem).removeClass('disabled');
        });
        $('#myModal').modal('show');
        idTechnicalTask = $(this).val();
    });



    $(document).on('click', '.appoint', function(e) {
        var idStudent = $(this).val();
        var request = {
            "idTechnicalTask": idTechnicalTask,
            "idStudent": idStudent
        }
        var appoints = $('#myModal').find('.appoint');
        $.each(appoints, function(index, elem){
           $(elem).addClass('disabled');
        });
        var sstt = $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "rest/appoint-technical-task",
            data: JSON.stringify(request),
            dataType: 'json',
            success: function() {
                location.reload();
            }
        });
    });
});