class Strings {
  public static void main(String[] args) {
    String s1 = "abc";
    String s2 = "abc";
    
    System.out.println(s1 == s2); //prints: true
    System.out.println("abc" == s1); //prints: true

    String o1 = new String("abc");
    String o2 = new String("abc");

    System.out.println(o1 == o2);    //prints: false

    System.out.println("abc" == o1); //prints: false

    System.out.println("abc" == o1.intern()); //prints: true

 
  }
}
