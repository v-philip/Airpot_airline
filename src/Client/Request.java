package Client;

import Core.Packet;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.Scanner;

public interface Request {
    public void handelRequest(Packet requestPacket, Packet responsePacket, PrintWriter writer, Scanner input, Gson gsonParser);
}
