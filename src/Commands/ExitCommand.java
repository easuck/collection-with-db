package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.ConsoleManager;

public class ExitCommand implements Command{

    ConsoleManager consoleManager;

    public ExitCommand(){
        this.consoleManager = new ConsoleManager();
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit the application";
    }

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            consoleManager.println("Good bye");
            consoleManager.exit();
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
