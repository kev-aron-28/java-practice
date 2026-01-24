import java.util.Arrays;
import java.util.stream.Stream;

public class Ex1 {
    public static void main(String[] args) {
        Stream<String> echos = Stream.generate(() -> "ECHO").limit(10);

        // echos.forEach((v) -> System.out.println(v));

        Stream<Integer> integers = Stream.iterate(0, n -> n < 100, n -> n + 1).limit(10).skip(1);

        System.out.println(integers.toList());

        Stream.of("A,B", "C,D")
            .flatMap(s -> Arrays.stream(s.split(",")))
            .forEach(System.out::println);

        Stream.of("A,B", "C,D")
            .mapMulti((str, consumer) -> {
                for(String s : str.split(",")) {
                    consumer.accept(s);
                }
            })
            .forEach(System.out::println);
    
        Stream.iterate(0, n -> n + 1).dropWhile(n -> n < 5).forEach(System.out::println);
    }
}
