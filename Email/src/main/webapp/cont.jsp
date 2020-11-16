<%@ page import="java.util.List" %>
<%@ page import="repositories.ConturiRepository" %>
<%@ page import="servlets.UserServlet" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 13.05.2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Conturi</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<style>
    body {

        background-image: url('background4.jpg');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;

    }

</style>

<p align="center">
    <%
        out.print("<form align=\"left\" method=\"post\" action=\"/conturi\">\n" +
                "    <input type=\"submit\" class=\"btn btn-dark btn-lg\" name=\"buton\" value=\"Pagina principala\">\n" +
                "</form>");
        out.print("<h3 align=\"center\"> Sunteti conectat pe contul " + UserServlet.getUsernameCurent() + "</h3>");
        List<String> conturi = ConturiRepository.afisareConturi();
        if(conturi != null) {
            out.print("<br>");
            for (String cont : conturi)
                out.print("<h3 align=\"center\">" + cont + "</h3>\n" + "<form align=\"center\" method=\"post\" action=\"/conturi\">\n" +
                                "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + cont + "\" value=\"Intra in cont\"><br>\n" +
                                "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + cont + "\" value=\"Sterge cont\"><br>\n" +
                                "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + cont + "\" value=\"Prieteni\"><br>\n" +
                        "</form>");
            out.print("<br>");
            out.print("<form align=\"center\" method=\"post\" action=\"/conturi\">\n" +
                    "    <input type=\"submit\" class=\"btn btn-dark btn-lg\" name=\"buton\" value=\"Adaugare cont\">\n" +
                    "</form>");
        }
    %>
</p>

<p align="center">
    <%
        if(request.getAttribute("schimbCont") != null) {
            out.print("<form align=\"center\" method=\"post\" action=\"/conturi\">\n" +
                    "    <label for=\"name\"><h4>Parola: </h4></label> <br>\n" +
                    "    <input type=\"password\" name=\"parola\"><br>\n" +
                    "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Schimba cont\">\n" +
                    "</form>");
        }
        if(request.getAttribute("empty2") != null)
            out.print("<h4>" + request.getAttribute("empty2") + "</h4>");
        if(request.getAttribute("parola incorecta") != null)
            out.print("<h4>" + request.getAttribute("parola incorecta") + "</h4>");
    %>
</p>

<p align="center">
    <%
        if(request.getAttribute("friends") != null) {
            List<String> prieteni = (List<String>) request.getAttribute("friends");
            out.print("<h2> Lista prieteni " + request.getAttribute("username") + ":<br>");
            for(String cont : prieteni)
                out.print(cont + "<br>");
            out.print("</h2>");
        }
        else if (request.getAttribute("nofriends") != null)
            out.print(request.getAttribute("nofriends"));
    %>
</p>



<p align="center">
    <%
        if(request.getAttribute("sters") != null) {
                String mesaj = (String) request.getAttribute("sters");
                out.print("<h4>" + mesaj + "</h4><br>");

                if(request.getAttribute("cont").equals(UserServlet.getUsernameCurent())) {
                    UserServlet.setUsernameCurent(null);
                    UserServlet.setIdCurent(0);
                    out.print("<h4>Ati sters contul pe care erati logat. Veti fi redirectionar la pagina de Log in.</h4>");
                    out.print("<form align=\"center\" method=\"post\" action=\"/conturi\">\n" +
                            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"OK\">\n" +
                            "</form>");
                }
        }

    %>

</p>

<p align="center">
    <%
        if (request.getAttribute("singurulCont") != null) {
            out.print("<h4>" + request.getAttribute("singurulCont") + "</h4>");
            out.print("<form align=\"center\" method=\"post\" action=\"/conturi\">\n" +
            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Accept\">\n" +
            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Cancel\">\n" +
                    "</form>");
        }
    %>
</p>

<p align="center">
    <%
        if(request.getAttribute("add") != null) {
            out.print("<form align=\"center\" method=\"post\" action=\"/conturi\">\n" +
                    "    <label for=\"name\"><h4>Username: </h4></label> <br>\n" +
                    "    <input type=\"text\" id=\"name\" name=\"username\"><br>\n" +
                    "    <label for=\"name\"><h4>Parola: </h4></label> <br>\n" +
                    "    <input type=\"password\" name=\"parola\"><br>\n" +
                    "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Creeaza cont\">\n" +
                    "</form>");
        }
        if(request.getAttribute("empty") != null)
            out.print("<h4>" + request.getAttribute("empty") + "</h4>");
        if(request.getAttribute("saved") != null)
            out.print("<h4>" + request.getAttribute("saved") + "</h4>");
        if(request.getAttribute("usernameExistent") != null)
            out.print("<h4>" + request.getAttribute("usernameExistent") + "</h4>");
    %>
</p>

</body>
</html>
