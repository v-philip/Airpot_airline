package Client;

import Core.Packet;
import DTO.Airport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FilterAirportRequest implements Request {
    public FilterAirportRequest() {

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Airport you want to filter by \n");
        String payload = sc.next();
        String responsePayload = null;


        Client c = new Client();


        requestPacket.setPayload(payload);

        writer.println(requestPacket.writeToJSON());
        writer.flush();

        String responseJSON = input.nextLine();
        responsePacket.readFromJSON(responseJSON);
        responsePayload = responsePacket.getPayload();


        if (responsePayload == null) {
            System.out.println("There are no airport with that id");
            return;
        }

        else
        {
            Type airportListType = new TypeToken<ArrayList<Airport>>(){}.getType();
            List<Airport> airports = gsonParser.fromJson(responsePayload, airportListType);
            System.out.println("The details of the airport with that id is: ");
            System.out.println("Displaying All Airport");
            for(Airport airport : airports)
            {
                System.out.println(airport);
            }
        }
    }
}
