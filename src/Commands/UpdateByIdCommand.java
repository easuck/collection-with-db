package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;
import Utility.UserActionsOnElement;

public class UpdateByIdCommand implements Command{

    private CollectionManager collectionManager;
    private UserActionsOnElement userActionsOnElement;
    private ConsoleManager consoleManager;

    public UpdateByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
            consoleManager.println("element was updated");
        }
        catch(NumberFormatException e){
            consoleManager.println("id must be a number");
        }
    }
}
