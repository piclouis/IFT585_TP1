import java.io.IOException;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buffer;

    public Client() throws IOException {
        createSocket(Server.PORT);
        address = InetAddress.getByName("localhost");
    }

    private void createSocket(int port) throws IOException {
        socket = new DatagramSocket();
    }

    private void create() {

    }

    private String read() throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public String send(String msg) throws IOException {
        buffer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length,address, Server.PORT);
        socket.send(packet);
        return read();
    }

    public void close() throws IOException {
        send("end");
        socket.close();
    }
}
