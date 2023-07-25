package Commands;

import Exceptions.WrongAmountCommandsException;
import MusicBand.MusicBand;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.UserActionsOnElement;

public class InsertCommand implements Command{

    private ConsoleManager consoleManager;
    private UserActionsOnElement userActionsOnElement;
    private CollectionManager collectionManager;

    public InsertCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        consoleManager = new ConsoleManager();
        userActionsOnElement = new UserActionsOnElement(collectionManager);
    }

    public InsertCommand(){};

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "insert element by the specified key";
    }

    @Override
    public void execute(String argument) {
        try{
            Integer key = Integer.parseInt(argument);
            MusicBand musicBand = new MusicBand();
            userActionsOnElement.setElementOfCollection(musicBand);
            collectionManager.insertElementByKey(key, musicBand);
        }
        catch(NumberFormatException e){
            consoleManager.println("key must be a number");
        }
    }
}
