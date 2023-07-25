package Commands;

public interface Command {

    String getName();

    String getDescription();

    void execute(String argument);

}
