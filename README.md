# Card Game project for ECM2414 Software Development Continuous Assessment (CA)

This Card Game project was created as a submission for the ECM2414 Software Development Continuous Assessment. It was developed using pair programming techniques that were taught in this module.


## Table of Contents
- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Tests](#running-the-test-suite)
  - [Running the Project](#running-the-project)
- [License](#license)

---

## About
The game simulates a card game similar to 'Pig' or 'Spoons' where players attempt to obtain a hand of identical cards by drawing and discard from a deck.  In this project, hands and decks are drawn from a pack which contains 8n cards, where n is the number of players (and a positive integer).  

---

## Features

- ### Multi-threading
  As the specification requires draw and discard to count as one atomic action, threading has been implemented. Actions may be performed in parallel, making the application faster and more efficient. 
- ### Thread-safe classes
  Multiple threads will need to access data (i.e. a deck being drawn from and discarded to) so ensuring correct function during simultaneous execution is necessary. Our code implements synchronisation to achieve this.
    - Thread-safe Card class
    - Thread-safe Player class
    - Thread-safe CardDeck (FIFO) class 
- ### Error handling

---

## Getting Started

### Prerequisites

Make sure you have the following installed:
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- A code editor or IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/)


## Running the Tests

The tests for this project are written with JUnit 4 and Gradle

### Installations
JUnit4, Java 17 and Gradle

### Getting Started

Navigate to home directory

```bash
cd CardGameProject
```
      
### Run the tests

If Gradle is installed, run the following to build and run the test files

```bash
./gradlew clean build test
```

### Test results

If all the tests pass, the following should come up in the terminal

```bash
    BUILD SUCCESSFUL
    8 actionable tasks: 8 executed
```

Any errors will be displayed
    
## Running the Project

Navigate to project directory 

```bash
cd src
```

Run project using the command

```bash
java -cp build/classes/java/main game.CardGame
```

Type player number and file name matching to player number, including full path
eg 4 and src/test/java/game/testFiles/valid4.txt

## Example run

For 4 players this would be the output

![Example run](egRun.png)

## License
MIT license - see file
