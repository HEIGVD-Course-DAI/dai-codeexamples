import java.io.*;

class FileOutputStreamMain {
    public static void main(String[] args) throws IOException {
            FileOutputStream fos = new FileOutputStream("file.bin");
            for (int i = 0; i < 255; i++) {
                fos.write(i);
            }
            fos.close();
    }
}