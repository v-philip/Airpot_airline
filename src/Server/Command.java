package Server;

import Core.Packet;

public interface Command {
    public Packet createResponse(Packet incomingPacket);
}
