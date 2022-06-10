$(document).ready(function () {

    //CARICO L'HTML SEZIONE SINISTRA
    $("#sezionesinistra").load("sezionesinistra.html");

    //CARICO L'HTML SEZIONE CENTRALE
    $("#sezionecentrale").load("sezionecentrale.html");

    //CARICO L'HTML SEZIONE DESTRA
    $("#sezionedestra").load("sezionedestra.html");


    class Post {
        constructor(id, testo, data, idUtente) {
            this.id = id;
            this.testo = testo;
            this.data = data;
            this.idUtente = idUtente;
        };
    };

    function renderCommenti(idPost) {
        $.get("commenti/" + idPost, function (ris) {
            for (const c of ris) {
                $(`<ul>
            <li>${c.idCommento}</li>
            <li>${c.testo}</li>
            <li>${c.data}</li>
            <li>${c.idUtente}</li>
            </ul>`).appendTo($('#postList'));
            }
        });
    }





    $('#postBtn').on('click', function () {
        var datacorr = new Date($.now());
        var ora = (datacorr.getHours() + ":" + datacorr.getMinutes() + ":" + datacorr.getSeconds());
        var data = (datacorr.getFullYear() + "-" + datacorr.getMonth() + "-" + datacorr.getDay() + " " + ora);
        const post = new Post(
            $('#testoPost').val(),
            Date.now(),
            1 // FIXME id temporaneo utente
        );
        addPost(post);

    });

    function addPost(post) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: 'post',
            type: 'POST',
            data: JSON.stringify(post),
            success: function (ris) {
                $('#postList').html('');
                renderPost();
            }

        });
    }







    //-- ---------------------- PARTE CENTRALE  ------------------------------------- 
    function renderPost() {
        $.get('post', function (ris) {
            for (const p of ris) {
                $(`
                <div class="box1">
                    <div class="d-flex skfjkk">
                        <div class="lkt40">
                            <img src="./img/polly.jpg" alt="">
                        </div>
                            <div class="pl-2 pt-1">
                        <h6>${p.idUtente}</h6>
                        </div>
                        </div>
                        <hr>
                        <p class="text-muted">
                        ${p.testo}
                         </p>
                    <div>
                </div>
                <div> 
                </div>
                <div class="d-flex justify-content-around">
                    <div>
                        <i class="fa fa-heart"></i>
                        Like
                    </div>
                    <div>
                        <i class="fa fa-comment"></i>
                        Comments
                    </div>
                    <div>
                        <i class="fa fa-share"></i>
                        Share
                    </div>
                </div>
                <div>
                    <hr>
                <textarea name="" id="" cols="3" rows="3" class="form-control" placeholder="Scrivi il commento" ></textarea>
                </div>
                </div>`).appendTo($('#postList'));

            }
        });
    }
    renderPost();

});





















//APPUNTI

/*  function renderPost() {
     $.get('post', function (ris) {
         for (const p of ris) {
             $(`<ul>
         <li>${p.idUtente}</li>
         <li>${p.testo}</li>
         <li>${p.data}</li>
         </ul>`).appendTo($('#postList'));
         renderCommenti(p.id);
         }  
     });
 }
 renderPost();
*/
