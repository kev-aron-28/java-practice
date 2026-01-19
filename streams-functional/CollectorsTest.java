import java.util.*;
import java.util.stream.Collectors;

public class CollectorsTest {
    public static void main(String args[]) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Daniel", "Eve");

        List<String> list = names.stream().filter(s -> s.length() > 3).collect(Collectors.toList());

        System.out.println(list);

        Set<String> set = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        System.out.println(set);


        long count = names.stream()
                .filter(n -> n.startsWith("D"))
                .collect(Collectors.counting());
        System.out.println("counting: " + count);
    
        int totalLength = names.stream()
                .collect(Collectors.summingInt(String::length));
        System.out.println("summingInt: " + totalLength);

        double averageLength = names.stream()
                .collect(Collectors.averagingInt(String::length));
        System.out.println("averagingInt: " + averageLength);

        IntSummaryStatistics stats = names.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println("summarizingInt: " + stats);

        Map<Integer, List<String>> grouped = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("groupingBy: " + grouped);

        Map<Integer, Long> countByLength = names.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("groupingBy + counting: " + countByLength);
        
        Map<Boolean, List<String>> partitioned = names.stream()
                .collect(Collectors.partitioningBy(n -> n.length() > 4));
        System.out.println("partitioningBy: " + partitioned);
    }
}