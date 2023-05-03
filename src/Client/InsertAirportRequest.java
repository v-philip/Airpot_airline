package Client;

import Core.Packet;
import DTO.Airport;
import com.google.gson.Gson;
import input.InputHandler;
import input.Validation;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class InsertAirportRequest implements Request {
    public InsertAirportRequest() {

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser) {
        Scanner sc = new Scanner(System.in);

        String responsePayload = null;
        Airport newAirport =InputHandler.InsertAirport(Validation.Airport_Short_Form_REGEX, Validation.String_Regex);

        Gson gson = new Gson();

        String payload = gson.toJson(newAirport);


        Client c = new Client();

        requestPacket.setPayload(payload);

        writer.println(requestPacket.writeToJSON());
        writer.flush();

        String responseJSON = input.nextLine();
        responsePacket.readFromJSON(responseJSON);
        responsePayload = responsePacket.getPayload();


        if (responsePayload == null)
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
            c.updateHashSet(airport.getAirport_id());
        }
    }
}
