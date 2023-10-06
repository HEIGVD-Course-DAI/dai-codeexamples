package ch.heig.dai.course.concurrency;

import java.io.*;
import java.net.*;
import static java.nio.charset.StandardCharsets.*;

/**
 * Client handler for a multi-threaded client. It implements
 * the Runnable interface and can be used in multi-threaded servers.
 */
public class RunnableClientHandler implements Runnable {
    final Socket socket;
    Worker worker;

    public RunnableClientHandler(Socket socket, Worker worker) {
        this.socket = socket;
        this.worker = worker;
    }

    /**
     * Read a HTTP request from the client, perform work, and send a HTTP response back.
     */
    public void run() {
        try(socket; // Not strictly necessary, but this is allowed.
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
            var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {
            
            System.out.println("Client connected");

            StringBuilder request = new StringBuilder();
            String response, line;
            while((line = in.readLine()) != null && line.length() != 0) {
                request.append(line);
             }
            response = worker.work(request.toString());
            out.write(response);
            out.flush();

        } catch (IOException e) {
            System.err.println("Error in ClientHandler: " + e.getMessage());
        }
    }
}