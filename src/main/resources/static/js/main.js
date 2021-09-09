$(function () {

    $('#login').click(function () {
        const data = $('.px-3 form').serialize();
        $.ajax({
            method: "POST",
            url: '/login',
            data: data,
            success: function (response) {

                // window.location = "/main";

            }
        });
        return false;
    });



});