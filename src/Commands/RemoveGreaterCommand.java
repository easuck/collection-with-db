package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveGreaterCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public RemoveGreaterCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
            consoleManager.println("element was removed");
        }
        catch (NumberFormatException e){
            consoleManager.println("length must be a number");
        }
    }
}
