import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import static java.nio.charset.StandardCharsets.*;

public class BroadcastReceiver {
    final static int PORT = 44444;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength(), UTF_8);

            System.out.println("Received message: " + message + " from " + packet.getAddress() + ", port " + packet.getPort());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
