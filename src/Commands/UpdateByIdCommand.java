package Commands;

import DAO.MusicBandDAO;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.UserActionsOnElement;

public class UpdateByIdCommand implements Command{

    private CollectionManager collectionManager;
    private UserActionsOnElement userActionsOnElement;
    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;

    public UpdateByIdCommand(CollectionManager collectionManager, MusicBandDAO musicBandDAO){
        this.collectionManager = collectionManager;
        this.musicBandDAO = musicBandDAO;
        userActionsOnElement = new UserActionsOnElement(collectionManager);
        consoleManager = new ConsoleManager();
    }

    public UpdateByIdCommand(){};

    @Override
    public String getName() {
        return "updateById";
    }

    @Override
    public String getDescription() {
        return "updates element by id";
    }

    @Override
    public void execute(String argument) {
        try{
            Long id = Long.parseLong(argument);
            collectionManager.updateById(id, userActionsOnElement);
            if(!(collectionManager.getElementById(id) == null)){
                musicBandDAO.updateById(collectionManager.getElementById(id));
            }
        }
        catch(NumberFormatException e){
            consoleManager.println("id must be a number");
        }
    }
}
