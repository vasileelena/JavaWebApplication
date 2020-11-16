<%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 12.05.2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inregistreaza-te:</title>
</head>
<body>


<%--<form align="center" method="post" action="/user">--%>
<%--    <label for="name">Nume: </label> <br>--%>
<%--    <input type="text" id="name" name="nume"><br>--%>
<%--    <label for="name">Prenume: </label> <br>--%>
<%--    <input type="text" name="prenume"><br>--%>
<%--    <label for="name">Username: </label> <br>--%>
<%--    <input type="text" name="username"><br>--%>
<%--    <label for="name">Parola: </label> <br>--%>
<%--    <input type="password" name="parola"><br>--%>
<%--    <input type="submit" name="buton" value="Sign in">--%>
<%--</form>--%>

<p align="center">
    <%
        if(request.getAttribute("saved") != null){
            out.print(request.getAttribute("saved"));
        }
        else if(request.getAttribute("unsaved") != null){
            out.print(request.getAttribute("unsaved"));
        }
        if(request.getAttribute("eroareSignin") != null){
            out.print(request.getAttribute("eroareSignin"));
        }
    %>
</p>

<div class="panda">
    <div class="ear"></div>
    <div class="face">
        <div class="eye-shade"></div>
        <div class="eye-white">
            <div class="eye-ball"></div>
        </div>
        <div class="eye-shade rgt"></div>
        <div class="eye-white rgt">
            <div class="eye-ball"></div>
        </div>
        <div class="nose"></div>
        <div class="mouth"></div>
    </div>
    <div class="body"> </div>

</div>
<form method="post" action="/user">
    <div class="hand"></div>
    <div class="hand rgt"></div>
    <h1>Inregistreaza-te: </h1>
    <div class="form-group">
        <input type="text" id="name" name="nume" class="form-control"/>
        <label for="name" class="form-label">Nume: </label>
    </div>
    <div class="form-group">
        <input type="text" name="prenume" class="form-control"/>
        <label for="name" class="form-label">Prenume: </label>
    </div>
    <div class="form-group">
        <input type="text" name="username" class="form-control"/>
        <label for="name" class="form-label">Username: </label>
    </div>
    <div class="form-group">
        <input type="password" name="parola" class="form-control"/>
        <label for="name" class="form-label">Parola: </label>
        <input type="submit" class="btn btn-dark" name="buton" value="Sign in">
    </div>
</form>

