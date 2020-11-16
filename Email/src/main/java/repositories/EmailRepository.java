package repositories;

import models.ConturiUser;
import models.EmailPrimitTrimis;
import servlets.UserServlet;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmailRepository {

    private static String usernamePrieten;

    private static String subiect;

    private static String mesaj;

    public static String getMesaj() {
        return mesaj;
    }

    public static void setMesaj(String mesaj) {
        EmailRepository.mesaj = mesaj;
    }

    public static String getSubiect() {
        return subiect;
    }

    public static void setSubiect(String subiect) {
        EmailRepository.subiect = subiect;
    }

    public static String getUsernamePrieten() {
        return usernamePrieten;
    }

    public static void setUsernamePrieten(String usernamePrieten) {
        EmailRepository.usernamePrieten = usernamePrieten;
    }

    public static void sendEmail(String username, String subiect, String mesaj) throws SQLException {

        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "SELECT id_user FROM conturi_user WHERE username like '" + username + "' ";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()) {
            int idUser = resultSet.getInt("id_user");

            sql = "INSERT INTO email(id_user, tip_email, data, username_prieten, subiect, mesaj) VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);

            LocalDate date = LocalDate.now();
            String tipEmail = "trimis";

            preparedStatement.setInt(1, UserServlet.getIdCurent());
            preparedStatement.setString(2, tipEmail);
            preparedStatement.setString(3, String.valueOf(date));
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, subiect);
            preparedStatement.setString(6, mesaj);

            preparedStatement.execute();

            tipEmail = "primit";

            sql = "INSERT INTO email(id_user, tip_email, data, username_prieten, subiect, mesaj) VALUES(?, ?, ?, ?, ?, ?)";

            preparedStatement = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, tipEmail);
            preparedStatement.setString(3, String.valueOf(date));
            preparedStatement.setString(4, UserServlet.getUsernameCurent());
            preparedStatement.setString(5, subiect);
            preparedStatement.setString(6, mesaj);

            preparedStatement.execute();
        }

    }

    public static List<String> raportPrieteni() throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();

        String sql = " SELECT username_prieten FROM (SELECT COUNT(*) AS Total, username_prieten " +
                "FROM email where id_user=" + UserServlet.getIdCurent() + " GROUP BY username_prieten ) AS Results " +
                "where Total = ( Select MAX(Total) from (SELECT COUNT(*) AS Total, username_prieten FROM email where id_user=" + UserServlet.getIdCurent() +
                " GROUP BY username_prieten ) AS Results)";

        ResultSet resultSet = statement.executeQuery(sql);
        List<String> friends = new ArrayList<>();
        while(resultSet.next()) {
            friends.add(resultSet.getString("username_prieten"));
        }
        return friends;
    }

    public static List<String> raportZi () throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();

        String sql = " SELECT data FROM (SELECT COUNT(*) AS Total, data " +
                "FROM email where id_user=" + UserServlet.getIdCurent() + " GROUP BY data ) AS Results " +
                "where Total = ( Select MAX(Total) from (SELECT COUNT(*) AS Total, data FROM email where id_user=" + UserServlet.getIdCurent() +
                " GROUP BY data ) AS Results)";

        ResultSet resultSet = statement.executeQuery(sql);
        List<String> days = new ArrayList<>();
        while(resultSet.next()) {
            days.add(resultSet.getString("data"));
        }
        return days;
    }

    public static List<EmailPrimitTrimis> inbox () throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "select subiect, mesaj, data, username_prieten " +
                "from email " +
                "where id_email - 1 in (select id_email from email where tip_email like 'trimis' and username_prieten like '" +
                UserServlet.getUsernameCurent() + "')";

        ResultSet resultSet = statement.executeQuery(sql);

        List<EmailPrimitTrimis> inbox = new ArrayList<>();

        while(resultSet.next()) {
            EmailPrimitTrimis email = new EmailPrimitTrimis(
                    resultSet.getString("username_prieten"),
                    resultSet.getString("data"),
                    resultSet.getString("subiect"),
                    resultSet.getString("mesaj")
            );
            inbox.add(email);
        }
        return inbox;
    }

    public static List<EmailPrimitTrimis> sent () throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "select subiect, mesaj, data, username_prieten " +
                "from email " +
                "where id_email + 1 in (select id_email from email where tip_email like 'primit' and username_prieten like '" +
                UserServlet.getUsernameCurent() + "')";

        ResultSet resultSet = statement.executeQuery(sql);

        List<EmailPrimitTrimis> sent = new ArrayList<>();

        while(resultSet.next()) {
            EmailPrimitTrimis email = new EmailPrimitTrimis(
                    resultSet.getString("username_prieten"),
                    resultSet.getString("data"),
                    resultSet.getString("subiect"),
                    resultSet.getString("mesaj")
            );
            sent.add(email);
        }
        return sent;
    }
    public static void deleteEmail(EmailPrimitTrimis email) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "select id_email from email " +
                "where username_prieten like '" + email.getUsernamePrieten() +
                "' and data like '" + email.getData() +"' and subiect like '" +
                email.getSubiect() + "' and mesaj like '" + email.getMesaj() +"' ";

        ResultSet resultSet = statement.executeQuery(sql);
        int id_email = 0;
        if(resultSet.next()) {
            id_email = resultSet.getInt("id_email");
        }
        if(id_email % 2 == 0) {
            id_email--;
            sql = "delete from email where id_email =" + id_email;
            statement.execute(sql);
            id_email++;
            sql = "delete from email where id_email =" + id_email;
            statement.execute(sql);
        }
        else {
            id_email++;
            sql = "delete from email where id_email =" + id_email;
            statement.execute(sql);
            id_email--;
            sql = "delete from email where id_email =" + id_email;
            statement.execute(sql);
        }
    }

}
