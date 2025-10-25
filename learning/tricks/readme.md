# Tricks on java

IN java a char is a int so this lets you map lowercase 'a-z' to a array of 26 being 'a' 0 and 'z' 25
counts[a.charAt(i) - 'a']++;
counts[b.charAt(i) - 'a']--;

as 'a' unicode is 97

# Swap two numbers without a temporary variable

int a = 5, b = 10;
a = a + b; // a = 5 + 10 = 15;
b = a - b; // b = 15 - 10 = 5;
a = a - b; // a = 15 - we

# Map letters to indices
int [] count = new int [26];
count['a' - 'a'] = 1; // 'a' → 0
count['z' - 'a'] = 25; // 'z' → 25

# Tricks and one liners

- Convert uppercase to lowercase
char lower = (char)(c | ' ');

- Convert lowercase to uppercase
char upper = (char)(c & '_');

- Reverse string
String rev = new StringBuilder(s).reverse().toString();

- Check even/odd
if ((n & 1) == 0) System.out.println("Even");

- Flip sign
n = ~n + 1;

- Sum of array
int sum = Arrays.stream(arr).sum();

- Max element
int max = Arrays.stream(arr).max().getAsInt();

