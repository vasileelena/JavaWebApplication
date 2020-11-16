<%@ page import="repositories.EmailRepository" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 18.05.2020
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reply</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<style>
    body {

        background-image: url('replybackground3.png');
        background-repeat: no-repeat;
        background-attachment: initial;
        background-size: 100% 100%;

    }

</style>

<p align="left">
    <%
        if(request.getAttribute("reply") != null) {
            //mesaj=x&buton=Reply
            out.print("<h3>    Reply to: " +  EmailRepository.getUsernamePrieten() +
                    "<br>     Subiect: " + EmailRepository.getSubiect() + "</h3><br>");
            out.print("<form align=\"left\" method=\"post\" action=\"/home\">\n" +
                    "    <label for=\"name\"><h4>    Mesaj: </h4></label> <br>\n" +
                    "    <input type=\"text\" name=\"mesaj\"><br><br>\n" +
                    "    <input type=\"submit\" class=\"btn btn-success\" name=\"buton\" value=\"Reply\">\n" +
                    "    <input type=\"submit\" class=\"btn btn-success\" name=\"buton\" value=\"Anulare\">\n" +
                    "</form>");
            out.print("<br>");
        }
        if(request.getAttribute("eroare") != null) {
            request.setAttribute("reply", 1);
            out.print(request.getAttribute("eroare"));
        }
        else if(request.getAttribute("ok") != null) {
            out.print("<h3 align=\"center\">" +request.getAttribute("ok") + "</h3>");
            out.print("<form align=\"center\" method=\"post\" action=\"/home\">\n" +
                    "    <input type=\"submit\" class=\"btn btn-success\" name=\"buton\" value=\"OK\">\n" +
                    "</form>");
        }
    %>
</p>

</body>
</html>
