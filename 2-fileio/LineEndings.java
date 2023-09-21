import java.io.*;
import java.nio.charset.StandardCharsets;

class LineEndings {
  public static void main(String[] args) throws IOException {
    var is = new BufferedReader(new InputStreamReader(new FileInputStream("LineEndings.java"), StandardCharsets.UTF_8));
    var os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("copy.txt"), StandardCharsets.UTF_8));
    String line;
    while ((line = is.readLine()) != null) {
        // Careful: line does not contain end-of-line characters
        os.write(line + "\n");
    }
    os.flush();
    os.close();
    is.close();
  }
}