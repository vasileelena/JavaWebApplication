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

@WebServlet("/email")
public class EmailServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.getRequestDispatcher("email.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        String line = req.getReader().readLine();
        line = URLDecoder.decode(line);

        String buton = null;
        if (line != null) {
            System.out.println(line);
            if (line.split("=")[1].equals("Trimite email")) { //x=Trimite email
                String usernamePrieten = line.split("=")[0];
                req.setAttribute("sendEmail", usernamePrieten);
                EmailRepository.setUsernamePrieten(usernamePrieten);
                req.getRequestDispatcher("email.jsp").forward(req, resp);
            }
            else if (line.split("=")[1].equals("Forward email")) {
                String usernamePrieten = line.split("=")[0];
                System.out.println(usernamePrieten);
                req.setAttribute("forward", usernamePrieten);
                try {
                    EmailRepository.sendEmail(usernamePrieten, EmailRepository.getSubiect(), EmailRepository.getMesaj());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("forward.jsp").forward(req, resp);
            }
            else if (line.split("=")[1].equals("Inapoi")) { //buton=Inapoi
                req.setAttribute("sendEmail", null);
                EmailRepository.setUsernamePrieten(null);
                EmailRepository.setSubiect(null);
                EmailRepository.setMesaj(null);
                req.setAttribute("eroare", null);
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
            else if (line.split("=")[1].equals("Pagina principala")) {
                req.setAttribute("zi", null);
                req.setAttribute("prieteni", null);
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
            else if (line.split("=")[1].equals("Cea mai aglomerata zi")) {
                List<String> days = null;
                try {
                    days = EmailRepository.raportZi();
                    req.setAttribute("zi", days);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                req.getRequestDispatcher("rapoarte.jsp").forward(req, resp);
            }

            else if (line.split("=")[1].equals("Cu cine ai interactionat cel mai mult")) {
                List<String> friends = null;
                try {
                    friends = EmailRepository.raportPrieteni();
                    req.setAttribute("prieteni", friends);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                req.getRequestDispatcher("rapoarte.jsp").forward(req, resp);
            }

            else if (line.split("&")[2].split("=")[1].equals("Trimite")) { //subict=xs&mesaj=xsc&buton=Trimite

                if (line.split("&")[0].split("=").length == 2 && line.split("&")[1].split("=").length == 2) {

                    String subiect = line.split("&")[0].split("=")[1];
                    String mesaj = line.split("&")[1].split("=")[1];

                    try {

                        EmailRepository.sendEmail(EmailRepository.getUsernamePrieten(), subiect, mesaj);
                        req.setAttribute("trimis", "Emailul a fost trimis cu succes!");
                        EmailRepository.setUsernamePrieten(null);
                        req.getRequestDispatcher("email.jsp").forward(req, resp);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    req.setAttribute("eroare", "Subiectul sau mesajul nu pot fi necompletate! ");
                    req.setAttribute("sendEmail", EmailRepository.getUsernamePrieten());
                    req.getRequestDispatcher("email.jsp").forward(req, resp);
                }
            }
            else if(line.split("&")[2].split("=")[1].equals("Anulare")){
                //subict=&mesaj=&buton=Anulare
                req.setAttribute("sendEmail", null);
                EmailRepository.setUsernamePrieten(null);
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }


        }



    }

}
