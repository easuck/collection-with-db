package DAO;

import MusicBand.*;

import Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicBandDAO {

    private DatabaseConnector databaseConnector;
    private Connection connection;

    public MusicBandDAO(){
        this.databaseConnector = new DatabaseConnector();
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

                    //musicBand.setGenre((MusicGenre)resultSet.getObject("genre"));

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
}
