package Client;

import Core.Packet;
import DTO.Airport;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class ArrivingFromRequest implements Request {
    public ArrivingFromRequest() {

    }

    @Override
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the airport that you want to Search for");
        String responsePayload = null;


        Client c = new Client();

        HashSet<Integer> ids = c.getAirportIDs();


        String payload = sc.next();
        int id = Integer.parseInt(payload);
        if (ids.contains(id)) {
            requestPacket.setPayload(payload);

            writer.println(requestPacket.writeToJSON());
            writer.flush();

            String responseJSON = input.nextLine();
            responsePacket.readFromJSON(responseJSON);
            responsePayload = responsePacket.getPayload();
        }


        if (responsePayload == null) {
            System.out.println("There are no airport with that id");
            return;
        } else {
            System.out.println(responsePayload);
        }
    }

}
