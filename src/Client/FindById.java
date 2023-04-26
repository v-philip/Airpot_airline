package Client;

import Core.Packet;
import DTO.Airport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindById implements Request{
    public FindById(){

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser){
        {
            requestPacket.setPayload("");
            writer.println(requestPacket.writeToJSON());
            writer.flush();

            String responseJSON = input.nextLine();
            responsePacket.readFromJSON(responseJSON);

            String responsePayload = responsePacket.getPayload();
            if(responsePayload == null)
            {
                System.out.println("There are no airport with that id");
                return;
            }

            Airport airport = gsonParser.fromJson(responsePayload,Airport.class);
            System.out.println();

            System.out.println("The details of the airport with that id is: ");
            System.out.println(airport);

        }
    }
}
