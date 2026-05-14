Maven its a build lifecycle engine + dependency manager + project standarizer

At its core, maven answer three questions:
1. How is my projet structured?
2. What does building meean?
3. What do I depend on?

Everything revolves around one file

# The POM

```
<project>
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.kevin</groupId>
  <artifactId>my-app</artifactId>
  <version>1.0.0</version>

  <dependencies>
    <!-- external libraries -->
  </dependencies>
</project>
```
- groupid: organization
- artifactId: project name
- version

Like a primary key in a distributed system

# Packaging

```
<packaging>jar</packaging>
```

- jar
- war
- pom
- ear

This afects which plugins and lifecycle behaviour get applied

# Maven lifecycle
- validate
- compile
- test
- package
- verify
- install
- deploy

Phase breakdown
validate: Checks if project is correct
compile: compiles src/main/java
test: run tests
package: creates artifact
verify: runs additional checks
install: copies artifact to local repo
deploy: pushs artifact to remote repo

# Plugins 
- maven-compiler-plugin: compiles java
- maven-surefire-plugin: runs tests
- maven-jar-plugin: builds JAR


# Commands
A maven command usually follows this pattern:

```
mvn <phase> [options]
```

1. mvn clean
Deletes target/

When to use:
- Build is acting weird
- Swtiching branches
- Changing dependencies

2. mvn compile
Compiles the Java source code
- output target/classes

Internally runs:
- validate
- compile

3. mvn test
Compiles test, runs unit tests

4. mvn package
Builds your artifact:
- jar
- war

5. mvn install
Builds the project, copies artifact to local repo