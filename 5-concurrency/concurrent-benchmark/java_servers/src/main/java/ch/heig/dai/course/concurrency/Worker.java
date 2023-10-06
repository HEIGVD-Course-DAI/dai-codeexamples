package ch.heig.dai.course.concurrency;

import java.io.*;

public class Worker {
    int SLEEP_DURATION = 100;
    int NUM_COMPUTE_ITERATIONS = 100_000;

    public String work(String request) throws IOException {
        // Perform one or several tasks
        if (request.contains("/compute")) workCompute();
        if (request.contains("/sleep"))  workSleep();
        return "HTTP/1.0 200 OK\r\nContent-Length: 3\r\nContent-Type: text/plain\r\n\r\nX\r\n";
    }

    public void workCompute() {
        // Compute something
        double result = 0.0;
        for (int i = 0; i < NUM_COMPUTE_ITERATIONS; i++) {
            result += Math.random();
        }
        System.out.println("  ==> Compute finished");
    }

    public void workSleep() {
        try {
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            System.err.println("Error during sleep");
        }
        System.out.println("  ==> Sleep finished");
    }
}
