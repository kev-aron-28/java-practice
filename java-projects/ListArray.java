
import java.util.ArrayList;
import java.util.List;

public class ListArray {
  public static void main(String[] args) {
      List<String> list = new ArrayList<>();
      list.add("1");
      list.add("2");
      list.add("3");
      
      list.set(0, "0");

      System.out.println(list);

  }
}
