package Server;

import Core.Menu;
import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import DTO.Airport;
import Exception.DaoException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import static Core.Menu.*;
public class TreadPerClient implements Runnable{
    private Socket dataSocket;
    AirportDaoInterface IAirportDao = new MySqlAirportDao();

    public TreadPerClient(Socket dataSocket) {
        this.dataSocket = dataSocket;
    }

    @Override
    public void run(){

        try
        {
            OutputStream out = dataSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));
            Packet incomingPacket = new Packet(ClientMenu.None, null);

            while(!incomingPacket.getRequestType().equals(ClientMenu.EXIT))
            {
                incomingPacket.readFromJSON(input.nextLine());
                System.out.println("\nReceived message: \n" + incomingPacket);

                CommandFactory factory = new CommandFactory();

                Command command = factory.createCommand(incomingPacket.getRequestType());
                Packet responsePacket = null;

                if(command != null)
                {
                    responsePacket = command.createResponse(incomingPacket);
                }
                else continue;

                writer.println(responsePacket.writeToJSON());
                writer.flush();
            }
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }
}
