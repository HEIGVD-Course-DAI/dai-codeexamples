import java.io.*;
import java.net.*;

class BinaryTCPServer {
    public static void main(String args[]) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {

                try (Socket socket = serverSocket.accept();
                     var in = new BufferedInputStream(socket.getInputStream());
                     var out = new BufferedOutputStream(socket.getOutputStream())) {

                    int i;
                    while ((i = in.read()) != -1) {
                        out.write(i);
                        out.flush();
                    }
                
                } catch (IOException e) {
                    System.out.println("Server: socket ex.: " + e);
                }
            } // while(true)

        } catch (IOException e) {
            System.out.println("Server: server socket ex.: " + e);
        }
    }
}