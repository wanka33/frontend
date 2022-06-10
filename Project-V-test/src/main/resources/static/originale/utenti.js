$(document).ready(function () {

    function getUtenti() {

        $.get('utenti', function (ris) {

            for (const ut of ris) {

                $(`<tr>
					<td>${ut.nome}</td>
					<td>${ut.cognome}</td>
                    <td>${ut.numTel}</td>
                    <td>${ut.email}</td>
				
				`).appendTo($('#outputUtenti'));
            }

        })

        
    }
    
    getUtenti();


});

