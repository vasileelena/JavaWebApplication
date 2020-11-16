<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 16.05.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rapoarte</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<style>
    body {

        background-image: url('rapoartebackground1.jpg');
        background-repeat: no-repeat;
        background-attachment: initial;
        background-size: 100% 110%;

    }

</style>

<form align="left" method="post" action="/email">
    <input type="submit" class="btn btn-danger btn-lg" name="buton" value="Cea mai aglomerata zi">
    <input type="submit" class="btn btn-danger btn-lg" name="buton" value="Cu cine ai interactionat cel mai mult"><br><br>
    <input type="submit" class="btn btn-danger" name="buton" value="Pagina principala">
</form>

<p align="center" >
    <%
        if(request.getAttribute("zi") != null){
            List<String> days = (List<String>) request.getAttribute("zi");
            if(days != null) {
                out.print("<h1> Zilele cele mai aglomerate: <br>");
                for (String day : days)
                    out.print(day + "<br>");
                out.print("</h1>");
            }
            else out.print("<h1> Nu aveti email-uri in cont.</h1>");

        }

        if(request.getAttribute("prieteni") != null){
            List<String> friends = (List<String>) request.getAttribute("prieteni");
            if(friends != null) {
                out.print("<h2> Persoana/persoanele cu care ati interactionat cel mai mult: <br>");
                for (String friend : friends)
                    out.print(friend + "<br>");
                out.print("</h1>");
            }
            else out.print("<h1> Nu aveti email-uri in cont.</h2>");
        }
    %>
</p>

</body>
</html>
