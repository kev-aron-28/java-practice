
import java.io.ByteArrayInputStream;

public class Bais {
  public static void main(String args[]) {
    byte[] buffer = { 1, 3, 4 };

    try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer)){
      int data = bais.read();

      while(data != -1) {
        System.out.println(data);

        data = bais.read();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
