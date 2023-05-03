package Server;

import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.ArrivalDaoInterface;
import DAO.MySqlAirportDao;
import DAO.MySqlArrivalDao;
import Exception.DaoException;

public class DeleteArrivalCommand implements Command{
    private ArrivalDaoInterface iArrival;
    public DeleteArrivalCommand()
    {
        this.iArrival = new MySqlArrivalDao();
    }

    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String responseString = null;
        String  id = incomingPacket.getPayload();

        try
        {
            responseString = iArrival.DeleteByIdJson(id);
        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);
    }
}
