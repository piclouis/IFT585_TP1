import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Client {
    final static int MESSAGE_LENGTH = 1021;
    final static int WINDOW_SIZE = 5;

    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buffer;

    public Client() throws IOException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(Server.SERVER_NAME);

        File file = new File("test");
        byte[] fileByteArray = new byte[(int) file.length()];
    }

    public void send() throws IOException {
        int noAck = 0;
        int nbPackets = 20;
        int window = WINDOW_SIZE;

        while(noAck < nbPackets) {
            int noPacket;
            for(noPacket = noAck; noPacket < window; ++noPacket) {
                System.out.println("Send no packet:" + noPacket);
            }

            noAck = 3;
            System.out.println("Last Ack:" + noAck);
            window = (window < nbPackets) ? window + (noAck - noPacket) : nbPackets;
        }
    }

    private void read() {
        while(true) {

        }
    }

    public void close() throws IOException {
        socket.close();
    }
}
