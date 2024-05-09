/**
 * 
 */
$(document).ready(function() {
    $('#datatable').DataTable();

var $loginMsg = $('.loginMsg'),
  $login = $('.login'),
  $signupMsg = $('.signupMsg'),
  $signup = $('.signup'),
  $frontbox = $('.frontbox');

/*$('#switch1').on('click', function() {
  $loginMsg.toggleClass("visibility");
  $frontbox.addClass("moving");
  $frontbox.addClass("tablebox");
  $signupMsg.toggleClass("visibility");

  $signup.toggleClass('hide');
  $login.toggleClass('hide');
})*/

$('#switch2').on('click', function() {
  $loginMsg.toggleClass("visibility");
  $frontbox.removeClass("moving");
  $frontbox.removeClass("tablebox");
  $signupMsg.toggleClass("visibility");

  $signup.toggleClass('hide');
  $login.toggleClass('hide');
})



    $('#user-form').on('submit', function(event) {
        event.preventDefault();

        const documentType = $('#document-type').val();
        const documentNumber = $('#document-number').val();
	
	    if (documentType === 'c') {
	        //if (!/^\d{8}$/.test(documentNumber)) {
			if(!/^([0-9]{3,8}|[0-9]{10})$/.test(documentNumber) ){
	            alert('Cedula invalida. Por favor ingrese un numero de 8 digitos.');
	            return;
	        }
	    } else if (documentType === 'p') {
	        if (!/^([0-9]{7})$/.test(documentNumber)) {
	            alert('Pasaporte Invalido. Por favor ingrese los 7 digitos, sin las dos letras.');
	            return;
	        }
	    } else {
	            alert('Se debe seleccionar algun tipo de documento.');
	            return;			
		}
		var person = {documento: documentNumber, tipoDocument: documentType}; 
        $.ajax({
            url: '/consultar/usuario',
            type: 'POST',
            dataType: 'json',
			contentType: "application/json; charset=utf-8",
            data: JSON.stringify(person),
            beforeSend: function( xhr, settings ){  
	            var table = $("#datatable").DataTable();
	            var rows = table.rows().remove().draw();            
          	},
            success: function(data) {
                const users = data.users;
                const table = $('#datatable').DataTable();
                table.clear();
                users.forEach(function(user) {
                    table.row.add([
                        user.firstname,
                        user.secondname,
                        user.lastname,
                        user.sendlastname,
                        user.telefono,
                        user.dirección,        
                        user.ciudad
                    ]);
                });
                table.draw();
                $loginMsg.toggleClass("visibility");
				$frontbox.addClass("moving");
				$frontbox.addClass("tablebox");
				$signupMsg.toggleClass("visibility");
				
				$signup.toggleClass('hide');
				$login.toggleClass('hide');
            },
            error: function(error, textStatus, errorThrown) {
                console.log("El servicio genero un error.");
                if(error.status == 404){
					alert(error.responseJSON.mensaje);
				} else if(error.status  == 500 || error.status == 400){
					alert(error.responseJSON.error);
				}
            }
        });
    });
});