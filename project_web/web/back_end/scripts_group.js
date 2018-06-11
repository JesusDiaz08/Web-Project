(function ($) {

    'use strict';

    $("#btnAdd").click(function(){
        pop_ventana = window.open('registro.html','Registrar usuario','width=400,height=650');
        getActivities();
    });

    function fillTable(groups) {
        $("#table_groups > tbody").empty();
        users.forEach(function (group) {
            $("#table_groups > tbody").append($("<tr>").append($("<td>", {
                    text: user.id_user
                }), $("<td>", {
                    text: user.name
                }), $("<td>", {
                    text: user.id_email
                }), $("<td>", {
                    text: user.type_user
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


    function getActivities() {
        $.get("../ServletGetGroups",
            function (data) {
                let groups = JSON.parse(data);
                fillTable(groups);
            });
    }
    

    function remove(group) {
        $.get("../ServletDropGroup", {
            name: group.name
        }, function () {
            getActivities();
        });
    }

    function edit(group) {
        $("#editModal").modal({
            keyboard: false
        });
        $("#Name").val(group.name);

        $("#saveEdit").click(function () {
            $("#editModal").modal("toggle");
            $.post("../ServletEditGroup", {
                name: group.name,
                name: $("#NameEdit").val(),
            }, function () {
                getActivities();
            });
        });
    }
    
    getActivities();

})(jQuery);