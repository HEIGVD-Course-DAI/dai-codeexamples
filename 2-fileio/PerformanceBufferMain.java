import java.io.*;

class PerformanceBufferMain {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        // Write 10 MB of data to a file
        var bos = new BufferedOutputStream(new FileOutputStream("file.bin"));
        for (int i = 0; i < 10 * 1024 * 1024; i++) {
            bos.write(1);
        }
        bos.flush();
        bos.close();
        long end = System.currentTimeMillis();
        System.out.println("Write time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        // Read 10 MB of data from a file
        var bis = new BufferedInputStream(new FileInputStream("file.bin"));
        int b;
        while ((b = bis.read()) != -1) {
            // Do nothing
        }
        bis.close();
        end = System.currentTimeMillis();
        System.out.println("Read time:  " + (end - start) + " ms");
    }
}