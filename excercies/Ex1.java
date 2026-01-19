package excercies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class User {
        int id;
        String name;
        int age;
        String country;

        public User(int age, String country, int id, String name) {
            this.age = age;
            this.country = country;
            this.id = id;
            this.name = name;
        }
    }

public class Ex1 {

    public static void main(String[] args) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader("excercies/Files/ex1.txt"));
        ) {
            String line;
            List<User> users = new ArrayList<>();
            
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                int id = Integer.parseInt(values[0]);
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                String country = values[3];

                User user = new User(age, country, id, name);
                users.add(user);
                System.out.println(values[2]);
            }

            List<User> adults = users.stream().filter(u -> u.age >= 18).toList();

            Map<String, List<User>> countries = users.stream().collect(
                Collectors.groupingBy(u -> u.country)
            );

            Map<String, Double> avgAg = users.stream().collect(
                Collectors.groupingBy(
                    u -> u.country,
                    Collectors.averagingInt(u -> u.age)
                )
            );

            Map<String, Long> nameFreq = users.stream().collect(
                Collectors.groupingBy(
                    u -> u.name,
                    Collectors.counting()
                )
            );

            List<User> sorted = 
                users.stream().sorted(
                    Comparator.comparingInt((User u) -> u.age).reversed()
                        .thenComparing(u -> u.name)
                ).toList();



        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
