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

        String responseJSON = input.nextLine();
        responsePacket.readFromJSON(responseJSON);

        String responsePayload = responsePacket.getPayload();
        if(responsePayload == null)
        {
            System.out.println("The database is not loaded");
            return;
        }

        Type recipeListType = new TypeToken<ArrayList<Airport>>(){}.getType();
        List<Airport> airports = gsonParser.fromJson(responsePayload, recipeListType);

        System.out.println("Displaying All Recipes");
        for(Airport airport : airports)
        {
            System.out.println(airport);
            System.out.println();
        }

    }
}
