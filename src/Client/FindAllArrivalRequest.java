package Client;

import Core.Packet;
import DTO.Airport;
import DTO.Arrival;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindAllArrivalRequest implements Request{
    public FindAllArrivalRequest(){

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

        Type arrivalListType = new TypeToken<ArrayList<Arrival>>(){}.getType();
        List<Arrival> a = gsonParser.fromJson(responsePayload, arrivalListType);

        System.out.println("Displaying All Airport");
        for(Arrival ar : a)
        {
            System.out.println(ar);
            System.out.println();
        }

    }
}