<style type="text/css">

    @import url(https://fonts.googleapis.com/css?family=Dancing+Script|Roboto);
    , :after, *:before {
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    body {
        background: #dcdcdc;
        text-align: center;
        font-family: 'Roboto', sans-serif;
    }

    .panda {
        position: relative;
        width: 200px;
        margin: 50px auto;
    }

    .face {
        width: 200px;
        height: 200px;
        background: #fff;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
        margin: 50px auto;
        -moz-box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
        -webkit-box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
        box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
        z-index: 50;
        position: relative;
    }

    .ear, .ear:after {
        position: absolute;
        width: 80px;
        height: 80px;
        background: #000;
        z-index: 5;
        border: 10px solid #fff;
        left: -15px;
        top: -15px;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
    }
    .ear:after {
        content: '';
        left: 125px;
    }

    .eye-shade {
        background: #000;
        width: 50px;
        height: 80px;
        margin: 10px;
        position: absolute;
        top: 35px;
        left: 25px;
        -moz-transform: rotate(220deg);
        -ms-transform: rotate(220deg);
        -webkit-transform: rotate(220deg);
        transform: rotate(220deg);
        -moz-border-radius: 25px/20px 30px 35px 40px;
        -webkit-border-radius: 1.25;
        border-radius: 25px/20px 30px 35px 40px;
    }
    .eye-shade.rgt {
        -moz-transform: rotate(140deg);
        -ms-transform: rotate(140deg);
        -webkit-transform: rotate(140deg);
        transform: rotate(140deg);
        left: 105px;
    }

    .eye-white {
        position: absolute;
        width: 30px;
        height: 30px;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
        background: #fff;
        z-index: 500;
        left: 40px;
        top: 80px;
        overflow: hidden;
    }
    .eye-white.rgt {
        right: 40px;
        left: auto;
    }

    .eye-ball {
        position: absolute;
        width: 0px;
        height: 0px;
        left: 20px;
        top: 20px;
        max-width: 10px;
        max-height: 10px;
        -moz-transition: 0.1s;
        -o-transition: 0.1s;
        -webkit-transition: 0.1s;
        transition: 0.1s;
    }
    .eye-ball:after {
        content: '';
        background: #000;
        position: absolute;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
        right: 0;
        bottom: 0px;
        width: 20px;
        height: 20px;
    }

    .nose {
        position: absolute;
        height: 20px;
        width: 35px;
        bottom: 40px;
        left: 0;
        right: 0;
        margin: auto;
        -moz-border-radius: 50px 20px/30px 15px;
        -webkit-border-radius: 50px;
        border-radius: 50px 20px/30px 15px;
        -moz-transform: rotate(15deg);
        -ms-transform: rotate(15deg);
        -webkit-transform: rotate(15deg);
        transform: rotate(15deg);
        background: #000;
    }

    .body {
        background: #fff;
        position: absolute;
        top: 200px;
        left: -20px;
        -moz-border-radius: 100px 100px 100px 100px/126px 126px 96px 96px;
        -webkit-border-radius: 100px;
        border-radius: 100px 100px 100px 100px/126px 126px 96px 96px;
        width: 250px;
        height: 282px;
        -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
    }

    .hand, .hand:after, .hand:before {
        width: 40px;
        height: 30px;
        -moz-border-radius: 50px;
        -webkit-border-radius: 50px;
        border-radius: 50px;
        -moz-box-shadow: 0 2px 3px rgba(0, 0, 0, 0.15);
        -webkit-box-shadow: 0 2px 3px rgba(0, 0, 0, 0.15);
        box-shadow: 0 2px 3px rgba(0, 0, 0, 0.15);
        background: #000;
        margin: 5px;
        position: absolute;
        top: 70px;
        left: -25px;
    }
    .hand:after, .hand:before {
        content: '';
        left: -5px;
        top: 11px;
    }
    .hand:before {
        top: 26px;
    }
    .hand.rgt, .rgt.hand:after, .rgt.hand:before {
        left: auto;
        right: -25px;
    }
    .hand.rgt:after, .hand.rgt:before {
        left: auto;
        right: -5px;
    }

    .foot {
        top: 360px;
        left: -80px;
        position: absolute;
        background: #000;
        z-index: 1400;
        -moz-box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
        -webkit-box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
        box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
        -moz-border-radius: 40px 40px 39px 40px/26px 26px 63px 63px;
        -webkit-border-radius: 40px;
        border-radius: 40px 40px 39px 40px/26px 26px 63px 63px;
        width: 82px;
        height: 120px;
    }
    .foot:after {
        content: '';
        width: 55px;
        height: 65px;
        background: #222;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
        position: absolute;
        bottom: 10px;
        left: 0;
        right: 0;
        margin: auto;
    }
    .foot .finger, .foot .finger:after, .foot .finger:before {
        position: absolute;
        width: 25px;
        height: 35px;
        background: #222;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
        top: 10px;
        right: 5px;
    }
    .foot .finger:after, .foot .finger:before {
        content: '';
        right: 30px;
        width: 20px;
        top: 0;
    }
    .foot .finger:before {
        right: 55px;
        top: 5px;
    }
    .foot.rgt {
        left: auto;
        right: -80px;
    }
    .foot.rgt .finger, .foot.rgt .finger:after, .foot.rgt .finger:before {
        left: 5px;
        right: auto;
    }
    .foot.rgt .finger:after {
        left: 30px;
        right: auto;
    }
    .foot.rgt .finger:before {
        left: 55px;
        right: auto;
    }

    form {
        display: none;
        max-width: 400px;
        padding: 20px 40px;
        background: #fff;
        height: 300px;
        margin: auto;
        display: block;
        -moz-box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
        -webkit-box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
        box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
        -moz-transition: 0.3s;
        -o-transition: 0.3s;
        -webkit-transition: 0.3s;
        transition: 0.3s;
        position: relative;
        -moz-transform: translateY(-100px);
        -ms-transform: translateY(-100px);
        -webkit-transform: translateY(-100px);
        transform: translateY(-100px);
        z-index: 500;
        border: 1px solid #eee;
    }
    form.up {
        -moz-transform: translateY(-180px);
        -ms-transform: translateY(-180px);
        -webkit-transform: translateY(-180px);
        transform: translateY(-180px);
    }

    h1 {
        color: #cc0000;
        font-family: 'Dancing Script', cursive;
    }

    .btn {
        background: #fff;
        padding: 5px;
        width: 150px;
        height: 35px;
        border: 1px solid #cc0000;
        margin-top: 25px;
        cursor: pointer;
        -moz-transition: 0.3s;
        -o-transition: 0.3s;
        -webkit-transition: 0.3s;
        transition: 0.3s;
        -moz-box-shadow: 0 50px #cc0000 inset;
        -webkit-box-shadow: 0 50px #cc0000 inset;
        box-shadow: 0 50px #cc0000 inset;
        color: #fff;
    }
    .btn:hover {
        -moz-box-shadow: 0 0 #cc0000 inset;
        -webkit-box-shadow: 0 0 #cc0000 inset;
        box-shadow: 0 0 #cc0000 inset;
        color: #cc0000;
    }
    .btn:focus {
        outline: none;
    }

    .form-group {
        position: relative;
        font-size: 15px;
        color: #666;
    }
    .form-group + .form-group {
        margin-top: 30px;
    }
    .form-group .form-label {
        position: absolute;
        z-index: 1;
        left: 0;
        top: 5px;
        -moz-transition: 0.3s;
        -o-transition: 0.3s;
        -webkit-transition: 0.3s;
        transition: 0.3s;
    }
    .form-group .form-control {
        width: 100%;
        position: relative;
        z-index: 3;
        height: 35px;
        background: none;
        border: none;
        padding: 5px 0;
        -moz-transition: 0.3s;
        -o-transition: 0.3s;
        -webkit-transition: 0.3s;
        transition: 0.3s;
        border-bottom: 1px solid #777;
        color: #555;
    }
    .form-group .form-control:invalid {
        outline: none;
    }
    .form-group .form-control:focus, .form-group .form-control:valid {
        outline: none;
        -moz-box-shadow: 0 1px #cc0000;
        -webkit-box-shadow: 0 1px #cc0000;
        box-shadow: 0 1px #cc0000;
        border-color: #cc0000;
    }
    .form-group .form-control:focus + .form-label, .form-group .form-control:valid + .form-label {
        font-size: 12px;
        color: #cc0000;
        -moz-transform: translateY(-15px);
        -ms-transform: translateY(-15px);
        -webkit-transform: translateY(-15px);
        transform: translateY(-15px);
    }

    .alert {
        position: absolute;
        color: #f00;
        font-size: 16px;
        right: -180px;
        top: -300px;
        z-index: 200;
        padding: 30px 25px;
        background: #fff;
        -moz-box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
        -webkit-box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
        box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
        opacity: 0;
        -moz-transform: scale(0, 0);
        -ms-transform: scale(0, 0);
        -webkit-transform: scale(0, 0);
        transform: scale(0, 0);
        -moz-transition: linear 0.4s 0.6s;
        -o-transition: linear 0.4s 0.6s;
        -webkit-transition: linear 0.4s;
        -webkit-transition-delay: 0.6s;
        transition: linear 0.4s 0.6s;
    }
    .alert:after, .alert:before {
        content: '';
        position: absolute;
        width: 25px;
        height: 25px;
        background: #fff;
        left: -19px;
        bottom: -8px;
        -moz-box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        -webkit-box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
    }
    .alert:before {
        width: 15px;
        height: 15px;
        left: -35px;
        bottom: -25px;
    }

    .wrong-entry {
        -moz-animation: wrong-log 0.3s;
        -webkit-animation: wrong-log 0.3s;
        animation: wrong-log 0.3s;
    }
    .wrong-entry .alert {
        opacity: 1;
        -moz-transform: scale(1, 1);
        -ms-transform: scale(1, 1);
        -webkit-transform: scale(1, 1);
        transform: scale(1, 1);
    }

    @-moz-keyframes eye-blink {
        to {
            height: 30px;
        }
    }
    @-webkit-keyframes eye-blink {
        to {
            height: 30px;
        }
    }
    @keyframes eye-blink {
        to {
            height: 30px;
        }
    }
    @-moz-keyframes wrong-log {
        0%, 100% {
            left: 0px;
        }
        20% , 60% {
            left: 20px;
        }
        40% , 80% {
            left: -20px;
        }
    }
    @-webkit-keyframes wrong-log {
        0%, 100% {
            left: 0px;
        }
        20% , 60% {
            left: 20px;
        }
        40% , 80% {
            left: -20px;
        }
    }
    @keyframes wrong-log {
        0%, 100% {
            left: 0px;
        }
        20% , 60% {
            left: 20px;
        }
        40% , 80% {
            left: -20px;
        }
    }

</style>

<script>
    $('#password').focusin(function(){
        $('form').addClass('up')
    });
    $('#password').focusout(function(){
        $('form').removeClass('up')
    });


    // Panda Eye move
    $(document).on( "mousemove", function( event ) {
        var dw = $(document).width() / 15;
        var dh = $(document).height() / 15;
        var x = event.pageX/ dw;
        var y = event.pageY/ dh;
        $('.eye-ball').css({
            width : x,
            height : y
        });
    });

    // validation


    $('.btn').click(function(){
        $('form').addClass('wrong-entry');
        setTimeout(function(){
            $('form').removeClass('wrong-entry');
        },3000 );
    });
</script>

</body>
</html>


