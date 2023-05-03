package Server;

import Core.Packet;
import DAO.ArrivalDaoInterface;
import DAO.MySqlArrivalDao;
import Exception.DaoException;
public class SortArrivalCommand implements Command {
    private ArrivalDaoInterface iArrival;
    public SortArrivalCommand()
    {
        this.iArrival = new MySqlArrivalDao();
    }

    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String responseString = null;

        try
        {
            responseString = iArrival.ArrivalSorted();
        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);
    }
}
