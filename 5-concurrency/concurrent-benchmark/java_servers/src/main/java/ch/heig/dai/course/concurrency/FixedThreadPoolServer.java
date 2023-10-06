package ch.heig.dai.course.concurrency;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * A mult-threaded server that creates a new thread for each client.
 */
public class FixedThreadPoolServer {

    /**
     * Start the server on the given port and run the client handler on a thread pool.
     * The client handler is responsible for closing the socket.
     */
    public void serve(int port, int numThreads) {
        System.out.println("Starting fixed thread pool server on port: " + port + " with " + numThreads + " threads");
        Worker worker = new Worker();

        try (var serverSocket = new ServerSocket(port);
             ExecutorService executor = Executors.newFixedThreadPool(numThreads)) {
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