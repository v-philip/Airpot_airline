package Test;

import static org.junit.jupiter.api.Assertions.*;

import Core.Menu;
import Server.FindAllAirportCommand;
import org.junit.jupiter.api.Test;

import Core.Packet;

class FindAllAirportCommandTest {

    @Test
    void testCreateResponse() {
        FindAllAirportCommand cmd = new FindAllAirportCommand();
        Packet inputPacket = new Packet(Menu.ClientMenu.FIND_ALL_AIRPORT, "");

        Packet outputPacket = cmd.createResponse(inputPacket);

        assertNotNull(outputPacket.getPayload());
    }

}