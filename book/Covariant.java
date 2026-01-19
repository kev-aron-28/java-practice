package book;

class Animal {
    public Animal getAnima() {
        return this;
    }
}

class Dog extends Animal {

    @Override
    public Dog getAnima() {
        return this;
    }
    
}

public class Covariant {
    
}
