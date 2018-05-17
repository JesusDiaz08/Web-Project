function validar() {
    var name, last_name, email, user_name, password, expression;
    name = document.getElementById('name').value;
    last_name = document.getElementById('last_name').value;
    user_name = document.getElementById('user_name').value;
    email = document.getElementById('email').value;
    password = document.getElementById('password').value;
    
    /*Verify email by Regular Expression*/
    expression = /\w+@+[a-z]+\.+[a-z]/;
    /*----------------------------------
     * \w: Indica que debe haber un texto o número
     * @: especificando la separación para el dominio
     * [a-z]: escribir el dominio
     * .: separador para el tipo: .com, .edu, etc
     * [a-z]: tipo
     * */
    if (name==="" || last_name==="" || user_name==="" || email==="" || password===""){
        alert("Todos los campos son obligatorios");
        return false;
    }else if(name.length > 30){
        alert("El nombre es muy largo");
        return false;
    }else if(last_name.length>40){
        alert("El apellido es muy largo");
        return false;
    }else if(email.length>100){
        alert("El correo es muy largo");
        return false;
    }else if(!expression.test(email)){
        alert("El correo no es válido");
        return false;
    }else if(user_name.length>20 || password.length>20){
        alert("El usuario y la clave solo deben tener 20 caracteres como máximo");
        return false;
    }   
}