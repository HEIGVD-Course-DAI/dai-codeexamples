import java.io.*;

class PerformanceBytesMain {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        // Write 10 MB of data to a file
        var fos = new FileOutputStream("file.bin");
        for (int i = 0; i < 10 * 1024 * 1024; i++) {
            fos.write(1);
        }
        fos.close();
        long end = System.currentTimeMillis();
        System.out.println("Write time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        // Read 10 MB of data from a file
        var fis = new FileInputStream("file.bin");
        int b;
        while ((b = fis.read()) != -1) {
            // Do nothing
        }
        fis.close();
        end = System.currentTimeMillis();
        System.out.println("Read time:  " + (end - start) + " ms");
    }
}