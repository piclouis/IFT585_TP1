import java.net.InetAddress;

public interface IReceiver {
    void sendAck(int sequenceNumber, InetAddress address, int port);
    void receive();
}
