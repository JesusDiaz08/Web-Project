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
                }), $("<form action='../ServletGetJSONRTF' method='POST'>")
                                    .append($("<input type='hidden' value='"+diagram.JSON+"' id='vJSON' name='JSON'>"), $("<input type='hidden' value='"+diagram.RTF+"' name='RTF'>"),
                                           $("<input type='submit' class='btn btn-primary'>")) )
            ));
            alert(diagram.JSON);
        });
    }

    function showDiagram(diagram){
        $.post("..ServletGetJSONRTF", {
            JSON: diagram.JSON,
            RTF: diagram.RTF
        });
        //$.post("../Servlet_student_diagram", {
            //JSON: diagram.JSON;
            //RTF: diagram.RTF;
            //alert(diagram.JSON);
            //alert(diagram.RTF);
            //window.open('../Servlet_student_diagram?JSON='+diagram.JSON+'&RTF='+diagram.RTF);
        //});
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
            alert("getActivities");
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