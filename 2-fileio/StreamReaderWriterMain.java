import java.io.*;
import java.nio.charset.StandardCharsets;

class StreamReaderWriterMain {
    public static void main(String[] args) throws IOException {

        var reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream("StreamReaderWriterMain.java"),
                            StandardCharsets.UTF_8));

        var writer = new BufferedWriter(
                        new OutputStreamWriter(
                            new FileOutputStream("copy.txt"),
                            StandardCharsets.UTF_8));

        int c; // is -1 if end of file or char value 0-65535
        while ((c = reader.read()) != -1) {
            writer.write(c);
        }
        writer.flush(); 
        writer.close();
        reader.close();
    }
}