package Commands;

import DAO.MusicBandDAO;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveLowerCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;

    public RemoveLowerCommand(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
        consoleManager = new ConsoleManager();
    }

    public RemoveLowerCommand(){};
    @Override
    public String getName() {
        return "removeLower";
    }

    @Override
    public String getDescription() {
        return "removes all elements from collection where album length filed value less than given one";
    }

    @Override
    public void execute(String argument) {
        try{
            Integer length = Integer.parseInt(argument);
            collectionManager.removeLower(length);
            musicBandDAO.removeLower(length);
            consoleManager.println("element was removed");
        }
        catch(NumberFormatException e){
            consoleManager.println("length must be a number");
        }
    }
}
