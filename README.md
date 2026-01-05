# Tennis Game Kata

Java implementation of a tennis scoring system.

The application computes and prints the score after each won ball, based on a sequence where:
- `A` represents a point won by Player A
- `B` represents a point won by Player B

## Scope

The implementation covers:
- Standard tennis scoring (`0 → 15 → 30 → 40`)
- Deuce and Advantage handling
- Game winner detection

The program processes a single point sequence per execution via a command-line interface.

## Requirements

- Java 21
- Maven 3.9+

## Run Tests

```bash
mvn test
```

## Run the Application
Compile the project:

```bash
mvn clean compile
```

Run the program with a sequence of points:

```bash
java -cp target/classes com.wafa.tennis.Main AAABBBABAA
```

## Example Output
For an input sequence AAABBBABAA, the program will output:

```bash
Player A : 15 / Player B : 0
Player A : 30 / Player B : 0
Player A : 40 / Player B : 0
Player A : 40 / Player B : 15
Player A : 40 / Player B : 30
Deuce
Advantage Player A
Deuce
Advantage Player A
Player A wins the game
```
## Project Architecture

- Domain: Core tennis logic and immutable models.

- Infrastructure: Console-based implementation of the output port.

- Main: Application entry point responsible for argument validation and orchestration.


### Author
**Wafa D.** - *Software Engineer*
