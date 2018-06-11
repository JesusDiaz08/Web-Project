(function ($) {

    'use strict';

    $("#btnAdd").click(function(){
        pop_ventana = window.open('registro.html','Registrar usuario','width=400,height=650');
        getActivities();
    });

    function fillTable(groups) {
        $("#table_groups > tbody").empty();
        groups.forEach(function (group) {
            $("#table_groups > tbody").append($("<tr>")
                .append($("<td>", {
                    text: group.nombre_gpo
                }), $("<td>").append($("<button>", {
                    class: 'btn btn-danger mr-2',
                    text: 'Eliminar'
                }).click(function () {
                    remove(user)
                }), $("<button>", {
                    class: 'btn btn-warning',
                    text: 'Editar'
                }).click(function () {
                    edit(user)
                }))
            ));
        });
    }

    function getUsersGroup(users) {
        $("#table_groups > tbody").empty();
        groups.forEach(function (group) {
            $("#table_groups > tbody").append($("<tr>")
                .append($("<td>", {
                    text: group.nombre_gpo
                }), $("<td>").append($("<button>", {
                    class: 'btn btn-danger mr-2',
                    text: 'Eliminar'
                }).click(function () {
                    remove(user)
                }), $("<button>", {
                    class: 'btn btn-warning',
                    text: 'Editar'
                }).click(function () {
                    edit(user)
                }))
            ));
        });
    }
    
       function getStudents(students) {
        $("#table_students > tbody").empty();
        students.forEach(function (student) {
            $("#table_students > tbody").append($("<tr>")
                .append($("<td>", {
                    text: student.id_user
                }), $("<td>").append($("<input type='checkbox'>", {
                    class: 'btn btn-danger mr-2',
                    text: 'Agregar/Eliminar'
                }).click(function () {
                    remove(user)
                })
            )));
        });
    }
      function getStd() {
        $.post("../Servlet_getStudents",
            function (data) {
                let students = JSON.parse(data);
                    getStudents(students);
            });
    }

    function getActivities() {
        $.get("../ServletGetGroups",
            function (data) {
                let groups = JSON.parse(data);
                fillTable(groups);
            });
    }
    

    function remove(group) {
        $.get("../ServletDropGroups", {
            name: group.name
        }, function () {
            getActivities();
        });
    }

    function edit(group) {
        getStd();
        $("#editModal").modal({
            keyboard: false
        });
        $("#Name").val(group.nombre_gpo);

        $("#saveEdit").click(function () {
            $("#editModal").modal("toggle");
            $.post("../ServletEditGroups", {
                nombre_gpo: group.nombre_gpo,
                nombre_gpo: $("#NameEdit").val(),
            }, function () {
                getActivities();
            });
        });
        
    }
    
    getActivities();

})(jQuery);