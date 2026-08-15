# Design patterns

Design patterns are typical solutions to commonly ocurring problems in software design
They are like pre-made blue-prints that you can customize to solve recurring design problem in your code

The pattern is not a specific piece of code , but a general concept for solving a particular problem

# What does the pattern consists of ?
- Intent: Briefly describes both the problem and the solution
- Motivation: Further explains the problem and the solution the pattern makes possible
- Structure: Shows each part of the pattern and how they are related
- Code example

# Classification of patterns
Design patterns differ by their complexity, level of detail and scale of applicability to the entire system beign designed

Creational — how objects are created
Structural — how objects are composed
Behavioral — how objects communicate and distribute responsibilities

# SOLID principles

## Single responsability principle
A class should have just one reason to change

## Open / Closed principle
Classes should be open for extension but closed for mofication

## Liskov Substitution principle
When extending a class, remember that you should be able to pass objects of the subclass in place of objects of the 
parent class without breaking the client code

## Interface segregation principle
Clients should not be force to depend on methods they do not use

## Dependency inversion principle
High-level classes should not depend on low level classes