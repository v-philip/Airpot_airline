package Server;

import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import Exception.DaoException;

public class FindAllAirportCommand implements Command {
    private AirportDaoInterface  iAirport;
    public FindAllAirportCommand()
    {
        this.iAirport = new MySqlAirportDao();
    }

    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String responseString = null;

        try
        {
            responseString = iAirport.FindAllAirportsJson();
        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);
    }
}
