import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import static java.nio.charset.StandardCharsets.*;

class MulticastSender {
    final static String IPADDRESS = "239.1.2.3";
    final static int PORT = 44444;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {

            String message = "Hello group members!";
            byte[] payload = message.getBytes(UTF_8);
            InetSocketAddress dest_address = new InetSocketAddress(IPADDRESS, 44444);
            var packet = new DatagramPacket(payload, payload.length, dest_address);
            socket.send(packet);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
