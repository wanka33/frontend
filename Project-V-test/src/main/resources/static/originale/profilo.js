$(document).ready(function () {


    function getProfilo() {

        $.get('utenti/1', function (p) {

            

                $(`<tr><th>Nome</th>
                <td>${p.nome}<br /></td>
            </tr>
            <tr>
                <th>Cognome</th>
                <td>${p.cognome}<br /></td>
            </tr>
            <tr>
                 <th>Numero telefono</th>
                <td>${p.numTel}<br /></td>
            </tr>
            <tr>
                <th>E-mail</th>
                 <td>${p.email}<br /></td>
            </tr>
            <tr>
                 <th>Residenza</th>
                <td>${p.indirizzo}<br /></td>
            </tr>
            <tr>
                 <th>Codice fiscale</th>
                <td>${p.cf}<br /></td>
            </tr>`).appendTo($('#outputTable'));
            

        })
       

    }

    getProfilo();
});