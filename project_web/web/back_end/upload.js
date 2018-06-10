document.addEventListener("DOMContentLoaded", ()=>{
    let form = document.getElementById("form-upload");
    form.addEventListener("submit", function(event){
       event.preventDefault();
       uploadFile(this);
    });
});


function uploadFile(form){
    let barra_estado = form.children[1].children[0],
        span = barra_estado.children[0],
        boton_cancelar = form.children[2].children[1];

    barra_estado.classList.remove('barra_verde', 'barra_roja');
    
    /*Establecemos la peticion*/
    let peticion = new XMLHttpRequest();
    /*Progreso de subida*/
    peticion.upload.addEventListener("progress", (event) => {
        let porcentaje = Math.round((event.loaded / event.total) * 100);
        console.log(porcentaje);
        
        barra_estado.style.width = porcentaje+'%';
        span.innerHTML = porcentaje+'%';
    });
    
    /*El proceso ha finalizado*/
    peticion.addEventListener("load",() => {
       barra_estado.classList.add('barra_verde');
       span.innerHTML = "Proceso completo";
    });
    
    /*Enviar datos a Servleyt*/
    peticion.open('post','../UploadServlet');
    peticion.send(new FormData(form));
    
    /*El procesos ha sido cancelado*/
    boton_cancelar.addEventListener("click", () => {
        peticion.abort();
        barra_estado.classList.remove('barra_verde');
        barra_estado.classList.add('barra_roja');
        span.innerHTML = "Proceso cancelado";
    });
}


