(function ($) {

    'use strict';

    $("#btnAdd").click(function(){
        pop_ventana = window.open('registro.html','Registrar usuario','width=400,height=650');
        getActivities();
    });

    function fillTable(diagrams) {
        $("#table_diagrams > tbody").empty();
        diagrams.forEach(function (diagram) {
            $("#table_diagrams > tbody").append($("<tr>").append($("<td>", {
                    text: diagram.nameProject
                }), $("<td>").append($("<button>", {
                    class: 'btn btn-danger mr-2',
                    text: 'Eliminar'
                }).click(function () {
                    remove(diagram)
                }), $("<button>", {
                    class: 'btn btn-warning',
                    text: 'Editar'
                }).click(function () {
                    edit(diagram)
                }), $("<button>", {
                    class: 'btn btn-primary',
                    text: 'Ver diagrama'
                }).click(function(){
                    showDiagram(diagram)         
                }))
            ));
        });
    }

    function showDiagram(diagram){
        
    }

    function getActivities() {
        $.get("../ServletGetDiagrams", {
            name_user: $("#idNameUser").val()
        }, function (data) {
            let diagrams = JSON.parse(data);
            fillTable(diagrams);
        });
    }
    

    function remove(diagram) {
        
        //alert("BTN pressed");
        $.get("../ServletDropDiagrams", {
            nameProject: diagram.nameProject,
            name_user: $("#idNameUser").val()
        }, function () {
            //alert("getActivities");
            getActivities();
        });
        
    }

    function edit(diagram) {
        $("#editModal").modal({
            keyboard: false
        });
        $("#NameEdit").val(diagram.nameProject);

        $("#saveEdit").click(function () {
            $("#editModal").modal("toggle");
            $.post("../ServletEditUser", {
                nameProject: diagram.nameProject,
                nameProject: $("#NameEdit").val()
            }, function () {
                getActivities();
            });
        });
    }
    
    getActivities();

})(jQuery);