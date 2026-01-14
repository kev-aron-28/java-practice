# Java concurrency

Two core problems:
- Mutal exclusion: Two threads modifying the same data
- Visibility: ONe thread doest not see what another wrote

## Low level synchorization primitives


### synchronized

What it solves:
- Mutual exlusion
- visibility

It uses an intrinsic lock on an object and only one thread can hold it at a time

### volatile
This only solves visibility only and does not provide atomicity
