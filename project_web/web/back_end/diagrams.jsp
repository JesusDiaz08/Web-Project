<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Administrator</title>
    <link rel="stylesheet" href="css/style_admin.css">
    <link rel="stylesheet" href="css/main_admin.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css">
</head>

<body>
    <header>
        <div align="right">
            <a href="log_in.html" style="margin-right: 10px;" target="_top">Sign out</a>
        </div>
    </header>
    <input type="hidden" id="idNameUser" value="<%= session.getAttribute("repo_teacher") %>"/>
    <div id='mySidenav' class='sidenav'>
        <a href='teacher_activity.html' id='createDiagram'>Crear diagrama</a>
    </div>
    <div id='mySidenav' class='sidenav'>
        <a href='teacher_asign.html' id='asign'>Asignar</a>
    </div>
    <div id='mySidenav' class='sidenav'>
        <a href='teacher_activity.html' id='evalue'>Evaluar</a>
    </div>
    <div id='mySidenav' class='sidenav'>
        <a href='diagrams.jsp' id='myProjects'>Mis proyectos</a>
    </div>
    
    <div align="center">
        <h1 align="center">Profesor</h1>    
    </div>
    <div class="container" style="margin-left:135px;">
        <div class='row'>
            <div class="col-ms-12 mt-5">
                <table class="table" id="table_diagrams">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th colspan="3">ACCIONES</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody id='table_body'></tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Edita a este usuario</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden=true>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form">
                        <label class="sr-only" for="nombre">Nombre</label>
                        <input type="text" class="form-control md-2 mr-sm-2" id="NameEdit" placeholder="Nombre"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary" id="saveEdit">Guardar cambios</button>    
                </div>  
            </div>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="scripts_diagrams.js"></script>
</body>

</html>