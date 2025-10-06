import java.util.*;

public class Main {
	public static void main(String args[]) {
		System.out.println("Hello world");
		// Set
		Set<String> set = new HashSet<>();
		set.add("Kevin");
		set.add("Aron");
		set.add("Tapia");
		set.add("Cruz");

		for(String s : set) {
			System.out.println(s);
		}
		
		Set<Integer> sn = new TreeSet<>();

		sn.add(1);
		sn.add(2);
		sn.add(10);
		// Ordered elements
		// And no duplicates
		System.out.println("Treeset: " + sn);
		
		// Combines both a DoubleLinkedList and a HashSet
		// Maintains insertion order
		Set<Integer> lhs  = new LinkedHashSet<>();
	}



}
