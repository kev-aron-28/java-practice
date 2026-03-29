# All ways or writing/reading files


1. Bytes streams

## Read
FileInputStream

``` java
FileInputStream fis = new FileInputStream("file.txt");
int data;
while ((data = fis.read()) != -1) {
    System.out.print((char) data);
}
fis.close();
```

## Write


``` java
FileOutputStream fos = new FileOutputStream("file.txt");
fos.write("Hello".getBytes());
fos.close();
```

``` java
InputStream fileStream = new FileInputStream("datos.bin");
BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
int byteData = bufferedStream.read();
bufferedStream.close();
```

2. Character streams (text-based)

## Read

``` java
FileReader fr = new FileReader("file.txt");
int c;
while ((c = fr.read()) != -1) {
    System.out.print((char) c);
}
fr.close();
```


## Write

``` java
FileWriter fw = new FileWriter("file.txt");
fw.write("Hello");
fw.close();
```

use with:

``` java
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
String line;
while ((line = br.readLine()) != null) {
    System.out.println(line);
}
br.close();
```


3. NIO (Modern api)

## Read entire file

``` java
String content = Files.readString(Path.of("file.txt"));
```

## Write entire file

``` java
Files.writeString(Path.of("file.txt"), "Hello");
```


## Read all lines

``` java
try (Stream<String> lines = Files.lines(Path.of("file.txt"))) {
    lines.forEach(System.out::println);
}
```

4. NIO channesl and buffers

5. Scanner (Simple but slower)

``` java
Scanner sc = new Scanner(new File("file.txt"));
while (sc.hasNextLine()) {
    System.out.println(sc.nextLine());
}
sc.close();
```

6. PrintWriter / PrintStream 

``` java
PrintWriter pw = new PrintWriter("file.txt");
pw.println("Hello");
pw.close();
```

7. Data Streams (structured binary)

``` java
DataOutputStream dos = new DataOutputStream(new FileOutputStream("file.bin"));
dos.writeInt(100);
dos.close();


DataInputStream dis = new DataInputStream(new FileInputStream("file.bin"));
int num = dis.readInt();
dis.close();
```

8. Object serialization

``` java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.dat"));
oos.writeObject(myObject);
oos.close();
```