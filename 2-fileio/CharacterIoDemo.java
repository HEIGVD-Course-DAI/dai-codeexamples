import java.io.*;
import java.nio.charset.*;

/**
 * This program shows the effect of character encodings in Java. It shows that while
 * Java uses Unicode once characters or Strings are created in memory, a translation
 * needs to happen when bytes are converted into characters, and the other way around.
 * 
 * The program also highlight typical problems that arise if the developer does not
 * control character encodings. Problems that manifest themselves by seeing '?' or
 * other strange characters appear in text messages.
 * 
 */
public class CharacterIoDemo {
	
	public static void main(String[] args) throws IOException {
        var main = new CharacterIoDemo();
		
		String message = "ABC élève　広島";
        main.encodeAndDecode(message, StandardCharsets.US_ASCII);
        main.encodeAndDecode(message, StandardCharsets.ISO_8859_1);
        main.encodeAndDecode(message, StandardCharsets.UTF_8);
        main.encodeAndDecode(message, StandardCharsets.UTF_16LE);
        main.encodeAndDecode(message, StandardCharsets.UTF_16);
	}

    private void encodeAndDecode(String message, Charset encoding) {
        byte[] bytes = message.getBytes(encoding);
        String decodedMessage = new String(bytes, encoding);
        String utf8DecodedMessage = new String(bytes, StandardCharsets.UTF_8);

		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("When I encode '" + message + "' (" + message.length() + " chars) with encoding " + encoding + ", I generate " + bytes.length + " bytes.");
		System.out.println("If I decode the result with the same encoding (" + encoding + "), I get: " + decodedMessage);
		System.out.println("If I decode the result with the default encoding (UTF-8), I get: " + utf8DecodedMessage);
    }
}
