package Commands;

import DAO.MusicBandDAO;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveGreaterCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;
    public RemoveGreaterCommand(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
        consoleManager = new ConsoleManager();
    }

    public RemoveGreaterCommand(){};
    @Override
    public String getName() {
        return "removeGreater";
    }

    @Override
    public String getDescription() {
        return "removes all elements from collection where album length filed value exceeds the given one";
    }

    @Override
    public void execute(String argument) {
        try{
            Integer length = Integer.parseInt(argument);
            collectionManager.removeGreater(length);
            musicBandDAO.removeGreater(length);
        }
        catch (NumberFormatException e){
            consoleManager.println("length must be a number");
        }
    }
}
