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
            case FILTER_AIRPORT:
                c = new FilterAirportCommand();
                break;
            case INSERT_AIRPORT:
                c= new InsertAirportCommand() ;
                break;
            case FIND_ALL_ARRIVAL:
                c= new FindAllArrivalCommand();
                break;
            case DELETE_BY_ID:
                c = new DeleteArrivalCommand();
                break;
            case VIEW_ARRIVAL_SORTED:
                c= new  SortArrivalCommand();
                break;
            case ARRIVING_FROM:
                c= new ArrivingFromCommand();
                break;
        }

        return c;
    }
}
