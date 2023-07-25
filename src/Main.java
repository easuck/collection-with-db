import Utility.CollectionManager;
import Utility.CommandsManager;
import Utility.DatabaseConnector;

import java.sql.Connection;

public class Main {
    public static void main(String[] args)  {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.connect();

        CollectionManager collectionManager = new CollectionManager();
        CommandsManager commandsManager = new CommandsManager(collectionManager);
        commandsManager.manageCommands();
    }
}