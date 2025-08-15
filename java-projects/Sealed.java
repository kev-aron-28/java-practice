sealed class Animal permits Dog, Cat, WildAnimal {}

final class Dog extends Animal {}       // No further subclasses
sealed class Cat extends Animal permits PersianCat {} 
non-sealed class WildAnimal extends Animal {} // Anyone can extend

final class PersianCat extends Cat {}
class Tiger extends WildAnimal {}       // ✅ Allowed because WildAnimal is non-sealed

public class Sealed {
  
}
