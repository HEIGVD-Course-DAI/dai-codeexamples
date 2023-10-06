package ch.heig.dai.course.concurrency;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * A mult-threaded server using a CachedThreadPool executor.
 */
public class CachedThreadPoolServer {

    /**
     * Start the server on the given port and run the client handler on a cached thread pool.
     * The client handler is responsible for closing the socket.
     */
    public void serve(int port) {
        System.out.println("Starting cached thread pool server on port: " + port);
        Worker worker = new Worker();

        try (var serverSocket = new ServerSocket(port);
             ExecutorService executor = Executors.newCachedThreadPool()) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    var handler = new RunnableClientHandler(socket, worker);
                    executor.execute(handler);
                } catch (IOException e) {
                    System.err.println("Error opening client socket: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening server socket: " + e.getMessage());
        }
    }
}