package ch.heig.dai.course.concurrency;

import java.io.*;
import java.net.*;
import static java.nio.charset.StandardCharsets.*;

/**
 * Client handler for a single threaded client. It does not implement
 * the Runnable interface and cannot be used in multi-threaded servers.
 */
public class SimpleClientHandler {

    /**
     * Read a HTTP request from the client, perform work, and send a HTTP response back.
     */
    public void handle(Socket socket, Worker worker) {
        try(socket; // Not strictly necessary, but this is allowed.
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
            var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {
            
            System.out.println("Client connected");

            StringBuilder request = new StringBuilder();
            String line;
            while((line = in.readLine()) != null && line.length() != 0) {
                request.append(line);
             }
            String response = worker.work(request.toString());
            out.write(response);
            out.flush();

        } catch (IOException e) {
            System.err.println("Error in ClientHandler: " + e.getMessage());
        }
    }
}