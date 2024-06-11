# CMPE160 - Introduction to Object Oriented Programming

**Instructor:** Tuna Tuğcu
**Term:** Spring 21

This repository contains my work for the projects assigned as part of the CMPE160 - Introduction to Object Oriented Programming course. Each project has been implemented using Java, and Java 17 or higher should be sufficient to run the projects.

## Projects Overview

### Project 1: Simple Java Project

**Description:**
A simple project with a few classes designed to get you started with Java. You will find some classes under `Project1/template/` that you need to fill in based on the project description.

**Project Outline:**
- **Classes to Fill:** Bill.java, Customer.java, Main.java, Operator.java
- **Project Description:** [description.pdf](Project1/description.pdf)

### Project 2: Object-Oriented Programming Fundamentals

**Description:**
A project designed to teach the fundamentals of OOP, including packages, inheritance, interfaces, and regular expressions for inputs. It involves implementing a simulation of a port management system.

**Project Outline:**
- **Main Concept:** Port management system simulation
- **Project Description:** [description.pdf](Project2/description.pdf)

### Project 3: Marketplace Simulation

**Description:**
A project that simulates a marketplace to teach OOP concepts in a functional program. This includes different classes with the same functionality but different implementations within the same data structure.

**Project Outline:**
- **Main Concept:** Basic marketplace model with traders, wallets, transactions, currencies, and market
- **Project Description:** [description.pdf](Project3/description.pdf)
- **Diagram:** [openMarketTransaction.png](Project3/openMarketTransaction.png)

## Running the Projects

1. **Navigate to the project directory**:
   ```sh
   cd ProjectX
   ```

2. **Compile the project**:
   ```sh
   javac src/*.java -d bin
   ```

3. **Run the project**:
   ```sh
   java -cp bin Main
   ```

   #### Note: Check the project description for any additional files that need to be compiled, and the structure of the project.

## Repository Structure

```
.
├── Project1
│   ├── description.pdf
│   ├── src
│   ├── template
│   └── testcases
├── Project2
│   ├── description.pdf
│   ├── src
│   ├── testing
│   │   └── cases
├── Project3
│   ├── description.pdf
│   ├── openMarketTransaction.png
│   ├── src
│   ├── template
│   └── testing
│       ├── custom
│       ├── inputs
│       └── outputs
```

## Notes

- Each project folder contains a `description.pdf` file with detailed instructions and usage information.
- Ensure you have Java 17 or higher installed to compile and run the projects.
