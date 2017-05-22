$(function() {
     $(document).on('click','.updateTechTask', function(e) {
        var id ={
            "id": $(this).val()
        }
        var sstt = $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/home/update-technical-task",
            data: JSON.stringify(id),
            dataType: 'json',
            success: function() {
                window.location.href = "/home/update-technical-task";
            }
        });
     });
});