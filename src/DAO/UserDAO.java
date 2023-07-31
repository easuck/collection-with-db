package DAO;

import Utility.DatabaseConnector;

import java.sql.*;

public class UserDAO {

    private DatabaseConnector databaseConnector;
    private Connection connection;
    private static int userId;

    public UserDAO(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
        connection = databaseConnector.connect();
        userId = lastIdFromDatabase();
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        UserDAO.userId = userId;
    }

    public int lastIdFromDatabase(){
        try{
            String query = "select * from users order by id desc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }


    public void signUp(String username, String password){
        try{
            String query = "insert into users(id, username, password) values(?,?,?)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, ++UserDAO.userId);
            pStatement.setString(2, username);
            pStatement.setString(3, password);
            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int singIn(String username, String password){
        try{
            String query = "select * from users where username = ? and password = ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("id");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
