# Questions

POO
Encapsulation: Ocultar estado interno y controlar su acceso
Abstraccion: Representacion de la realidad 
Herencia: Reutilizar , extender comportamiento de otra clase
Polimorfismo: Una referencia puede representar diferentes implementaciones

2. Interface vs abstract class
An interface defines a contract with methods to be implemented on the class, on the other hand an abstract class
first can contain an state and also default behavior

3. Overloading vs overriding
Overloading: Mismo metodo, diferentes parametros, se determina enucompile time
Overriding: Una clase hija redefine un metodo heredado, se determina en runtime mediante dynamic dispatch

4. == vs equals()
Con primitives int a == int b 

== compara si las referencias apuntan al mismo objeto
equals() compara igualdad logica, segun la implementacion de la clase

5. equals() + hashcode()
Si dos objectos son iguales mediante equals(), deben tener el mismo hashCode()

pero dos objectos pueden tener al mismo hashCode() y no ser iguales

6. String es immutable
No se modifica el objecto original, se crea otro string y esto se debe al String Pool

7. String vs StringBuilder vs StringBuffer
String = immutable
StringBuilder = Mutable y no es thread-safe
StringBuilder = Mutable y sincronizado 