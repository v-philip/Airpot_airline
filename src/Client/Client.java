package Client;

import Core.Menu;
import Core.Packet;
import com.google.gson.Gson;
import input.InputHandler;
import input.Validation;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static Core.Menu.*;

public class Client {
    private static final Gson gsonParser = new Gson();
    public static void main(String[] args)
    {

        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server.Server :" + socket.getPort() );



            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(new OutputStreamWriter(os));   // true => auto flush buffers

            InputStream in = socket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));

            Packet requestPacket = null;
            Packet receivedResponsePacket = new Packet(ClientMenu.None, "");

            boolean flag = true;

            while(flag)
            {
                System.out.println("1 to Exit ");
                System.out.println("2 to Print all airport");
                System.out.println("3 to find airpot by id");
                System.out.println("4 to filter airport");

                ClientMenu choice = getChoice();
                if (choice == ClientMenu.EXIT)
                    flag = false;
                else
                {
                    requestPacket = new Packet(choice, null);
                    RequestFactory requestFactory = new RequestFactory();
                    Request request = requestFactory.createRequest(requestPacket.getRequestType());
                    request.handelRequest(requestPacket, receivedResponsePacket, socketWriter, input, gsonParser);
                }
            }
            System.out.println("The app have exited");
            socket.close();
        }
        catch(IOException e)
        {
            System.out.println("Client message: IOException: "+e);
        }
    }

    private static ClientMenu getChoice()
    {
        String userInput = InputHandler.getAndValidateEnumOptions(
                Validation.WHOLE_NUMBER_REGEX,
                ClientMenu.values().length - 2,
                "(0-3)");

        return ClientMenu.values()[Integer.parseInt(userInput)];
    }
}
