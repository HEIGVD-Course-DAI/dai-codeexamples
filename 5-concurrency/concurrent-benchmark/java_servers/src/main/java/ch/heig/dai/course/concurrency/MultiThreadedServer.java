package ch.heig.dai.course.concurrency;

import java.io.*;
import java.net.*;

/**
 * A mult-threaded server that creates a new thread for each client.
 */
public class MultiThreadedServer {

    /**
     * Start the server on the given port and create a new thread for each client.
     * The client handler is responsible for closing the socket.
     */
    public void serve(int port) {
        System.out.println("Starting multi-threaded server on port: " + port);
        Worker worker = new Worker();

        try (var serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    var handler = new RunnableClientHandler(socket, worker);
                    Thread thread = new Thread(handler);
                    thread.start();
                } catch (IOException e) {
                    System.err.println("Error opening client socket: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening server socket: " + e.getMessage());
        }
    }
}