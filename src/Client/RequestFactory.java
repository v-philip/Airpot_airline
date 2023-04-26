package Client;
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

                break;
//            case DELETE_RECIPE_BY_NAME:
//                request = new DeleteRecipeRequest();
//                break;
//            case SORT_RECIPES:
//                request = new SortRecipesRequest();
//                break;
//            case FILTER_RECIPES:
//                request = new FilterRecipesRequest();
//                break;
            default:
                break;
        }

        return request;
    }
}
