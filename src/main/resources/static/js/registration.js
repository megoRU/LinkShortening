$(function () {

    $('#submit').click(function () {
        const data = $('.form-signin form').serialize();
        $.ajax({
            method: "POST",
            url: '/registration',
            data: data,
            success: function (response) {

                window.location = "/login";

            }
        });
        return false;
    });

});
