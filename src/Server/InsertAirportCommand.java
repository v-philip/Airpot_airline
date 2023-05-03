package Server;

import Client.InsertAirportRequest;
import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import DTO.Airport;
import com.google.gson.Gson;

import Exception.DaoException;

public class InsertAirportCommand implements Command{
    private AirportDaoInterface iAirport;
    public InsertAirportCommand(){
        this.iAirport = new MySqlAirportDao();
    }
    @Override
    public Packet createResponse(Packet incomingPacket){

        String responseString = null;

        Gson gson = new Gson();
        Airport response = new Airport();

        // Convert the JSON string to a Java object
        Airport obj = gson.fromJson(incomingPacket.getPayload(),Airport.class);

        try
        {
            response = iAirport.InsertAirport(obj);
            responseString = gson.toJson(response);

        }
        catch (DaoException daoe)
        {
            System.out.println("DisplayAllCommand.createResponse() " + daoe.getMessage());
        }


        return new Packet(incomingPacket.getRequestType(), responseString);

    }
}
