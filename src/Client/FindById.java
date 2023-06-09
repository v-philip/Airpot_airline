package Client;

import Core.Packet;
import DTO.Airport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import input.InputHandler;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FindById implements Request{
    public FindById(){

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser){
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the id of the airport that you want to Search for \n");
            String responsePayload = null;


            Client c = new Client();

            HashSet<Integer> ids= c.getAirportIDs();


            String payload =sc.nextLine();
            int id = Integer.parseInt(payload);
            if  (ids.contains(id))
            {
                requestPacket.setPayload(payload);

                writer.println(requestPacket.writeToJSON());
                writer.flush();

                String responseJSON = input.nextLine();
                responsePacket.readFromJSON(responseJSON);
                responsePayload = responsePacket.getPayload();
            }


            if(responsePayload == null)
            {
                System.out.println("There are no airport with that id");
                return;
            }

            else
            {
                Airport airport = gsonParser.fromJson(responsePayload, Airport.class);
                System.out.println();

                System.out.println("The details of the airport with that id is: ");
                System.out.println(airport);
            }
        }
    }
}
