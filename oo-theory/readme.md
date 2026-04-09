#  OOP  

# Object Model en Java
El Object Model describe cómo Java representa el mundo usando objetos.

Un objeto tiene 3 cosas fundamentales:

Estado → datos (fields)
Comportamiento → métodos
Identidad → referencia única en memoria

El stack guarda la referencia, el objeto vive en el heap.

# Encapsulation REAL (Invariants)

La encapsulación no es poner private y getters/setters.
Eso es un mal entendimiento común.

La encapsulación real significa: El objeto protege sus invariantes.

Un invariant es una condición que siempre debe ser verdadera.

# Dynamic Dispatch
Dynamic dispatch es la base del polymorphism en Java.

```
Animal a = new Dog();
a.speak();
```

Java hace esto internamente:
- Ve el tipo real del objeto
- Busca el método en esa clase
- Lo ejecuta

# Object Identity vs Object Equality

Object Identity
Dos referencias apuntan al mismo objeto.

Se compara con ==

Object Equality
Dos objetos representan el mismo valor lógico.

Se compara con equals()

Si sobrescribes equals(), debes sobrescribir hashCode().

Esto es crítico para:
- HashMap
- HashSet
- caching
- Hibernate

# Class vs Instance Members

Instance Members: Pertenecen al objeto.
Class Members (static): Pertenecen a la clase, no al objeto.

# final
Significa que no puede cambiar

## final variable
no puede cambiar 
``` java
final int x = 10;
```

## final reference
No puedes cambiar la referencia, pero si el contenido
``` java
final List list = new ArrayList();
```

## final method
No puede ser overridden.
``` java
class A {
    final void method() {}
}
```

## final class
No puede ser extendida.

```
public final class String
```