package servlets;

import models.ConturiUser;
import models.User;
import repositories.ConturiRepository;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static String usernameCurent;

    private static int idCurent;

    public static int getIdCurent() {
        return idCurent;
    }

    public static void setIdCurent(int idCurent) {
        UserServlet.idCurent = idCurent;
    }

    public static String getUsernameCurent() {
        return usernameCurent;
    }

    public static void setUsernameCurent(String usernameCurent) {
        UserServlet.usernameCurent = usernameCurent;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

            req.getRequestDispatcher("firstpage.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Map<String, String> userMap = new HashMap<>();
        String line = req.getReader().readLine();
        line = URLDecoder.decode(line);

        //Nume=popescu&Prenume=ion&Username=ion.p@gmail.com&Parola=abcd&buton=Signin
        String buton = null;
        if (line != null) {

            if(line.charAt(0) == 'n')
                buton = line.split("&")[4].split("=")[1];
            else if(line.charAt(0) == 'u')
                buton = line.split("&")[2].split("=")[1];
            else {
                buton = line.split("=")[1];
                if (buton.equals("Sign in")) {
                    buton = "Signin1";
                    req.getRequestDispatcher("signin.jsp").forward(req, resp);
                }
                else if (buton.equals("Log in")) {
                    buton = "Login1";
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            }
        }

        if (buton.equals("Sign in")) {
            if (line != null && line.split("&")[0].split("=").length == 2 &&
                    line.split("&")[1].split("=").length == 2 &&
                    line.split("&")[2].split("=").length == 2 &&
                    line.split("&")[3].split("=").length == 2) {
                userMap.put(line.split("&")[0].split("=")[0], line.split("&")[0].split("=")[1]);
                userMap.put(line.split("&")[1].split("=")[0], line.split("&")[1].split("=")[1]);
                userMap.put(line.split("&")[2].split("=")[0], line.split("&")[2].split("=")[1]);
                userMap.put(line.split("&")[3].split("=")[0], line.split("&")[3].split("=")[1]);


                User user = new User(
                        userMap.get("nume"),
                        userMap.get("prenume")
                );

                try {

                    if (ConturiRepository.findByUsername(userMap.get("username")) == null) {

                        int idUser = Integer.parseInt(UserRepository.save(user));

                        ConturiUser cont = new ConturiUser(
                                userMap.get("username"),
                                idUser,
                                userMap.get("parola")
                        );
                        ConturiRepository.save(cont);
                        req.setAttribute("saved", "Userul si contul au fost salvate!");
                    } else req.setAttribute("unsaved", "Exista deja un user cu username-ul dat! ");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else if(line != null) {
                req.setAttribute("eroareSignin", "Toate campurile trebuie completate!");
                req.getRequestDispatcher("signin.jsp").forward(req, resp);
            }

        } else if (buton.equals("Log in")) {

            if (line != null) {
                if (line.split("&")[0].split("=").length == 1 || line.split("&")[1].split("=").length == 1){
                    req.setAttribute("eroare", "Credentiale incorecte!");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
                else {
                    userMap.put(line.split("&")[0].split("=")[0], line.split("&")[0].split("=")[1]);
                    userMap.put(line.split("&")[1].split("=")[0], line.split("&")[1].split("=")[1]);
                }
            }
            if(!userMap.isEmpty()) {
                try {

                    ConturiUser cont = ConturiRepository.findByUsername(userMap.get("username"));
                    if (cont != null) {

                        if (cont.getParola().equals(userMap.get("parola"))) {

                            usernameCurent = cont.getUsername();
                            Statement statement = DbConnection.getConnection().createStatement();

                            String sql = "SELECT id_user FROM conturi_user WHERE username like '" + usernameCurent + "' ";

                            ResultSet resultSet = statement.executeQuery(sql);

                            if (resultSet.next()) {
                                idCurent = resultSet.getInt("id_user");
                            }
                            req.getRequestDispatcher("home.jsp").forward(req, resp);

                        } else {
                            req.setAttribute("logare", "Parola incorecta!");
                            req.getRequestDispatcher("login.jsp").forward(req, resp);
                        }
                    } else {
                        req.setAttribute("logare", "Username inexistent!");
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
