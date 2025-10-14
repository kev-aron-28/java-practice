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

