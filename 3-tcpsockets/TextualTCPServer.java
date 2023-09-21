import java.io.*;
import java.net.*;
import static java.nio.charset.StandardCharsets.*;

class TexualTCPServer {
    public static void main(String args[]) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {

                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {

                    String line;
                    while ((line = in.readLine()) != null) {
                        // There are two errors here!
                        out.write(line);
                    }
                
                } catch (IOException e) {
                    System.out.println("Server: socket ex.: " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Server: server socket ex.: " + e);
        }
    }
}