import java.util.Arrays;

public class Arrays {
  public static void main(String args[]) {
    int ages[] = { 1, 2, 3 };
    
    for (int elem : ages) {
      System.out.println(elem);
    }

    int len = ages.length;

    int ages1[][] = new int[3][2];

    int copy[] = Arrays.copyOf(ages, len);

    System.out.println(copy.toString());
  }
}
