
import java.util.List;
import java.util.stream.Stream;

public class StreamsS {
  public static void main(String[] args) {
      List<String> names = List.of("Kevin", "Aron", "Tapia", "Cruz");

      long count = names.stream()
        .filter(n -> n.length() > 3)
        .map((a) -> a.toLowerCase())
        .count();

      Stream<Integer> s = Stream.of(1,2,3,4,5);

      Stream<Integer> evens = Stream.iterate(0, n -> n + 2);

      Stream<String> namesTransformed = names.stream()
        .map((a) -> a + 'a')
        ;

      List<String> t = namesTransformed.toList();


    }
}
