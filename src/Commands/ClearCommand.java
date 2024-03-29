package Commands;

import DAO.MusicBandDAO;
import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class ClearCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;


    public ClearCommand(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
        consoleManager = new ConsoleManager();
    }

    public ClearCommand(){};

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clears the collection";
    }

    @Override
    public void execute(String argument) {
        try {
            if(collectionManager.getCollectionSize()!=0) {
                if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
                collectionManager.clearCollection();
                consoleManager.println("Your record deleted form collection");
                musicBandDAO.clear();
                consoleManager.println("Database сleared");
            }else{
                consoleManager.println("There are no elements in the collection");
            }
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
