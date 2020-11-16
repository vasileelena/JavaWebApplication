package repositories;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import models.ConturiUser;
import models.Prieteni;
import models.User;
import servlets.UserServlet;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrieteniRepository {

    //se apeleaza de 2 ori, cu conturile inversate
    public static void save(String username1, String username2) throws SQLException {

        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "SELECT id_user FROM conturi_user WHERE username like '" + username1 + "' ";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            int idUser = resultSet.getInt("id_user");
            sql = "INSERT INTO prieteni(id_user, username) VALUES(?, ?)";

            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, username2);

            preparedStatement.execute();
        }

    }

    public static boolean findFriends(String username1, String username2) throws SQLException {

        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "SELECT id_user FROM conturi_user WHERE username like '" + username1 + "' ";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            int idUser = resultSet.getInt("id_user");

            sql = "SELECT username FROM prieteni WHERE username like '" + username2 + "' and id_user =" + idUser;

            ResultSet resultSet1 = statement.executeQuery(sql);
            if(!resultSet1.next()) {
                return false;
            }
            else return true;
        }
        return true;
    }

    public static List<String> friends(String username) throws SQLException {

        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "SELECT username FROM prieteni WHERE id_user=" + UserServlet.getIdCurent() +
                " AND username IN (SELECT cu.username FROM prieteni p " +
                "JOIN conturi_user cu ON(p.id_user = cu.id_user) WHERE p.username LIKE '" + username + "') ";

        ResultSet resultSet = statement.executeQuery(sql);
        List<String> friends = new ArrayList<>();
        while(resultSet.next()) {
            friends.add(resultSet.getString("username"));
        }
        return friends;
    }


}
