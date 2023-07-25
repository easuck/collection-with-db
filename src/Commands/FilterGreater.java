package Commands;

import Utility.CollectionManager;
import Utility.ConsoleManager;

public class FilterGreater implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public FilterGreater(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        consoleManager = new ConsoleManager();
    }

    public FilterGreater(){}

    @Override
    public String getName() {
        return "filterGreater";
    }

    @Override
    public String getDescription() {
        return "filters elements of collection so number of participants exceeds the given one, then displays elements";
    }

    @Override
    public void execute(String argument) {
        try{
            Integer numberOfParticipants = Integer.parseInt(argument);
            collectionManager.filterGreater(numberOfParticipants);
        }
        catch(NumberFormatException e){
            consoleManager.println("number of participants must be a number");
        }
    }
}
