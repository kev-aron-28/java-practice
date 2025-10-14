# Regex

Java’s regular expressions (regex) come from the java.util.regex package, mainly using:

Pattern → compiles the regex

Matcher → applies it to a string

| Pattern | Meaning                               | Example  | Matches             |
| ------- | ------------------------------------- | -------- | ------------------- |
| `.`     | Any single character (except newline) | `a.b`    | `acb`, `aab`, `a9b` |
| `^`     | Start of string                       | `^Hello` | `"Hello world"`     |
| `$`     | End of string                         | `world$` | `"Hello world"`     |
| `\d`    | Any digit `[0-9]`                     | `\d\d`   | `"12"`              |
| `\D`    | Any non-digit                         | `\D`     | `"a"`, `"?"`        |
| `\w`    | Word character `[A-Za-z0-9_]`         | `\w+`    | `"abc_12"`          |
| `\W`    | Non-word character                    | `\W`     | `"@"`, `"!"`        |
| `\s`    | Whitespace                            | `\s`     | `" "`, `"\t"`       |
| `\S`    | Non-whitespace                        | `\S`     | `"a"`, `"9"`        |
| `\\`    | Escape backslash                      | `\\.`    | `"."`               |


| Pattern | Meaning         | Example  | Matches                   |
| ------- | --------------- | -------- | ------------------------- |
| `*`     | 0 or more       | `a*`     | `""`, `"a"`, `"aaaa"`     |
| `+`     | 1 or more       | `a+`     | `"a"`, `"aaa"`            |
| `?`     | 0 or 1          | `a?`     | `""`, `"a"`               |
| `{n}`   | Exactly n       | `a{3}`   | `"aaa"`                   |
| `{n,}`  | At least n      | `a{2,}`  | `"aa"`, `"aaa"`, `"aaaa"` |
| `{n,m}` | Between n and m | `a{2,4}` | `"aa"`, `"aaa"`, `"aaaa"` |


| Pattern       | Meaning           | Example                  |
| ------------- | ----------------- | ------------------------ |
| `[abc]`       | a, b, or c        | `"[abc]"` matches `"a"`  |
| `[^abc]`      | not a, b, or c    | `"[^abc]"` matches `"d"` |
| `[a-z]`       | lowercase letters | `"h"`                    |
| `[A-Z]`       | uppercase letters | `"G"`                    |
| `[0-9]`       | digits            | `"5"`                    |
| `[a-zA-Z0-9]` | alphanumeric      | `"A"`, `"4"`             |


| Pattern        | Meaning               | Example                                    |       |                              |
| -------------- | --------------------- | ------------------------------------------ | ----- | ---------------------------- |
| `(abc)`        | Capturing group       | `"(abc)+"` matches `"abcabc"`              |       |                              |
| `(?:abc)`      | Non-capturing group   | `"a(?:bc)+"`                               |       |                              |
| `(?<name>abc)` | Named group (Java 7+) | `"(?<word>\\w+)"`                          |       |                              |
| `              | `                     | OR operator                                | `"cat | dog"`matches`"cat"`or`"dog"` |
| `(?=...)`      | Positive lookahead    | `"\\d(?=px)"` matches `"5"` in `"5px"`     |       |                              |
| `(?!...)`      | Negative lookahead    | `"\\d(?!px)"`                              |       |                              |
| `(?<=...)`     | Positive lookbehind   | `"(?<=#)\\w+"` matches `"abc"` in `"#abc"` |       |                              |
| `(?<!...)`     | Negative lookbehind   | `"(?<!#)\\w+"`                             |       |                              |
