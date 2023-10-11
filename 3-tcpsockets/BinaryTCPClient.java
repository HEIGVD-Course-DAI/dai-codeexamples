import java.io.*;
import java.net.*;

class BinaryTCPClient {
    public static void main(String args[]) {

        try (Socket socket = new Socket("localhost", 1234);
             var in = new BufferedInputStream(socket.getInputStream());
             var out = new BufferedOutputStream(socket.getOutputStream())) {

            for (int i = 0; i < 10; i++) {
                out.write(i);
                out.flush();
                System.out.println("Echo: " + in.read());
            }
        } catch (IOException e) {
            System.out.println("Client: exception: " + e);
        }
    }
}
