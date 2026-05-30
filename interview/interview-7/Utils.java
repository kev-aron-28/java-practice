package interview.interview-7;

import java.util.Arrays;
import java.util.List;

public class Utils {
    @SafeVarargs
    public static <T> List<T> toList(T... elements) {
        return Arrays.asList(elements);
    }
}
