<%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 13.05.2020
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prima pagina</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<style>
    body {

        background-image: url('background1.jpg');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;

    }

</style>

<h2 align="center">Bun venit!</h2>


<form align="center" method="post" action="/user">
    <input type="submit" class="btn btn-danger" name="buton" value="Sign in"><br>
    <input type="submit" class="btn btn-danger" name="buton" value="Log in">
</form>

</body>
</html>
