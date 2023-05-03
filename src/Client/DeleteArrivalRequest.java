package Client;

import Core.Packet;
import DTO.Airport;
import DTO.Arrival;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DeleteArrivalRequest implements Request {
    public DeleteArrivalRequest(){

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id airline you want to delete");
        String responsePayload = null;


        Client c = new Client();

        HashSet<Integer> ids= c.getAirportIDs();


        String payload =sc.next();


            requestPacket.setPayload(payload);

            writer.println(requestPacket.writeToJSON());
            writer.flush();

            String responseJSON = input.nextLine();
            responsePacket.readFromJSON(responseJSON);
            responsePayload = responsePacket.getPayload();



        if(responsePayload == null)
        {
            System.out.println("There are no airport with that id");
            return;
        }

        else
        {
//            Airport airport = gsonParser.fromJson(responsePayload, );
            System.out.println(responsePayload);


        }

    }
}
