package repositories;

import models.ConturiUser;
import models.User;
import servlets.UserServlet;
import utils.DbConnection;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConturiRepository {

    public static void save(ConturiUser cont) throws SQLException {
        String sql = "INSERT INTO conturi_user(username, parola, id_user) VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);

        preparedStatement.setString(1, cont.getUsername());
        preparedStatement.setString(2, cont.getParola());
        preparedStatement.setInt(3, cont.getIdUser());

        preparedStatement.execute();
    }

    public static ConturiUser findByUsername(String username) throws SQLException {

        Statement statement = DbConnection.getConnection().createStatement();
        String sql = "SELECT * FROM conturi_user WHERE username like '" + username + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()) {
            ConturiUser cont = new ConturiUser(
                    resultSet.getString("username"),
                    resultSet.getInt("id_user"),
                    resultSet.getString("parola")
            );
            return cont;
        }
        else
            return null;
    }

    public static List<String> afisareConturi() {

        List<String> conturi = new ArrayList<>();
        try {
            Statement statement = DbConnection.getConnection().createStatement();

            String sql = "SELECT username FROM conturi_user WHERE id_user=" + UserServlet.getIdCurent();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                conturi.add(resultSet.getString("username"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return conturi;
    }

    public static boolean delete(String username) throws SQLException { //returneaza 0 daca avem un sigur cont si trebuie sters si user-ul, 1 daca contul s a sters cu succes

        List<String> conturi = afisareConturi();
        if(conturi.size() == 1){
            return false;
        }
        else {
            List<String> prieteniCont = PrieteniRepository.friends(username);

            if(prieteniCont != null) {
                for(String prieten : prieteniCont) {
                    String sql = "DELETE FROM prieteni WHERE username LIKE '" + prieten + "' ";
                    PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
                    preparedStatement.execute();
                }
            }

            String sql = "DELETE FROM conturi_user WHERE username like '" + username + "' ";
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.execute();


            sql = "DELETE FROM prieteni WHERE username like '" + username + "' ";
            preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.execute();

            List<Integer> emailuri = new ArrayList<>();

            sql = "select id_email " +
                    "from email " +
                    "where id_email - 1 in(select id_email from email where tip_email like 'trimis' and username_prieten like '"
                    + username +"')";

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }

            sql = "select id_email " +
                    "from email " +
                    "where id_email in(select id_email from email where tip_email like 'trimis' and username_prieten like '"
                    + username +"')";

            statement = DbConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }

            sql = "select id_email " +
                    "from email " +
                    "where id_email + 1 in (select id_email from email where tip_email like 'primit' and username_prieten like'"
                    + username +"')";

            statement = DbConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }
            sql = "select id_email " +
                    "from email " +
                    "where id_email in(select id_email from email where tip_email like 'primit' and username_prieten like '"
                    + username +"')";

            statement = DbConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }

            for(int i : emailuri){
                sql = "DELETE FROM email WHERE id_email =" + i;
                preparedStatement = DbConnection.getConnection().prepareStatement(sql);
                preparedStatement.execute();
            }

            return true;
        }

    }
    public static void deleteUser() {
        try {
            List<String> prieteniCont = null;
            prieteniCont = PrieteniRepository.friends(UserServlet.getUsernameCurent());


            if(prieteniCont != null) {
                for(String prieten : prieteniCont) {
                    String sql = "DELETE FROM prieteni WHERE username LIKE '" + prieten + "' ";
                    PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
                    preparedStatement.execute();
                }
            }

            String sql = "DELETE FROM conturi_user WHERE username like '" + UserServlet.getUsernameCurent() + "' ";
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.execute();


            sql = "DELETE FROM prieteni WHERE username like '" + UserServlet.getUsernameCurent() + "' ";
            preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.execute();

            sql = "DELETE FROM user WHERE id_user=" + UserServlet.getIdCurent();
            preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.execute();

            List<Integer> emailuri = new ArrayList<>();

            sql = "select id_email " +
                    "from email " +
                    "where id_email - 1 in(select id_email from email where tip_email like 'trimis' and username_prieten like '"
                    + UserServlet.getUsernameCurent() +"')";

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }

            sql = "select id_email " +
                    "from email " +
                    "where id_email in(select id_email from email where tip_email like 'trimis' and username_prieten like '"
                    + UserServlet.getUsernameCurent() +"')";

            statement = DbConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }

            sql = "select id_email " +
                    "from email " +
                    "where id_email + 1 in (select id_email from email where tip_email like 'primit' and username_prieten like'"
                    + UserServlet.getUsernameCurent() +"')";

            statement = DbConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }
            sql = "select id_email " +
                    "from email " +
                    "where id_email in(select id_email from email where tip_email like 'primit' and username_prieten like '"
                    + UserServlet.getUsernameCurent() +"')";

            statement = DbConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                emailuri.add(resultSet.getInt("id_email"));
            }

            for(int i : emailuri){
                sql = "DELETE FROM email WHERE id_email =" + i;
                preparedStatement = DbConnection.getConnection().prepareStatement(sql);
                preparedStatement.execute();
            }

            UserServlet.setUsernameCurent(null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
