package Commands;

import Exceptions.WrongAmountCommandsException;
import MusicBand.*;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class RemoveFirstByGenreCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public RemoveFirstByGenreCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        consoleManager = new ConsoleManager();

    }

    public RemoveFirstByGenreCommand(){}

    @Override
    public String getName() {
        return "removeFirstByGenre";
    }

    @Override
    public String getDescription() {
        return "removes first element of collection where genre matches with the given one";
    }

    @Override
    public void execute(String argument) {
        try{
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            consoleManager.println("1.HIP-HOP\n2.RAP\n3.POP");
            Integer genreNumber;
            String genre;
            while(true){
                try{
                    consoleManager.print("enter a number of genre you need: ");
                    genreNumber = consoleManager.readInt();
                    if (genreNumber < 1 || genreNumber > 3) throw new Exception();
                    if (genreNumber == 1){
                        genre = MusicGenre.HIP_HOP.getTitle();
                        break;
                    }
                    if (genreNumber == 2){
                        genre = MusicGenre.RAP.getTitle();
                        break;
                    }
                    genre = MusicGenre.POP.getTitle();
                    break;
                }
                catch(Exception e){
                    consoleManager.println("wrong input, try again");
                }
            }
            collectionManager.removeFirstByGenre(genre);
            consoleManager.println("element was removed");
        }
        catch(WrongAmountCommandsException e){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
        catch(NumberFormatException e){
            consoleManager.println("wrong input, you must have entered the number of a genre");
        }
    }
}
