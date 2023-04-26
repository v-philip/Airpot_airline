package Server;

import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import Exception.DaoException;

public class FindAirportByIdCommand implements Command {
    private AirportDaoInterface iAirport;
    public FindAirportByIdCommand()
    {
        this.iAirport = new MySqlAirportDao();
    }

    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String responseString = null;
        int id =  Integer.parseInt(incomingPacket.getPayload());

        try
        {
            responseString = iAirport.FindAirportIdsJson(id);
        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);
    }
}
