
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GenericsCollections {

  static class Person implements Comparable<Person> {
      int id;
      public Person(int id) {
        this.id = id;
      }

      @Override
      public int compareTo(Person t) {
        return this.id - t.id;
      }

      @Override
      public String toString() {
        return "ID: " + this.id;
      }
  }

  public static void main(String args[]) {
    // List interface
    
    List<Number> array = new ArrayList<>();
    Number num = 1;
    array.add(num);

    List<Number> list = new LinkedList<>();

    // Set interface
    Set set = new HashSet<>();

    // Map interface
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Kevin");
    String value = map.get(1);

  // Queue interface
  
    Queue<Integer> queue = new ArrayDeque<>();

    // Compare

    List<Person> people = new ArrayList<>();
    people.add(new Person(3));
    people.add(new Person(1));
    people.add(new Person(2));

    Collections.sort(people);

    System.out.println(people);

    people.sort(Comparator.comparing(p -> p.id));


    List<String> names = new ArrayList<>(Arrays.asList("Kevin", "Aron", "Tapia", "Cruz"));

    Collections.sort(names);

    Collections.sort(names, Collections.reverseOrder());

    names.sort((a, b) -> a.length() - b.length());

    List<String> sorted = names.stream()
      .sorted()
      .toList();

  }
}
