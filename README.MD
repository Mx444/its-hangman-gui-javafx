# Hangman Java Game - ITS Steve Jobs Academy Exercise

This repository contains a simple implementation of the classic Hangman game in Java, developed as an exercise during the course at the ITS Steve Jobs Academy. The project includes both a command-line interface (CLI) and a graphical user interface (GUI) using JavaFX.

## Repository Structure

The project follows a standard Maven structure:

-   **src/main/java/**: Contains the main Java source code.
  -   **com/example/**: Contains the main JavaFX application entry point 
  -   **game/**: Contains the core game logic and the JavaFX GUI implementation.
  -   **main/**: Contains the main entry point for the CLI version.
-   **src/main/resources/**: Contains resources used by the application, such as the words list.
-   **pom.xml**: Maven project configuration file.

## Features

-   Classic Hangman gameplay.
-   Supports both Command-Line Interface (CLI) and Graphical User Interface (GUI).
-   Words are loaded from a text file (<mcfile name="words.txt" path="c:\Users\david\Desktop\Repos\its-steve-jobs\hangman-java\src\src\main\java\resources\words.txt"></mcfile>).
-   Visual representation of the hangman (in both CLI and GUI).

## How to Build and Run

This project uses Maven.

1.  **Build the project**:
    ```bash
    mvn clean install
    ```

2.  **Run the CLI version**:
    ```bash
    mvn exec:java -Dexec.

3.  **Run the GUI version**:
    ```bash
    mvn javafx:run
    ```

## Educational Objectives

This project demonstrates:
-   Basic Java programming concepts.
-   Object-Oriented Programming (OOP) principles.
-   Implementing game logic.
-   Handling file input (loading words).
-   Creating a Command-Line Interface (CLI).
-   Creating a Graphical User Interface (GUI) using JavaFX.

## Requirements

-   Java Development Kit (JDK) 8 or higher.
-   Maven build tool.
-   A text editor or IDE (like VS Code, Eclipse, or IntelliJ IDEA).

---

*This repository contains a simple Hangman game implementation developed as part of the educational journey at ITS Steve Jobs Academy.*