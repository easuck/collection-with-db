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
            int key = collectionManager.replaceIfGreater(length);
            if (key != -1){
                musicBandDAO.replaceIfGreater(length, key);
                consoleManager.println("value was replaced");
            }
            else{
                consoleManager.println("no fit element to replace");
            }
        }
        catch(NumberFormatException e){
            consoleManager.println("length must be a number");
        }
    }
}
