package repositories;

import models.ConturiUser;
import models.User;
import servlets.UserServlet;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static String save(User user) throws SQLException {
        String sql = "INSERT INTO user(nume, prenume) VALUES(?, ?)";

        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);

        preparedStatement.setString(1, user.getNume());
        preparedStatement.setString(2, user.getPrenume());

        preparedStatement.execute();

        Statement statement = DbConnection.getConnection().createStatement();
        sql = "SELECT max(id_user) FROM user ";

        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()) {
            return resultSet.getString("max(id_user)");
        }
        else
            return null;
    }

    public static List<String> findUser() throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();

        String sql = "SELECT nume, prenume from USER WHERE id_user = " + UserServlet.getIdCurent();

        ResultSet resultSet = statement.executeQuery(sql);
        List<String> user = new ArrayList<>();
        while(resultSet.next()){
            user.add(resultSet.getString("nume"));
            user.add(resultSet.getString("prenume"));
        }
        return user;
    }


}
