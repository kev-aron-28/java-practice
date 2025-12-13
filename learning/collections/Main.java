import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String args[]) {
		// List: Ordered list allows duplicated 
	
		// ArrayList
		// List<String> arrayList = new ArrayList<>();
		// LinkedLIst
		// List<String> linkedList = new LinkedList();


		// Set: Collection without duplicates
		List<String> arrayList = new ArrayList<>(List.of("Ana", "Luis", "Pedro"));

		Set<Integer> set = new HashSet<>(Set.of(5, 10, 5));
		
		Map<String, Integer> map = new HashMap<>();
		map.put("Ana", 20);
		map.put("Luis", 30);
		map.put("Pedro", 23);

		List<String> list = new ArrayList<>();

		for(String value : list) {

		}

		for(int i = 0; i < list.size(); i++) {}

		// list.stream().forEach(); 

		List<Double> lk = new LinkedList<>();
		lk.add(2.5);
		lk.add(7.9);
		lk.add(1.1);

		Set<String> treeSet = new TreeSet<>(Set.of("Manzana", "PEra", "Banana"));

		Map<Integer, String> students = new HashMap<>(Map.of(1, "Ana", 2, "Luis", 3, "Pedro"));

		List<String> name = List.of("");

		for(String n : name) {

		}

		name.stream().forEach(el -> {});

		for(int i = 0; i < name.size(); i++) {

		}
		List<String> cities = new ArrayList<>(List.of("Madrid", "Paris", "Londres"));

		Collections.sort(cities);

		Set<Integer> numbers = new TreeSet<>(Set.of(7,3,9,1,5));
		for(Integer i : numbers) {
			System.out.println(i);
		}

		Map<String, Integer> ages = new HashMap<>();
		ages.put("ana", 22);
		ages.put("luis", 19);
		ages.put("maria", 30);

		for(Map.Entry<String, Integer> entry : ages.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue());
		}

		List<Integer> nums = new ArrayList<>(List.of(1,2,3,4,5,6,7,8));

		Iterator<Integer> numsIterator = nums.iterator();

		while(numsIterator.hasNext()) {
			Integer value = numsIterator.next();

			if(value % 2 == 0) numsIterator.remove();
		}

		List<String> nombres = new ArrayList<>(List.of("ana", "pedro", "maria"));

		Collections.reverse(nombres);

		Collections.shuffle(nombres);

		List<Integer> numbersFilter = List.of(3,10,5,2,8,1);

		numbersFilter.stream().filter(val -> val > 5).collect(Collectors.toList());

		List<String> palagras = List.of("java", "python", "go", "rust");

		List<Integer> palabrasLen = palagras.stream().map(val -> val.length()).collect(Collectors.toList());

		List<Integer> numsDuplicates = List.of(1,2,3,4,5,6,1,2,5);

		Set<Integer> noDuplicates = numsDuplicates.stream().collect(Collectors.toSet());

		String frase = palagras.stream()
        .collect(Collectors.joining(" "));

		List<String> lenguages = List.of("Java", "Python", "Go");
		Map<String, Integer> lenguagesLen = lenguages.stream().collect(Collectors.toMap(l -> l, l -> l.length()));

		List<String> palabras = List.of("uno", "dos", "tres", "cuatro", "cinco", "seis");

		Map<Integer, List<String>> group = palabras.stream().collect(Collectors.groupingBy(val -> val.length()));

		List<Integer> numbersPartition = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Map<Boolean, List<Integer>> partition = numbersPartition.stream().collect(Collectors.partitioningBy(val -> val % 2 == 0));

		List<String> nombres2 = new ArrayList<>(List.of("Ana", "Mario", "Luis", "Alfredo", "Luisa"));

		nombres2.removeIf(val -> val.startsWith("A"));


	}



}
