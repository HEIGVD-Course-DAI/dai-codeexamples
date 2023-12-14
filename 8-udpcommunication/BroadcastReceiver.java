import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadcastReceiver {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(44444)) {

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            byte[] payload = packet.getData();
            String message = new String(payload, 0, packet.getLength());

            System.out.println("Received message: " + message + " from " + packet.getAddress() + ", port " + packet.getPort());
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
