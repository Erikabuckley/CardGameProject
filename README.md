# Project Name

A brief description of what the project does and who it's for.
This Card Game project was created as a submission for the ECM2414 Software Development Continuous Assessment (CA). It was developed using pair programming techniques that were taught in this module. Instructions on how to play this are below in the section 'Getting Started'.

It is a card playing simulation that implements deatures such as:
 - thread-safe classes
 - an executable CardGame class
 - error handling.


---

## Table of Contents
- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Project](#running-the-project)
- [License](#license)

---

## About

The game simulates a card game similar to 'Pig' or 'Spoons' where players attempt to obtain a hand of identical cards by drawing and discard from a deck.  In this project, hands and decks are drawn from a pack which contains 8n cards, where n is the number of players (and a positive integer).  



---

## Features

To improve performance and concurrency, threads have been implemented within classes so actions may be performed as one e.g. draw and discard count as one atomic action. Using multi-threading 

---

## Getting Started

### Prerequisites

Make sure you have the following installed:
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- A code editor or IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/)

### Installation

```bash
# Clone the repository
git clone https://github.com/your-username/your-project.git

# Navigate into the project directory
cd your-project
```
### Running the project

```bash
javac -d bin src/game/*
java -cp bin game/CardGame
```

## License
MIT license - see file
