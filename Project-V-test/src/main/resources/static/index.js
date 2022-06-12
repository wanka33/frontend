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

    
    /* ------------- BOTTONE ADD POST ----------------------------------------*/
   
    $('#postBtn').click(function () {
        var data = new Date($.now()).toJSON();
        const post = new Post(
            $('#testoPost').val(),
            data,
            1 // FIXME id temporaneo utente
        );
        console.log(post);
        addPost(post);

    });

    /* ------------- FUNZIONE ADD POST ----------------------------------------*/
    function addPost(post) {
        console.log("post: ", post);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: 'post',
            type: 'POST',
            data: JSON.stringify(post),
            success: function (ris) {
                console.log("ris: ", ris);
                $('#postList').html('');
                getPost();
            }

        });
    }


    /* ------------- RENDER POST E COMMENTI POST ----------------------------------------*/
    function renderPost() {
        $.get('post/withComm', function (ris) {
            let dataBella;
            for (const p of ris) {
                dataBella = bellaData(p.data);
                let render =
                    `<div class="box1">
                        <div class="d-flex skfjkk">
                            <div class="lkt40">
                                <img src="./img/polly.jpg" alt="">
                            </div>
                            <div class="pl-2 pt-1">
                        <h6>${p.idUtente}</h6>
                        <p><small><em>${dataBella}</em></small></p>
                        </div>
                        </div>
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
                <textarea name="" id="" cols="3" rows=1" class="form-control" placeholder="Scrivi il commento" ></textarea>
                </div>
                <br>    
                
                <div id="commmentiList>
                
             </div>
    
                </div>`

                if (p.commenti && p.commenti.length > 0) {
                    
                    for (const c of p.commenti) {
                        dataBella = bellaData(c.data);
                        render += 
                        `<br>
                        <div>
                             <p name="" id="" cols="3" rows="1" class="form-control"  style="background-color:#DADDE1; border-radius: 15px;">
                                <b>${c.idUtente}</b><br>
                                 ${c.testo}
                             </p>
                            <p style="margin-top:-20px; padding-left: 10px;">
                                 <small><em>${dataBella}</em></small>
                            </p>
                        </div`
                    }
                }

                $(render).appendTo($('#postList'));



            }
        });

    }
    renderPost();

    /* ------------- FORMATTAZIONE DATA ----------------------------------------*/
    function bellaData(dataBrutta) {
        var data = new Date(dataBrutta);
        var ora = data.getHours() + ":" + data.getMinutes()
        var databella = data.getDate() + "-" + data.getMonth() + "-" + data.getFullYear() + " " + ora;
        return databella;
    }




});

























    //old one

/*  //CARICO L'HTML SEZIONE SINISTRA
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


             <div id="commmentiList>
            
          </div>

             </div>`

             ).appendTo($('#postList'));
             renderCommenti(p.id);


         }
     });
 }
 renderPost();


 function renderCommenti(idPost) {
     $.get("commenti/" + idPost, function (ris) {
         for (const c of ris) {
             $(` <br>
             <p name="" id="" cols="3" rows="1" class="form-control">
             ${c.testo}
              </p>`).appendTo($('#commentiList'));
         }
     });
 }

}); */