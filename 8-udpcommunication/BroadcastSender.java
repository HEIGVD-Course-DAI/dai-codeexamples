import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;

class BroadcastSender {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);

            String message = "Hello from the other side!";
            byte[] payload = message.getBytes();
            var packet = new DatagramPacket(payload, payload.length, 
                                            InetAddress.getByName("255.255.255.255"), 
                                            44444);
            socket.send(packet);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}