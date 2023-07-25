package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class ShowCommand implements Command{

    public CollectionManager collectionManager;
    public ConsoleManager consoleManager;

    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        consoleManager = new ConsoleManager();
    }

    public ShowCommand(){};

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "prints to standard output all elements of the collection";
    }

    @Override
    public void execute(String argument) {
        try{
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            collectionManager.showElements();
        }
        catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
