
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerC {
  public static void main(String[] args) {
    Scanner scanner = new Scanner("Kevin Hola Como Estas").useDelimiter("H");

    while(scanner.hasNext()) {
      System.out.println(scanner.next());
    }

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter age: ");

    if(sc.hasNext()) {
      System.out.println(sc.next());
    }

    try {
      Scanner sf = new Scanner(new File("input.txt"));
      
      while(sf.hasNext()) {
        System.out.println(sf.next());
      }

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
