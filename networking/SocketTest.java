
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
        try (
            var s = new Socket("time-a.nist.gov", 13);
            var in = new Scanner(s.getInputStream())
        ) {
            while(in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

