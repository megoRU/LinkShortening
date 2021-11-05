$(function () {

    $('#submit').click(function () {
        const data = $('.form-signin form').serialize();
        $.ajax({
            method: "POST",
            url: '/registration',
            data: data,
            success: function (response) {

                // window.location = "/login";

            },
            error: function () {
                alert('Пользователь уже существует');
            }
        });
        return false;
    });

});
