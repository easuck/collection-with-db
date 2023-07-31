import DAO.MusicBandDAO;
import DAO.UserDAO;
import RegistrationAndAuthorization.StartingApp;
import Utility.CollectionManager;
import Utility.CommandsManager;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args)  {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        UserDAO userDAO = new UserDAO(databaseConnector);
        StartingApp startingApp = new StartingApp(userDAO);
        startingApp.start();
        System.out.println("current user id: " + UserDAO.getUserId());

        MusicBandDAO musicBandDAO = new MusicBandDAO(databaseConnector);
        CollectionManager collectionManager = new CollectionManager(musicBandDAO);
        collectionManager.addDataFromDatabase();
        CommandsManager commandsManager = new CommandsManager(collectionManager, musicBandDAO);
        commandsManager.manageCommands();
    }
}