
import java.util.List;
import java.util.function.*;

public class LambdaE {
  @FunctionalInterface
  interface MyFunciton {
    int apply(int x);
  }

  public static void main(String[] args) {
    MyFunciton square = x -> x * x;
    System.out.println(square.apply(3));    

    // Predicate
    // Takes T and returns boolean

    Predicate<Integer> isEven = n -> n % 2 == 0;

    System.out.println(isEven.test(1));

    // Function
    // Takes T returns R
    Function<String, Integer> length = s -> s.length();

    // Consumer
    // Takes T returns void

    Consumer<String> printer = s -> System.out.println(s);
    printer.accept("Kevin");

    // Supplier
    // Takes nothing, returns T

    Supplier<Double> random = () -> Math.random();
    System.out.println(random.get());

    //BiFunction
    // Takes two arguments, T, U and returns R
    BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
    System.out.println(sum.apply(1, 2).toString());

    // Method references
    List<String> names = java.util.Arrays.asList("Kevin", "ARon");
    names.forEach(System.out::print);


    Predicate<String> startsWithA = s -> s.startsWith("A");
    Predicate<String> endsWithZ = s -> s.endsWith("Z");

    Boolean result = startsWithA.and(endsWithZ).test("Kevin");
    System.out.println(result);
  }
}
