import DAO.MusicBandDAO;
import Utility.CollectionManager;
import Utility.CommandsManager;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args)  {

        MusicBandDAO musicBandDAO = new MusicBandDAO();
        CollectionManager collectionManager = new CollectionManager(musicBandDAO);
        collectionManager.addDataFromDatabase();
        CommandsManager commandsManager = new CommandsManager(collectionManager, musicBandDAO);
        commandsManager.manageCommands();
    }
}