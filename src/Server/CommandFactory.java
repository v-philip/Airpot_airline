package Server;
import static Core.Menu.*;
public class CommandFactory {
    public CommandFactory()
    {
    }
    public Command createCommand(ClientMenu command)
    {
        Command c = null;

        switch (command)
        {
            case FIND_ALL_AIRPORT:
                c = new FindAllAirportCommand();
                break;
            case FIND_BY_ID:
                c = new FindAirportByIdCommand();
                break;

        }

        return c;
    }
}
