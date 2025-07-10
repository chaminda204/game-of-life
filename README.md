# Game of Life - Java Implementation

## Overview

This is a demo coding challenge for BGL, focusing on showcasing design, and development capabilities.  Following are the main focus areas

- *Clean Code* 
- *Maintainability**
- *SOLID principles*
- *Extensibility*
- *Unit testing with* **Spock Framework**

---

## Features

- Simulation on a configurable grid (default 200x200)
- Supports rules for reproduction, survival, underpopulation (added for debugging), and overpopulation (added for debugging)
---

```java

// configure rules
List<GameRule> rules = List.of(
   // new UnderpopulationRule(),
   // new OverpopulationRule()
    new ReproductionRule(),
    new SurvivalRule()
);

// initiate game
Game game = new Game(200, rules);

// Initialize live cells
game.setLiveCells(List.of(new Cell(5,5), new Cell(6,5), new Cell(7,5)));

// Simulate generations
List<GenerationResponse> results = game.generate(2);

```

```swift
src/
    main/
        java/com/au/bgl/gameoflife/
            Game.java
        domain/Cell.java
            rules/ (Rule implementations and GameRule interface)
    test/
        groovy/com/au/bgl/gameoflife/
            GameE2ESpec.groovy
            GameSpec.groovy
        rules/ (Rule specs with Spock)

```



## Getting Started

### Prerequisites

- Java 21
- Gradle 8

### Build & Run

```bash
./gradlew build
./gradlew test
```
