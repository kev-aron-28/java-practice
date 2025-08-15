public class Switch {
  public static void main(String args[]) {
    boolean n = switch(1) {
      case 1 -> false;
      default -> true;
    };
    int i = 1;

    switch(i) {
      case 1 -> {
        System.out.println("HOLa");
      }
      default -> System.out.println("KEVIn");
    }
  };

  private void swWithYield() {
    int i = 1;

    int val = switch (1) {
      case 1 -> {
        yield 1;
      }
      default -> {
        yield 2;
      }
    };

  }
}
