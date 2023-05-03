package Test;

import Core.Menu;
import Core.Packet;
import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import Exception.DaoException;
import Server.FindAirportByIdCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindAirportByIdCommandTest {

    private FindAirportByIdCommand findAirportByIdCommand;

    @BeforeEach
    void setUp() {
        AirportDaoInterface airportDao = new MySqlAirportDao();
        findAirportByIdCommand = new FindAirportByIdCommand();
    }

    @Test
    void createResponse_withValidId_returnsPacketWithAirportJson() throws DaoException {
        // Arrange
        int validId = 1;
        String expectedResponseString = "{\"airportId\":1,\"shortForm\":\"LAX\",\"city\":\"Los Angeles\",\"country\":\"USA\"}";
        Packet incomingPacket = new Packet(Menu.ClientMenu.FIND_BY_ID, Integer.toString(validId));

        // Act
        Packet responsePacket = findAirportByIdCommand.createResponse(incomingPacket);

        // Assert
        assertEquals("FIND_AIRPORT_BY_ID", responsePacket.getRequestType());
        assertEquals(expectedResponseString, responsePacket.getPayload());
    }

}