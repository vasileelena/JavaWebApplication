<%@ page import="java.util.List" %>
<%@ page import="repositories.PrieteniRepository" %>
<%@ page import="servlets.UserServlet" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 15.05.2020
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trimite email</title>
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

<p align="left">
    <%
        List<String> prieteni = PrieteniRepository.friends(UserServlet.getUsernameCurent());
        if(prieteni != null) {
            out.print("<br>");
            for (String prieten : prieteni)
                out.print("<h3 align=\"left\">" + prieten + "</h3>\n" + "<form align=\"left\" method=\"post\" action=\"/email\">\n" +
                        "    <input type=\"submit\" class=\"btn btn-warning\" name=\"" + prieten + "\" value=\"Trimite email\"><br>\n" +
                        "</form>");
            out.print("<br>");
            out.print("<form align=\"left\" method=\"post\" action=\"/email\">\n" +
                    "    <input type=\"submit\" class=\"btn btn-dark btn-lg\"  name=\"buton\" value=\"Inapoi\">\n" +
                    "</form>");
        }
    %>
</p>

<p align="right">
    <%
        if(request.getAttribute("sendEmail") != null) {

            //String usernamePrieten = (String) request.getAttribute("sendEmail");
                out.print("<form align=\"right\" method=\"post\" action=\"/email\">\n" +
                        "    <label for=\"name\" style=\"color:yellow\"><h2>Subiect: </h2></label> <br>\n" +
                        "    <input type=\"text\" id=\"name\" name=\"subiect\"><br>\n" +
                        "    <label for=\"name\" style=\"color:yellow\"><h2>Mesaj: </h2></label> <br>\n" +
                        "    <input type=\"text\" name=\"mesaj\"><br>\n" +
                        "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Trimite\">\n" +
                        "    <input type=\"submit\" class=\"btn btn-dark\" name=\"buton\" value=\"Anulare\">\n" +
                        "</form>");
            out.print("<br>");
        }
        if(request.getAttribute("eroare") != null) {
            out.print("<h2>" + request.getAttribute("eroare") + "</h2>");
        }
    %>
</p>

<p align="right">
    <%
        if(request.getAttribute("trimis") != null) {

            out.print("<h2>" + request.getAttribute("trimis"));
            out.print("</h2><br>");
        }

    %>
</p>

</body>
</html>
