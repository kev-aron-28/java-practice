
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exceptions {
  public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("input.txt");

            int chara;

            while((chara = reader.read()) != -1) {
              System.out.println((char) chara);
            }

            FileWriter writer = new FileWriter("input.txt");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
