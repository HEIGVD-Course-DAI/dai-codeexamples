import java.io.*;

class FileInputStreamMain {
    public static void main(String[] args) throws IOException {
        var fis = new FileInputStream("FileInputStreamMain.java");
        int b; // is -1 if end of file or byte value 0-255
        while ((b = fis.read()) != -1) {
            System.out.print((char) b);
        }
        fis.close();
    }
}
