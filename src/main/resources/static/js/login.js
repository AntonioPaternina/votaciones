$('.message a').click(function () {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

$(document).ready(function () {
    $('#loginBtn').click(function () {
        var username = $('#loginUsername').val();
        var password = $('#loginPassword').val();

        $.ajax({
            url: 'index.html',
            method: 'GET',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
            },
            success: function (data) {
                $(location).attr('href','index.html');
            },
            error: function () {
                alert('Ha ocurrido un error');
            }
        });
    });
});