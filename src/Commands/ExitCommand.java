package Commands;

import DAO.MusicBandDAO;
import Exceptions.WrongAmountCommandsException;
import Utility.ConsoleManager;

public class ExitCommand implements Command{

    private ConsoleManager consoleManager;
    private MusicBandDAO musicBandDAO;

    public ExitCommand(MusicBandDAO musicBandDAO) {
        consoleManager = new ConsoleManager();
        this.musicBandDAO = musicBandDAO;
    }

    public ExitCommand(){}

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
            musicBandDAO.close();
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
