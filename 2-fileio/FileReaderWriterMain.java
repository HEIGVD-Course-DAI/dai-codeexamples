import java.io.*;

class FileReaderWriterMain {
    public static void main(String[] args) throws IOException {
        var reader = new FileReader("FileReaderWriterMain.java");
        var writer = new FileWriter("copy.txt");
        int c; // is -1 if end of file or char value 0-65535
        while ((c = reader.read()) != -1) {
            writer.write(c);
        }
        writer.flush(); 
        writer.close();
        reader.close();
    }
}