import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class UDPTest {
    Client client;
    @Before
    public void setup() throws IOException {
        //new Server().start();
        client = new Client();
    }

    @Test
    public void send() throws IOException {
        client.send();
    }

    @After
    public void tearDown() throws IOException {
        client.close();
    }
}
