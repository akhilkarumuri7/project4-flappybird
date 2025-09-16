# Flappy Bird (Java)

A simple clone of the classic **Flappy Bird** game, implemented in pure Java.  
This project was created as a class project for CMSC132, as a learning exercise in **object-oriented programming**, event handling, and graphics.

---

## Features
- **Player-controlled bird** (`BirdElement`) with jump mechanics
- **Pipes and obstacles** (`PipeElement`, `ObstacleElement`) that move across the screen
- **Collision detection** using polygons (`Point`, `Polygon` classes)
- **Scoring system** — earn points for passing pipes
- **Game loop** (`Game` class) that updates and renders elements
- Entry point: [`FlappyBird.java`](src/game/FlappyBird.java)

---

## Technologies
- Java (no external libraries required)
- Object-oriented design (separate classes for bird, pipes, game loop, geometry)

---

## How to Run

Make sure you have **Java 17+** installed.  
Clone the repo and run:

```bash
# Compile all source files into an output folder
mkdir -p out
javac -d out $(find src -name "*.java")

# Run the game
java -cp out game.FlappyBird
```
## Project Write-up

This project was originally developed as part of **CMSC132 (Spring 2023)** by Dev Patel and Akhil Karumuri.  
Our full write-up (PDF) describes the planning, design decisions, challenges, and skills we developed while working on this game.

- [Read the Write-up](FlappyBird_Project_Report.pdf)  
- [Demo Video](https://youtu.be/FixwEll1Nyc?si=5QTjfYViUDA8_quE)

Key reflections:
- We benefited from careful planning before coding, which helped structure the game efficiently.  
- Implementing random pipe heights while keeping spacing constant was tricky and took more time than expected.  
- The project helped us practice **inner/anonymous classes, lambda expressions, KeyListeners, and GUI painting**, which were new concepts for us at the time.  
- With more time, we would’ve liked to add **new game modes** (coins, moving pipes, themes) to expand the gameplay.
