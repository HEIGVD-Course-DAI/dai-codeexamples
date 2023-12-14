import java.io.IOException;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.net.InetSocketAddress;
import java.net.DatagramPacket;


class MulticastSender {
    final static String IPADDRESS = "239.1.2.3";
    final static int PORT = 44444;

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket()) {
            InetSocketAddress dest_address = new InetSocketAddress(IPADDRESS, 44444);

            String message = "This is a multicast message!";
            byte[] payload = message.getBytes(StandardCharsets.UTF_8);
            var packet = new DatagramPacket(payload, payload.length, dest_address);
            socket.send(packet);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}