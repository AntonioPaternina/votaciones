$(document).ready(function () {
  cargardatos();
  
});



function cargardatos(){
    var username = $('#loginUsername').val();
        var password = $('#loginPassword').val();

        $.ajax({
            url: '/eleccion/consultar',
            method: 'GET',
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert('Ha ocurrido un error');
            }
        });
}

