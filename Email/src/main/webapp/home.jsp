<%@ page import="java.util.List" %>
<%@ page import="repositories.ConturiRepository" %>
<%@ page import="servlets.UserServlet" %>
<%@ page import="repositories.UserRepository" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 13.05.2020
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<style>
    body {

        background-image: url('background1.jpg');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;

    }

</style>
<form align="left" method="post" action="/home">
    <input type="submit" class="btn btn-danger" name="buton" value="Imprieteneste-te!"><br>
    <input type="submit" class="btn btn-danger" name="buton" value="Trimite email"><br>
    <input type="submit" class="btn btn-danger" name="buton" value="Inbox"><br>
    <input type="submit" class="btn btn-danger" name="buton" value="Sent"><br>
</form>

<%
    List<String> user = UserRepository.findUser();
    out.print("<h2 align=\"right\">" + user.get(0) + " " + user.get(1) + "  </h2>");
%>


<form align="right" method="post" action="/home">
    <input type="submit" class="btn btn-primary" name="buton" value="Rapoarte"><br>
    <input type="submit" class="btn btn-primary" name="buton" value="Conturi"><br>
    <input type="submit" class="btn btn-primary" name="buton" value="Stergere user"><br>
    <input type="submit" class="btn btn-primary" name="buton" value="Log out"><br>
</form>

<p align="center">
    <%
        if (request.getAttribute("deleteUser") != null) {
            List<String> conturi = (List<String>) request.getAttribute("deleteUser");
            out.print("Conturile dvs. sunt: " + conturi + "<br> Sunteti sigur ca doriti sa stergeti userul?<br>");
            out.print("<form align=\"center\" method=\"post\" action=\"/home\">\n" +
                    "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Accept\">\n" +
                    "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Cancel\">\n" +
                    "</form>");
        }
        if(request.getAttribute("delete") != null) {
            System.out.println("deleteuser");
            List<String> conturi = ConturiRepository.afisareConturi();
            for(String cont : conturi) {
                System.out.println(cont);
                UserServlet.setUsernameCurent(cont);
                ConturiRepository.delete(cont);
            }
            ConturiRepository.deleteUser();
            UserServlet.setIdCurent(0);

            request.getRequestDispatcher("firstpage.jsp").forward(request, response);
        }
    %>
</p>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
