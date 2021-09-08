$(function () {

    $('#login').click(function () {
        const data = $('.form-signin form').serialize();
        $.ajax({
            method: "POST",
            url: '/login',
            data: data,
            success: function (response) {

                window.location = "/main";

            }
        });
        return false;
    });

});
