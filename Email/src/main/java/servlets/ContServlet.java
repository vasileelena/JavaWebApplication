package servlets;

import models.ConturiUser;
import models.User;
import repositories.ConturiRepository;
import repositories.PrieteniRepository;
import repositories.UserRepository;
import utils.DbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/conturi")
public class ContServlet extends HttpServlet {

    private static String schimbareCont;

    public static String getSchimbareCont() {
        return schimbareCont;
    }

    public static void setSchimbareCont(String schimbareCont) {
        ContServlet.schimbareCont = schimbareCont;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.getRequestDispatcher("cont.jsp").forward(req, resp);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Map<String, String> userMap = new HashMap<>();
        String line = req.getReader().readLine();
        line = URLDecoder.decode(line);

        System.out.println(line);
        if(line.split("=")[1].equals("Prieteni")){
            List<String> prieteni = new ArrayList<>();
            try {
                prieteni = PrieteniRepository.friends(line.split("=")[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(prieteni.isEmpty())
                req.setAttribute("nofriends", "<h4> Nu aveti prieteni pe username-ul " + line.split("=")[0] + "</h4>");
            else {
                req.setAttribute("friends", prieteni);
                req.setAttribute("username", line.split("=")[0]);
            }
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if(line.split("=")[1].equals("Intra in cont")){
            schimbareCont = line.split("=")[0];
            req.setAttribute("schimbCont", 1);
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if(line.split("=")[1].equals("Pagina principala")){
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
        else if (line.split("=")[1].equals("Sterge cont")) {
            try {
                req.setAttribute("cont", line.split("=")[0]);
                if(ConturiRepository.delete(line.split("=")[0]))
                    req.setAttribute("sters", "Contul " + line.split("=")[0] + " a fost sters!");
                else {
                    req.setAttribute("singurulCont", "Este unicul dvs. cont. Daca acceptati se va " +
                            "sterge intreg userul.<br>Doriti sa continuati?");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if(line.split("=")[1].equals("Accept")) {
            ConturiRepository.deleteUser();
            UserServlet.setIdCurent(0);
            req.getRequestDispatcher("firstpage.jsp").forward(req, resp);
        }
        else if(line.split("=")[1].equals("Cancel")) {
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if(line.split("=")[1].equals("OK")){
            req.getRequestDispatcher("firstpage.jsp").forward(req, resp);
        }
        else if(line.split("=")[1].equals("Adaugare cont")){

            req.setAttribute("add", 1);
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if(line.split("&")[0].split("=").length == 1 && line.split("&")[0].split("=")[0].equals("parola")) {
            req.setAttribute("empty2", "Introduceti o parola!");
            req.setAttribute("schimbCont", 1);
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if (line.split("&")[1].equals("buton=Schimba cont")) {
            ConturiUser cont = null;
            try {
                cont = ConturiRepository.findByUsername(schimbareCont);
                String parola = line.split("&")[0].split("=")[1];

                if (cont.getParola().equals(parola)) {

                    UserServlet.setUsernameCurent(schimbareCont);
                    schimbareCont = null;
                }
                else {
                    req.setAttribute("parola incorecta", "Parola incorecta!");
                    req.setAttribute("schimbCont", 1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }
        else if(line.split("&")[2].equals("buton=Creeaza cont")){

            if(line.split("&")[0].split("=").length == 1 || line.split("&")[1].split("=").length == 1) {
                req.setAttribute("empty", "Credentiale incorecte!");
                req.setAttribute("add", 1);
            }
            else {
                try {
                    String username = line.split("&")[0].split("=")[1];
                    String parola = line.split("&")[1].split("=")[1];
                    ConturiUser cont = ConturiRepository.findByUsername(username);
                    if(cont == null) {
                        cont = new ConturiUser(username, UserServlet.getIdCurent(), parola);
                        ConturiRepository.save(cont);
                        req.setAttribute("saved", "Contul a fost creat!");
                        req.setAttribute("add", null);
                    }
                    else {
                        req.setAttribute("usernameExistent", "Username-ul dat exista!");
                        req.setAttribute("add", 1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            req.getRequestDispatcher("cont.jsp").forward(req, resp);
        }

    }

}
