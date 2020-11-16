package servlets;

import models.ConturiUser;
import models.User;
import repositories.ConturiRepository;
import repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/prieteni")
public class PrieteniServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.getRequestDispatcher("friend.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String line = req.getReader().readLine();
        line = URLDecoder.decode(line);

        String username = null;

        if(line.split("=").length == 2)
            username = line.split("=")[1];
        else if(line.split("=").length == 3) {
            System.out.println(line);
            username = "";
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
        else username = "";

        if(username.isEmpty() & line.split("=").length != 3) {
            String mesaj = "Introduceti un username pentru cautare!";
            req.setAttribute("eroare", mesaj);
            req.getRequestDispatcher("friend.jsp").forward(req, resp);
        }
        else if(!username.isEmpty()){
            try {
                if (ConturiRepository.findByUsername(username) == null){
                    String mesaj = "Username-ul nu a fost gasit!";
                    req.setAttribute("eroare", mesaj);
                }
                else
                    req.setAttribute("username", username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("friend.jsp").forward(req, resp);
        }

    }

}
