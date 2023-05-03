package Server;

import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import Exception.DaoException;

public class Server {


    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080


            System.out.println("Server.Server: Server.Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {

                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server.Server: Client " + clientNumber + " has connected.");

                System.out.println("Server.Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server.Server: Port# of this server: " + socket.getLocalPort());

                TreadPerClient runnable = new TreadPerClient(socket);

                Thread t = new Thread(runnable); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server.Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server.Server: Listening for further connections...");
            }
        } catch (IOException e) {
            System.out.println("Server.Server: IOException: " + e);
        }
        System.out.println("Server.Server: Server.Server exiting, Goodbye!");

    }
}


