import java.net.InetAddress;

public interface ISender {
    void send(String message);
    void receive();
}
