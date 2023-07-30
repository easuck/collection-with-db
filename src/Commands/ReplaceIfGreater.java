package Commands;

import DAO.MusicBandDAO;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.UserActionsOnElement;

public class ReplaceIfGreater implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;

    public ReplaceIfGreater(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
        consoleManager = new ConsoleManager();

    }

    public ReplaceIfGreater(){}

    @Override
    public String getName() {
        return "replaceIfGreater";
    }

    @Override
    public String getDescription() {
        return "replace the first element of the collection if album length value is lower than given one";
    }

    @Override
    public void execute(String argument) {
        try{
            Integer length = Integer.parseInt(argument);
            collectionManager.replaceIfGreater(length);
            musicBandDAO.replaceIfGreater(length);
            consoleManager.println("value was replaced");
        }
        catch(NumberFormatException e){
            consoleManager.println("length must be a number");
        }
    }
}
