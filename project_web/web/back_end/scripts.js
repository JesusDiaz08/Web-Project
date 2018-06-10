(function ($) {

    'use strict';


    function fillTable(users) {
        $("#table > tbody").empty();
        users.forEach(function (user) {
            $("#table > tbody").append($("<tr>").append($("<td>", {
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
        $.get("../ServletGetUsers",
            function (data) {
                let users = JSON.parse(data);
                fillTable(users);
            });
    }

    function remove(user) {
        $.get("../ServletDropUser", {
            id_email: user.id_email
        }, function () {
            getActivities();
        });
    }

    function edit(user) {
        $("#editModal").modal({
            keyboard: false
        });
        $("#UserEdit").val(user.id_user);
        $("#NameEdit").val(user.name);
        $("#LastNameEdit").val(user.last_name);
        $("#EmailEdit").val(user.id_email);
        $("#PasswordEdit").val(user.password);
        $("#TypeEdit").val(user.type_user);

        $("#saveEdit").click(function () {
            $("#editModal").modal("toggle");
            $.post("../ServletEditUser", {
                id_user: user.id_user,
                name: $("#NameEdit").val(),
                last_name: $("#LastNameEdit").val(),
                id_email: $("#EmailEdit").val(),
                password: $("#PasswordEdit").val(),
                type_user: $("#TypeEdit").val()
            }, function () {
                getActivities();
            });
        });
    }
    getActivities();

})(jQuery);