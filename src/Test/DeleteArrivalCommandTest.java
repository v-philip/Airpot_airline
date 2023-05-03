package Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Core.Menu;
import DAO.MySqlArrivalDao;
import Server.DeleteArrivalCommand;
import org.junit.jupiter.api.Test;

import Core.Packet;
import DAO.ArrivalDaoInterface;
import Exception.DaoException;

public class DeleteArrivalCommandTest {


    @Test
    public void testCreateResponse() throws DaoException {
        // Create a mock ArrivalDaoInterface
        MySqlArrivalDao daoMock = new  MySqlArrivalDao() {
            @Override
            public String DeleteByIdJson(String id) throws DaoException {
                // Return a dummy response string
                return "success";
            }
        };

        // Create a new DeleteArrivalCommand with the mock DAO
        DeleteArrivalCommand command = new DeleteArrivalCommand();

        // Create a new incoming packet
        Packet incomingPacket = new Packet(Menu.ClientMenu.DELETE_BY_ID, "1");

        // Call the createResponse method and get the response packet
        Packet responsePacket = command.createResponse(incomingPacket);

        // Check that the response payload matches the expected string
        assertEquals("success", responsePacket.getPayload());
    }
}