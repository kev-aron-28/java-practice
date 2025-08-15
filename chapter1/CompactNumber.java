
import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumber {
  public static void main(String[] args) {
      NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

      System.out.println(fmt.format(42000));
  }
}
