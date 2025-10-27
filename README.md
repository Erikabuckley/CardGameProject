# Card Game project for ECM2414 Software Development Continuous Assessment (CA)

This Card Game project was created as a submission for the ECM2414 Software Development Continuous Assessment. It was developed using pair programming techniques that were taught in this module.


## Table of Contents
- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Project](#running-the-project)
  - [Running the Tests](#running-the-test-suite)
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

### Installation

```bash
# Clone the repository
git clone https://github.com/Erikabuckley/CardGameProject

# Navigate into the project directory
cd src/game
```
### Running the project

```bash
javac -d bin src/main/java/game/*
java -cp bin src/game/CardGame

```
---
## Running the test suite
The tests for this project are written with JUnit 4 and Gradle

### Installations
JUnit4, Java 8 and Gradle

### Getting Started

have stuff to do with gradle here

    unzip cardsTest.zip
    cd Gradle 
    
check if gradle is installed using

    gradle - v
     
### Run the tests

use this command if you've got Gradle installed.

    gradle test
this runs all the tests.

### Test results
If all the tests pass, this should come up in the terminal

    BUILD SUCCESSFUL
    3 actionable tasks: 3 executed, 0 up to date
    
all were executed successfully
if any fail, details will be displayed in the terminal.


## License
MIT license - see file
