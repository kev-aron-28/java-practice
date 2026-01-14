package book;

class SuperClass {
    public static void display() {
        System.out.println("SUPERCLASS CALLED");
    }
}

class SubClass extends SuperClass {
    public static void display(String[] args) {
        System.out.println("Subclass CALLED");
    }
}

public class Hiding {
    public static void main(String[] args) {
        SubClass c = new SubClass();

        c.display();
    }
}