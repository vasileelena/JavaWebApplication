<%@ page import="models.EmailPrimitTrimis" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: elena
  Date: 16.05.2020
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inbox</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"
</head>
<body>


<p align="left" method="post" action="/home">
    <%
        out.print("<form align=\"left\" method=\"post\" action=\"/conturi\">\n" +
                "    <input type=\"submit\" class=\"btn btn-danger\" name=\"buton\" value=\"Pagina principala\">\n" +
                "</form>");
        if(request.getAttribute("inbox") != null){
            List<EmailPrimitTrimis> inbox = (List<EmailPrimitTrimis>) request.getAttribute("inbox");
            out.print("<h1 align=\"center\" style=\"text-shadow:2px 2px 5px red\"> Inbox</h1>");
            if(inbox != null) {
                out.print("<table class=\"table table-striped\">" +
                        "<tr class=\"bg-danger\">" +
                        "<th>Trimis de</th>" +
                        "<th>Data</th>" +
                        "<th>Subiect</th>" +
                        "<th>Mesaj</th>" +
                        "<th></th>" +
                        "<th></th>" +
                        "<th></th></tr>");
                for(EmailPrimitTrimis email : inbox) {
                    out.print("<tr><td>" + email.getUsernamePrieten() + "</td>" +
                            "<td>" + email.getData() + "</td>" +
                            "<td>" + email.getSubiect() + "</td>" +
                            "<td>" + email.getMesaj() + "</td>" +
                            "<td>" +
                            "<form method=\"post\" action=\"/home\">" +
                            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + "uP="+email.getUsernamePrieten() +
                            "&s="+email.getSubiect() +
                            "&\" value=\"Reply\">"
                            + "</td>" +
                            "<td " +
                            "<form method=\"post\" action=\"/home\">" +
                            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + "uP="+email.getUsernamePrieten() +
                                    "&d="+email.getData() +
                                    "&s="+email.getSubiect() +
                                    "&m="+email.getMesaj() +
                                    "&\" value=\"Stergere email\">"
                            + "</td>" +
                            "<td>" +
                            "<form method=\"post\" action=\"/home\">" +
                            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + "uP="+email.getUsernamePrieten() +
                            "&d="+email.getData() +
                            "&s="+email.getSubiect() +
                            "&m="+email.getMesaj() +
                            "&\" value=\"Forward\">"
                            + "</td></tr>" +"</tr>");
                }
                out.print("</table>");
            }
            else
                out.print("Nu aveti niciun email!");
        }
        else if(request.getAttribute("sent") != null){
            List<EmailPrimitTrimis> sent = (List<EmailPrimitTrimis>) request.getAttribute("sent");
            out.print("<h1 align=\"center\" style=\"text-shadow:2px 2px 5px red\">Sent</h1>");
            if(sent != null) {
                out.print("<table class=\"table table-striped\">" +
                        "<tr class=\"bg-danger\">" +
                        "<th>Trimis de</th>" +
                        "<th>Data</th>" +
                        "<th>Subiect</th>" +
                        "<th>Mesaj</th>" +
                        "<th></th>" +
                        "<th></th></tr>");
                for(EmailPrimitTrimis email : sent) {
                    out.print("<tr><td>" + email.getUsernamePrieten() + "</td>" +
                            "<td>" + email.getData() + "</td>" +
                            "<td>" + email.getSubiect() + "</td>" +
                            "<td>" + email.getMesaj() + "</td>" +
                            "<td>" +
                            "<form method=\"post\" action=\"/home\">" +
                            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + "uP="+email.getUsernamePrieten() +
                            "&d="+email.getData() +
                            "&s="+email.getSubiect() +
                            "&m="+email.getMesaj() +
                            "&=\" value=\"Stergere email\">"
                            + "</td>" +
                            "<td>" +
                            "<form method=\"post\" action=\"/home\">" +
                            "    <input type=\"submit\" class=\"btn btn-dark\" name=\"" + "uP="+email.getUsernamePrieten() +
                            "&d="+email.getData() +
                            "&s="+email.getSubiect() +
                            "&m="+email.getMesaj() +
                            "&\" value=\"Forward\">"
                            + "</td></tr>" +"</tr>");
                }
                out.print("</table>");
            }
            else
                out.print("Nu aveti niciun email!");
        }
    %>
</p>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
