public class Sbuilder {
  public static void main(String[] args) {
    String s = "The ";
    s += " quick";

    s.concat("s");

    System.out.println(s);
  }
}
