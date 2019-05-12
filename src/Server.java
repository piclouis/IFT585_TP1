import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {
    final static String SERVER_NAME = "localhost";
    final static int PORT = 65000;
    final static int MESSAGE_LENGTH = 1021;

    private DatagramSocket socket;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

    public Server() throws IOException {
        socket = new DatagramSocket(PORT);
        System.out.println("Start server");
        read();
    }

    public void read() throws IOException {
        int noPacket = 0;
        int noLastPacket = 0;
        Timer timer;
        File file = new File("test");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        while (true) {
            byte[] message = new byte[1024];
            byte[] fileByteArray = new byte[MESSAGE_LENGTH];

            DatagramPacket receivedPacket = new DatagramPacket(message, message.length);
            socket.setSoTimeout(0);
            // Blocked
            socket.receive(receivedPacket);

            message = receivedPacket.getData();

            if (noPacket == 0)
                timer = new Timer();

            noPacket = ((message[0] & 0xff) << 8) + (message[1] & 0xff);

            InetAddress address = receivedPacket.getAddress();
            int port = receivedPacket.getPort();

            if(noPacket == (noLastPacket + 1)) {
                noLastPacket = noPacket;
                System.arraycopy(message, 3, fileByteArray, 0, MESSAGE_LENGTH);
                fileOutputStream.write(fileByteArray);
                System.out.println("Received no packet:" + noLastPacket);
            }
            else {
                System.out.println("Expected no packet: "
                        + (noLastPacket + 1) + " but received "
                        + noPacket + ".");
            }
            sendAck(noLastPacket, address, port);
        }
    }

    private void sendAck(int noPacket, InetAddress address, int port) throws IOException {
        byte[] ackPacket = new byte[2];
        ackPacket[0] = (byte) (noPacket >> 8);
        ackPacket[1] = (byte) (noPacket);
        DatagramPacket acknowledgment = new DatagramPacket(ackPacket, ackPacket.length, address, port);
        socket.send(acknowledgment);
        System.out.println("Sent ack: Sequence Number = " + noPacket);
    }
}
