package Server;

import Core.Packet;

import DAO.ArrivalDaoInterface;
import DAO.MySqlAirportDao;
import DAO.MySqlArrivalDao;
import DAO.MySqlDao;
import Exception.DaoException;
public class FindAllArrivalCommand implements Command {
    private ArrivalDaoInterface iArrival;
    public FindAllArrivalCommand()
    {
        this.iArrival = new MySqlArrivalDao();
    }

    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String responseString = null;

        try
        {
            responseString = iArrival.FindAllArrivalJson();
        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);
    }
}
