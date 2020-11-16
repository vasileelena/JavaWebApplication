<%@ page import="models.ConturiUser" %>
<%@ page import="servlets.UserServlet" %>
<%@ page import="servlets.PrieteniServlet" %>
<%@ page import="repositories.PrieteniRepository" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 13.05.2020
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friends</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<style>
    body {

        background-image: url('friendbackground.jpg');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;

    }

</style>

<h1 align="center">Introduceti username-ul:</h1>

<form align="center" method="post" action="/prieteni">
    <input type="text" name="username"><br><br>
    <input type="submit" class="btn btn-info" value="Imprieteneste-te"><br>
    <input type="submit" class="btn btn-info" name="buton" value="Inapoi">
</form>

<p align="center">
    <%

        if(request.getAttribute("eroare") != null) {
            String mesaj = (String) request.getAttribute("eroare");
            out.print("<h3 align=\"center\">" + mesaj + "</h3>");
        } else if(request.getAttribute("username") != null ){
            String username = (String) request.getAttribute("username");
            String usernameCurent = UserServlet.getUsernameCurent();

            boolean saved = false;

            if(!PrieteniRepository.findFriends(usernameCurent, username)) {

                PrieteniRepository.save(usernameCurent, username);
                saved = true;
            }
            if(!PrieteniRepository.findFriends(username, usernameCurent)) {

                PrieteniRepository.save(username, usernameCurent);
                saved = true;
            }

            if(saved)
                out.print("<h3 align=\"center\"> Esti acum prieten cu " + username + "</h3>" );
            else
                out.print("<h3 align=\"center\"> Esti deja prieten cu " + username + " pe contul curent! </h3>");
        }
    %>
</p>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
