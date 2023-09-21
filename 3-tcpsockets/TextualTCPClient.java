import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class TextualTCPClient {
    public static void main(String args[]) {

        try (Socket socket = new Socket("localhost", 1234)) {
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            for (int i = 0; i < 10; i++) {
                // There are two errors here!
                out.write("Hello " + i);
                System.out.println("Echo: " + in.readLine());
            }
        } catch (IOException e) {
            System.out.println("Client: exception while using client socket: " + e);
        }
    }
}