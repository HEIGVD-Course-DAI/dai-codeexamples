package ch.heig.dai.course.concurrency;

import java.io.*;
import java.net.*;

/**
 * A single-threaded server that handles one client at a time.
 */
public class SingleThreadedServer {

    /**
     * Start the server on the given port and directly call the ClientHandler on the same thread for each client.
     * The client handler is responsible for closing the socket.
     */
    public void serve(int port) {
        System.out.println("Starting single-threaded server on port: " + port);
        Worker worker = new Worker();

        try (var serverSocket = new ServerSocket(port)) {
            var handler = new SimpleClientHandler();    
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    handler.handle(socket, worker);
                } catch (IOException e) {
                    System.err.println("Error using client socket: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error opening server socket: " + e.getMessage());
        }
    }
}