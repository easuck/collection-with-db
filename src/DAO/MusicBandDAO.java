package DAO;

import MusicBand.*;

import Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicBandDAO {

    private DatabaseConnector databaseConnector;
    private Connection connection;

    public MusicBandDAO(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
        this.connection = databaseConnector.connect();
    }

    public List<MusicBand> getDataFromDatabase(){
        List<MusicBand> musicBands = new ArrayList<>();
        try{
            String query = "select * from music_bands";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                MusicBand musicBand = new MusicBand();
                try{
                    musicBand.setName(resultSet.getString("name"));

                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(resultSet.getDouble("coordinate_x"));
                    coordinates.setY(resultSet.getFloat("coordinate_y"));
                    musicBand.setCoordinates(coordinates);

                    musicBand.setNumberOfParticipants(resultSet.getInt("number_of_participants"));
                    musicBand.setSinglesCount(resultSet.getInt("singles_count"));

                    String title = resultSet.getString("genre");
                    musicBand.setGenre(defineMusicGenre(title));

                    Album album = new Album();
                    album.setName(resultSet.getString("best_album_name"));
                    album.setLength(resultSet.getLong("best_album_length"));
                    musicBand.setBestAlbum(album);

                    musicBands.add(musicBand);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            return musicBands;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public MusicGenre defineMusicGenre(String title){
        if(title.equals(MusicGenre.POP.getTitle())){
            return MusicGenre.POP;
        }
        if(title.equals(MusicGenre.RAP.getTitle())){
            return MusicGenre.RAP;
        }
        if(title.equals(MusicGenre.HIP_HOP.getTitle())){
            return MusicGenre.HIP_HOP;
        }
        return null;
    }

    public int numberOfRecords(){
        try{
            String query = "select from music_bands";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            return resultSetMetaData.getColumnCount();
        }
        catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public void clear(){
        try{
            String query = "delete from music_bands";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            connection.close();
        }
        catch(SQLException e){
            System.out.println("connection is already closed");
        }
    }

    public void removeByKey(int key){
        try{
            String query = "delete from music_bands where key=?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, key);
            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //ЭТО СКОРЕЕ ВСЕГО НЕ РАБОТАЕТ ИЗ-ЗА SELECT 1
    public void removeFirstByGenre(String title){
        try{
            String query = "delete from music_bands where exists " +
                    "(select 1 from music_bands where genre=? order by key asc)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, title);
            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeGreater(long length){
        try{
            String query = "delete from music_bands where best_album_length > ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, length);
            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeLower(long length){
        try{
            String query = "delete from music_bands where best_album_length < ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, length);
            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void replaceIfGreater(long length){
        try{
            String query = "update music_bands set best_album_length = ? where key=0 " +
                    "and best_album_length < ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, length);
            pStatement.setLong(2, length);
            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateById(MusicBand musicBand){
        try{
            String query = "update music_bands set coordinate_x=?," +
                    "coordinate_y=?," +
                    "number_of_participants=?," +
                    "singles_count=?," +
                    "genre=?," +
                    "best_album_name=?," +
                    "best_album_length=?," +
                    "name=? " +
                    "where id=?";

            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setDouble(1, musicBand.getCoordinates().getX());
            pStatement.setFloat(2, musicBand.getCoordinates().getY());
            pStatement.setInt(3, musicBand.getNumberOfParticipants());
            pStatement.setInt(4, musicBand.getSinglesCount());
            pStatement.setString(5, musicBand.getGenre().getTitle());
            pStatement.setString(6, musicBand.getBestAlbum().getName());
            pStatement.setLong(7, musicBand.getBestAlbum().getLength());
            pStatement.setString(8, musicBand.getName());
            pStatement.setLong(9, musicBand.getId());

            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertDataToDatabase(int key, MusicBand musicBand){
        try{
            String query = "insert into music_bands(id, name, coordinate_x, coordinate_y," +
                    " creation_date, number_of_participants, singles_count, genre," +
                    " best_album_name, best_album_length, key)" +
                    " values(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, musicBand.getId());
            pStatement.setString(2, musicBand.getName());
            pStatement.setDouble(3, musicBand.getCoordinates().getX());
            pStatement.setFloat(4, musicBand.getCoordinates().getY());
            pStatement.setDate(5, java.sql.Date.valueOf(musicBand.getCreationDate().toLocalDate()));
            pStatement.setInt(6, musicBand.getNumberOfParticipants());
            pStatement.setInt(7, musicBand.getSinglesCount());
            pStatement.setString(8, musicBand.getGenre().getTitle());
            pStatement.setString(9, musicBand.getBestAlbum().getName());
            pStatement.setLong(10, musicBand.getBestAlbum().getLength());
            pStatement.setInt(11, key);

            pStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
