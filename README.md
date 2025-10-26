# ECM2414 Software Development Continuous Assessment (CA): Card Game
---

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

This Card Game project was created as a submission for the ECM2414 Software Development Continuous Assessment (CA). It was developed using pair programming techniques that were taught in this module. Instructions on how to play this are below, in the section 'Getting Started'.

It is a card playing simulation witten in Java that implements features such as:
 - thread-safe classes
 - an executable CardGame class
 - error handling.
   
The game simulates a card game similar to 'Pig' or 'Spoons' where players attempt to obtain a hand of identical cards by drawing and discard from a deck.  In this project, hands and decks are drawn from a pack which contains 8n cards, where n is the number of players (and a positive integer).  

---

## Features

### Executable CardGame class
This class is used to play the game.
### Multi-threading
As the specification requires draw and discard to count as one atomic action, threading has been implemented. Actions may be performed in parallel, making the application faster and more efficient. 
### Thread-safe classes
Multiple threads will need to access data (i.e. a deck being drawn from and discarded to) so ensuring correct function during simultaneous execution is necessary. Our code implements synchronisation to achieve this.
- Thread-safe Card class
- Thread-safe Player class
- Thread-safe CardDeck (FIFO) class 
### Error handling
The code also includes error handling.

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
javac -d bin src/game/*
java -cp bin game/CardGame
```
---
### Running the test suite
The project contains unit tests for the ... classes
#### Install
#### Setup
#### Run the tests
#### Test results

## License
MIT license - see file
