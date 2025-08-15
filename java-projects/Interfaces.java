interface Moveable {
  default  void move() {
    System.out.println("Moving");
  }
  static int m1() { return 1; }
}

interface Logger {
    private void log(String msg) {
        System.out.println("LOG: " + msg);
    }

    default void info(String msg) {
        log("INFO: " + msg);
    }

    default void error(String msg) {
        log("ERROR: " + msg);
    }
}

class App implements Logger {}


public class Interfaces {
  public static void main(String[] args) {
      
  }
}
