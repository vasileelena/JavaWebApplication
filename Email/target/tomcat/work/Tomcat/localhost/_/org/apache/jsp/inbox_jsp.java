/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-05-20 12:10:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.EmailPrimitTrimis;
import java.util.List;

public final class inbox_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Inbox</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<p align=\"left\" method=\"post\" action=\"/home\">\r\n");
      out.write("    ");

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
    
      out.write("\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
