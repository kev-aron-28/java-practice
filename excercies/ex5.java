
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

class User {
    public int id;
    public String name;
    public int age;
    public String country;
    
    public User(int id, String name, int age, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
    }
}


class Task implements Runnable {
    private String file;
    List<User> users;
    public Task(String file, List<User> users) {
        this.file = file;
        this.users = users;
    }

    @Override
    public void run() {

        try (
            BufferedReader bf = new BufferedReader(new FileReader(this.file))
        ) {
            String line;
            while ((line = bf.readLine()) != null) { 
                String values[] = line.split(",");

                if(values.length != 4) continue;

                int id = Integer.parseInt(values[0]);

                String name = values[1];

                int age = Integer.parseInt(values[2]);

                String country = values[3];

                User user =  new User(id, name, age, country);

                users.add(user);
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }
}

public class ex5 {
    public static void main(String[] args) {
        
        List<User> users = Collections.synchronizedList(new ArrayList<>());
        
        ExecutorService ex = Executors.newFixedThreadPool(3);

        String files[] = { "excercies/1.txt", "excercies/2.txt", "excercies/3.txt" };
    
        for(String file : files) {
            ex.execute(new Task(file, users));
        }

        ex.shutdown();

        Map<String, Long> totalPerCountry = users.stream().collect(
            Collectors.groupingBy(u -> u.country, Collectors.counting())
        );

        Map<String, Long> nameFreq = users.stream().collect(
            Collectors.groupingBy(u -> u.name, Collectors.counting())
        );
        
    }
}