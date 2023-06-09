package Client;

import Core.Packet;
import DTO.Airport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindAllAirportRequest implements Request{
    public FindAllAirportRequest(){

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser)
    {
        requestPacket.setPayload("");
        writer.println(requestPacket.writeToJSON());
        writer.flush();

        Client c = new Client();

        String responseJSON = input.nextLine();
        responsePacket.readFromJSON(responseJSON);

        String responsePayload = responsePacket.getPayload();
        if(responsePayload == null)
        {
            System.out.println("The database is not loaded");
            return;
        }

        Type airportListType = new TypeToken<ArrayList<Airport>>(){}.getType();
        List<Airport> airports = gsonParser.fromJson(responsePayload, airportListType);

        System.out.println("Displaying All Airport");
        for(Airport airport : airports)
        {
            System.out.println(airport);
            System.out.println();
        }

        if (!Client.flag)
        {
        for(Airport airport : airports)
        {
            c.updateHashSet(airport.getAirport_id());
        }
        }

        c.setFlag( true);

    }
}
