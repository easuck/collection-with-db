package Commands;

import Exceptions.NoKeyReferenceException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveByKeyCommand implements Command{

    private CollectionManager collectionManager;

    private ConsoleManager consoleManager;

    public RemoveByKeyCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
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
            collectionManager.removeElementByKey(key);
            consoleManager.println("element was removed");
        }
        catch(NumberFormatException e){
            consoleManager.println("key must be a number");
        }
        catch(NoKeyReferenceException e){
            consoleManager.println("no element with such key in collection");
        }
    }
}
