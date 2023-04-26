package Server;

import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import Exception.DaoException;

public class Server {
    public static void main(String[] args)
    {
        try
        {
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
        } catch (IOException e)
        {
            System.out.println("Server.Server: IOException: " + e);
        }
        System.out.println("Server.Server: Server.Server exiting, Goodbye!");

    }

    public void start()
    {


    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            AirportDaoInterface IAirportDao = new MySqlAirportDao();
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server.Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("1"))
                    {
                        LocalTime time =  LocalTime.now();
                        socketWriter.println(time);  // sends current time to client
                    }
                    else if (message.startsWith("2"))
                    {
                        String str2 = IAirportDao.FindAirportIdsJson(2);
                        message ="Aiport found : " +str2;
                        socketWriter.println(message);  // send message to client
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            catch(DaoException e) {
                e.printStackTrace();
            }

            System.out.println("Server.Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }
}
