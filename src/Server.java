import java.io.IOException;
import java.net.*;

public class Server extends Thread {
    final static int PORT = 65000;

    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer = new byte[256];

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

    public Server() throws IOException {
        createSocket(PORT);
        System.out.println("Start server");
    }

    private void createSocket(int port) throws IOException {
        socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        try {
            running = true;

            while (running) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                socket.send(packet);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
