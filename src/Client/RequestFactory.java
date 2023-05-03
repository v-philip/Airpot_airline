package Client;
import Server.SortArrivalCommand;

import  static Core.Menu.*;
public class RequestFactory {
    public RequestFactory()
    {
    }

    public Request createRequest(ClientMenu requestType)
    {
        Request request = null;

        switch (requestType)
        {
            case FIND_ALL_AIRPORT:
                request =  new FindAllAirportRequest();
                break;
            case FIND_BY_ID:
                request = new FindById();
                break;
            case FILTER_AIRPORT:
                request = new FilterAirportRequest();
                break;
            case INSERT_AIRPORT:
                request = new InsertAirportRequest();
                break;
            case FIND_ALL_ARRIVAL:
            request = new FindAllArrivalRequest();
            break;
            case DELETE_BY_ID:
                request = new DeleteArrivalRequest();
                break;
            case VIEW_ARRIVAL_SORTED:
                request = new SortArrivalRequest();
                break;
            case ARRIVING_FROM:
                request = new ArrivingFromRequest();
            default:
                break;

        }

        return request;
    }
}
