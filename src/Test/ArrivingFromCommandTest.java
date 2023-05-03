package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Core.Menu;
import Server.ArrivingFromCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Core.Packet;
import DAO.ArrivalDaoInterface;
import Exception.DaoException;
import DAO.MySqlArrivalDao;
import com.google.gson.Gson;

public class ArrivingFromCommandTest {

    private ArrivingFromCommand arrivingFromCommand;

    @BeforeEach
    public void setup() {
        ArrivalDaoInterface iArrival = new MySqlArrivalDao();
        arrivingFromCommand = new ArrivingFromCommand();
    }

    @Test
    public void testCreateResponse() throws DaoException {
        Packet packet = new Packet(Menu.ClientMenu.ARRIVING_FROM, "1");
        String expectedResponse = "{\"flight_id\":\"AA100\",\"arrival_airport\":\"Miami International Airport\"}";
        Packet responsePacket = arrivingFromCommand.createResponse(packet);
        assertEquals(expectedResponse, responsePacket.getPayload());
    }
}