package servlets;

import models.ConturiUser;
import models.EmailPrimitTrimis;
import models.User;
import repositories.ConturiRepository;
import repositories.EmailRepository;
import repositories.UserRepository;
import utils.DbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Map<String, String> userMap = new HashMap<>();
        String line = req.getReader().readLine();
        line = URLDecoder.decode(line);

        String buton = null;
        System.out.println(line);
        if (line != null) {

                buton = line.split("=")[1];
                if (buton.equals("Imprieteneste-te!")) {
                    req.getRequestDispatcher("friend.jsp").forward(req, resp);
                }
                else if (buton.equals("Conturi")) {
                    req.getRequestDispatcher("cont.jsp").forward(req, resp);

                }
                else if (buton.equals("Stergere user")) {
                    List<String> conturi = ConturiRepository.afisareConturi();
                    req.setAttribute("deleteUser", conturi);
                    req.getRequestDispatcher("home.jsp").forward(req, resp);

                }
                else if (buton.equals("Accept")) {
                    req.setAttribute("delete", 1);
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                }
                else if (buton.equals("Cancel")) {
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                }
                else if (buton.equals("Log out")) {
                    UserServlet.setUsernameCurent(null);
                    UserServlet.setIdCurent(0);
                    req.getRequestDispatcher("firstpage.jsp").forward(req, resp);
                }
                else if (buton.equals("Trimite email")) {
                    req.getRequestDispatcher("email.jsp").forward(req, resp);
                }
                else if (buton.equals("Rapoarte")) {
                    req.getRequestDispatcher("rapoarte.jsp").forward(req, resp);
                }
                else if (buton.equals("Inbox")) {
                        List<EmailPrimitTrimis> inbox = null;
                        try {
                            inbox = EmailRepository.inbox();
                            req.setAttribute("inbox", inbox);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        req.getRequestDispatcher("inbox.jsp").forward(req, resp);
                }
                else if (buton.equals("Sent")) {
                    List<EmailPrimitTrimis> sent = null;
                    try {
                        sent = EmailRepository.sent();
                        req.setAttribute("sent", sent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.getRequestDispatcher("inbox.jsp").forward(req, resp);
                }
                else if (buton.equals("Pagina principala")) {
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                }
                else if (buton.equals("OK")) {
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                }
                else if (line.split("&")[0].split("=").length == 1 ) {

                    if(line.split("&")[1].split("=")[1].equals("Reply")) {
                        //mesaj gol la reply => mesaj=&buton=Reply
                        req.setAttribute("reply", 1);
                        req.setAttribute("eroare", "Mesajul nu poate fi gol!");
                        req.getRequestDispatcher("reply.jsp").forward(req, resp);
                    }
                    else {
                        //mesaj=&buton=Anulare
                        List<EmailPrimitTrimis> inbox = null;
                        try {
                            inbox = EmailRepository.inbox();
                            req.setAttribute("inbox", inbox);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        EmailRepository.setUsernamePrieten(null);
                        EmailRepository.setSubiect(null);
                        req.getRequestDispatcher("inbox.jsp").forward(req, resp);
                    }
                }


                else if (line.split("&")[0].split("=").length == 2 && line.split("&")[0].split("=")[0].equals("mesaj")){
                    //trimitere raspuns completat
                    buton = line.split("&")[1].split("=")[1];
                    if(buton.equals("Reply")) {
                        try {
                            String mesaj = line.split("&")[0].split("=")[1];
                            EmailRepository.sendEmail(EmailRepository.getUsernamePrieten(), EmailRepository.getSubiect(),
                                    mesaj);
                            req.setAttribute("ok", "Emailul a fost trimis!");
                            EmailRepository.setUsernamePrieten(null);
                            EmailRepository.setSubiect(null);
                            req.getRequestDispatcher("reply.jsp").forward(req, resp);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        EmailRepository.setUsernamePrieten(null);
                        EmailRepository.setSubiect(null);
                        List<EmailPrimitTrimis> inbox = null;
                        try {
                            inbox = EmailRepository.inbox();
                            req.setAttribute("inbox", inbox);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        req.getRequestDispatcher("inbox.jsp").forward(req, resp);
                    }
                }
                else if(line.split("&")[2].equals("=Reply")){ //buton din Inbox
                    req.setAttribute("reply", 1);
                    EmailRepository.setUsernamePrieten(line.split("&")[0].split("=")[1]);
                    EmailRepository.setSubiect(line.split("&")[1].split("=")[1]);
                    req.getRequestDispatcher("reply.jsp").forward(req, resp);
                }
                else if(line.split("&")[4].equals("=Stergere email")) {
                    EmailPrimitTrimis emailPrimitTrimis = new EmailPrimitTrimis(
                            line.split("&")[0].split("=")[1],
                            line.split("&")[1].split("=")[1],
                            line.split("&")[2].split("=")[1],
                            line.split("&")[3].split("=")[1]
                    );
                    try {
                        EmailRepository.deleteEmail(emailPrimitTrimis);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    List<EmailPrimitTrimis> inbox = null;
                    try {
                        inbox = EmailRepository.inbox();
                        req.setAttribute("inbox", inbox);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.getRequestDispatcher("inbox.jsp").forward(req, resp);
                }
                else if(line.split("&")[4].equals("==Stergere email")) {
                    EmailPrimitTrimis emailPrimitTrimis = new EmailPrimitTrimis(
                            line.split("&")[0].split("=")[1],
                            line.split("&")[1].split("=")[1],
                            line.split("&")[2].split("=")[1],
                            line.split("&")[3].split("=")[1]
                    );
                    try {
                        EmailRepository.deleteEmail(emailPrimitTrimis);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    List<EmailPrimitTrimis> sent = null;
                    try {
                        sent = EmailRepository.sent();
                        req.setAttribute("sent", sent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.getRequestDispatcher("inbox.jsp").forward(req, resp);
                }
                else if(line.split("&")[4].equals("=Forward")) {
                    System.out.println(line);
                    EmailRepository.setSubiect(line.split("&")[2].split("=")[1]);
                    EmailRepository.setMesaj(line.split("&")[3].split("=")[1]);

                    req.getRequestDispatcher("forward.jsp").forward(req, resp);
                }

        }

    }

}
