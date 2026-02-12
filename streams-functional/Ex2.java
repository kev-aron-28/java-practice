import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2 {
    public static void main(String[] args) {
        Stream<Integer> ints = Stream.of(1,2,3,5,6,34,2,4,32,1);
        IntSummaryStatistics summary = ints.collect(Collectors.summarizingInt(i -> i));
        
        System.out.println(summary.getAverage());
        System.out.println(summary.getMin());
    }    
}
