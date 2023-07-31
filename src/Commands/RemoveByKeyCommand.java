package Commands;

import DAO.MusicBandDAO;
import Exceptions.NoKeyReferenceException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveByKeyCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;

    public RemoveByKeyCommand(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
        consoleManager = new ConsoleManager();
    }

    public RemoveByKeyCommand(){};

    @Override
    public String getName() {
        return "removeByKey";
    }

    @Override
    public String getDescription() {
        return "removes element by key";
    }

    @Override
    public void execute(String argument) {
        try{
            Integer key = Integer.parseInt(argument);
            if (collectionManager.removeElementByKey(key)){
                musicBandDAO.removeByKey(key);
                consoleManager.println("element was removed");
            }
            else{
                consoleManager.println("you can't delete record with key " + key);
            }
        }
        catch(NumberFormatException e){
            consoleManager.println("key must be a number");
        }
        catch(NoKeyReferenceException e){
            consoleManager.println("no element with such key in collection");
        }
    }
}
