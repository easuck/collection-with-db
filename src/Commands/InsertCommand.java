package Commands;

import DAO.MusicBandDAO;
import Exceptions.WrongAmountCommandsException;
import MusicBand.MusicBand;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.UserActionsOnElement;

public class InsertCommand implements Command{

    private ConsoleManager consoleManager;
    private UserActionsOnElement userActionsOnElement;
    private CollectionManager collectionManager;
    private MusicBandDAO musicBandDAO;

    public InsertCommand(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
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
            if(collectionManager.getMusicBands().containsKey(key)) throw new Exception();
            MusicBand musicBand = new MusicBand();
            userActionsOnElement.setElementOfCollection(musicBand);
            collectionManager.insertElementByKey(key, musicBand);
            musicBandDAO.insertDataToDatabase(key, musicBand);
        }
        catch(NumberFormatException e){
            consoleManager.println("key must be a number");
        }
        catch(Exception e){
            consoleManager.println("element with such key already exists");
        }
    }
}
