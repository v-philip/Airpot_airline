package Server;

import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import Exception.DaoException;

public class FilterAirportCommand implements Command{
    private AirportDaoInterface iAirport;
    public FilterAirportCommand()
    {
        this.iAirport = new MySqlAirportDao();
    }

    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String responseString = null;
        String Country =  incomingPacket.getPayload();

        try
        {
            responseString = iAirport.filterByCountryJson(Country);
        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);
    }
}
