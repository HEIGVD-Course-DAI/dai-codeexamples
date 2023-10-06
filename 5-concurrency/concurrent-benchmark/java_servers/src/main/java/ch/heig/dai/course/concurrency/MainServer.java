package ch.heig.dai.course.concurrency;

/**
 * Entry point to start all other types of servers.
 */
public class MainServer {

    /**
     * Main method: read command line arguments type and number of threads.
     */
    public static void main(String[] args) {
        try {
            String serverType = args[0];
            int port = Integer.parseInt(args[1]);
            switch (serverType) {
                case "singlethread" -> (new SingleThreadedServer()).serve(port);
                case "multithread" -> (new MultiThreadedServer()).serve(port);
                case "fixedthreadpool" -> (new FixedThreadPoolServer()).serve(port, Integer.parseInt(args[2]));
                case "cachedthreadpool" -> (new CachedThreadPoolServer()).serve(port);
                case "virtualthread" -> (new VirtualThreadServer()).serve(port);
                default -> throw new IllegalArgumentException("Invalid server type: " + serverType);
            };
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + "\n\n" +
                    "Usage: java -jar app.jar <serverType> <port> <numThreads>\n" +
                    "numThreads is only used for fixedthreadpool\n" +
                    "serverType can be singlethread, multithread, fixedthreadpool, cachedthreadpool, or virtualthread.");
        }
    }
}