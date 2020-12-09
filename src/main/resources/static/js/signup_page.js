
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() !== "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })
    })


    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).addClass('active');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).removeClass('active');
            showPass = 0;
        }

    });

    $('#signUp_button').click(function () {
        var username = $('#username').val();
        var firstName = $('#firstName').val()
        var lastName = $('#lastName').val()
        var email = $('#email').val()
        var role = $('#role').val();
        var password = $('#password').val();
        console.log(username.pointerType)
        console.log(firstName)
        console.log(lastName)
        console.log(email)
        console.log(role)
        console.log(password)
        $.ajax({
            type:'POST',
            url:"http://localhost:8080/sign-up",
            dataType: 'json',
            contentType: 'application/json',
            data:{
                username: "ali",
                firstName: firstName.toString(),
                lastName: lastName.toString(),
                email: email.toString(),
                userType: role.toString(),
                password: password.toString()
            },
            success:function () {
                console.log("success")
                $("body").replaceWith("<h1> Success sign-up.Please wait for confirm</h1>" )
            },
            error:function (data) {
                console.log("error1")
               // $("body").replaceWith("<h1>" + data.message + "</h1>")
            }
        })
    })


})(jQuery);