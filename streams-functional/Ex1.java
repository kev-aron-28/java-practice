import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Ex1 {
    public static void main(String[] args) {
        Stream<String> echos = Stream.generate(() -> "ECHO").limit(10);

        // echos.forEach((v) -> System.out.println(v));

        Stream<Integer> integers = Stream.iterate(0, n -> n < 100, n -> n + 1).limit(10).skip(1);

        // System.out.println(integers.toList());

        Stream.of("A,B", "C,D")
            .flatMap(s -> Arrays.stream(s.split(",")));

        Stream.of("A,B", "C,D")
            .mapMulti((str, consumer) -> {
                for(String s : str.split(",")) {
                    consumer.accept(s);
                }
            });
    
        // Stream.iterate(0, n -> n + 1).dropWhile(n -> n < 5).forEach(System.out::println);

        Stream<Integer> numbers = Stream.of(4,3,5,1,3,5,6);

        numbers.sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}
