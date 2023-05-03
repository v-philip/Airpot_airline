package Server;

import Core.Packet;
import DAO.ArrivalDaoInterface;
import Exception.DaoException;
import DAO.MySqlArrivalDao;
import DTO.Airport;
import com.google.gson.Gson;

public class ArrivingFromCommand implements Command {
    private ArrivalDaoInterface iArrival;
    public ArrivingFromCommand()
    {
        this.iArrival = new MySqlArrivalDao();
    }
    @Override
    public Packet createResponse(Packet incomingPacket){

        String responseString = null;
        int id =  Integer.parseInt(incomingPacket.getPayload());


        try
        {
            responseString = iArrival.ArrivingFrom(id);


        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);

    }
}
