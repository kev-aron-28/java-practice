
# What is a unit test?
A unit test verifies the behavior of a small, isolated unit of code

usually the unit is:
- a method
- a class
- a small group of thighly related objects

The keyword is isolated

# what is unit test actually testing?
Not implementaton, it should primarily test observable behavior

# The fundamental test structure: AAA
A very common structure is: Arrage, Act, Assert

Arrange: Prepare the state
Act: Execute the behavior beign tested
Assert: Verify the result

OR somethimes you could see: Given, When, Then

This style is sepcially common in BDD-oriented testing


# What makes a good unit test?
A good unit test should generally be:
- Fast
- Independent
- Deterministic
- Focused
- Readable
- Repeatable
- Maintainable

These properties are more important than the number of tests
Test behavior, not implementation

# Test observable behavior
Suppose you refactor repository.save(user) to repository.persist(user),
A good behavior-focused test should not necessarily break if the externally observable

So tests should allow refactoring 

# Test doubles
Mock is often used as a generic term, but there are severayl types of test doubles

- Dummy,
- Stub
- Mock
- Spy
- Fake

# Mock vs stub
Stub: You care about the value returned
Mock: You care about the interaction

# Dont mock everything
This is a common testing anti-pattern
A useful rule: Mock boundaries and external dependencies, not every object in sight

# Test fixtures
A fixture is the data / environment required by a test
Shared fixtures can reduce duplication, but excessive shared state can make tests harder to understand

Prefer fixtures that are:
- local when simple
- reusable when genuinely repeated
- explicit about important differences