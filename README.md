# GourmetJava

Welcome to **Gourmet Java's UML Editor**! This is where you can find information about what the project does and how to use it.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [UML-Editor](#running-the-uml-editor)
- [Programmers](#programmers)

## Introduction

With this UML Editor you can make UML structures for a Java project you have in mind using your terminal of choice.

## Features

- Feature 1: Build and manipulate classes.
- Feature 2: Add and manipulate class attributes.
- Feature 3: Add and manipulate relationships between classes.
- Feature 4: Save and Load the UML Project.
- Feature 5: Help option to instruct user how to use the program.

## Installation

To run the UML Editor you need to have the most recent Java Virtual Machine installed(JVM). After having JVM installed you can use different techniques to run the program which are using an IDE, a terminal, or Gradle to run the program. 

This Installation guide will walk you through the process of installing a JVM and Gradle an open-source build automation tool.

### Java Installation

**For Windows**

1. Open up your browser of choice. 
2. Go to Oracle's [website](https://www.oracle.com/java/technologies/downloads/) to download the most recent version of Java for Windows.
3. Follow Oracle's installation guide for Java.
4. Go to C:\Program Files\Java\jdk-20\bin or wherever your bin file is located for Java. Copy the path of your bin file.
5. Open up Window's Start and go to Edit the System Enviroment Variables.
6. Select Environment Variables &rarr; navigate the System variables window and look for Path and double-click it &rarr; click new &rarr; and then paste the bin path into the the new line &rarr; click okay out of all windows to apply changes.
7. Open a command prompt.
8. In the console enter `java --version` to check that JVM was properly installed.

**For Linux**

1. Open a terminal.
2. Enter `sudo apt install openjdk-19-jre-headless`. Enter `ls` to see if the rpm file is present.
3. In the terminal enter `java --version` to check that JVM was properly installed.

### Gradle Installation

**For Windows**

1. Open up your browser of choice. 
2. Go to Gradle's [website](https://gradle.org/releases) &rarr; select the newest version of Gradle and download the *binary-only* option.
3. Create a Gradle folder in your C: Drive.
4. Go to your downloads folder and extract the Gradle zip into the Gradle folder you created in your C: Drive.
5. Go into the bin file in the Gradle folder and copy the bin path.
6. Open up Window's Start and go to Edit the System Enviroment Variables.
7. Select Environment Variables &rarr; navigate the System variables window and look for Path and double-click it &rarr; click new &rarr; and then paste the bin path into the the new line &rarr; click okay out of all windows to apply changes.
8. Open a command prompt.
9. In the console enter `gradle --version` to check that Gradle was properly installed.

**For Linux**

1. Open up your browser of choice. 
2. Go to Gradle's [website](https://gradle.org/releases) &rarr; select the newest version of Gradle and download the *binary-only* option.
3. Unzip the distribution zip file in the directory of your choosing.
4. Enter the following commands: 
```bash
❯ mkdir /opt/gradle
❯ unzip -d /opt/gradle gradle-8.4-bin.zip
❯ ls /opt/gradle/gradle-8.4
```
5. Configure your system environment. To install Gradle, the path to the unpacked files needs to be in your Path. Configure your PATH environment variable to include the bin directory of the unzipped distribution. Enter `export PATH=$PATH:/opt/gradle/gradle-8.4/bin`. 
6. In the console enter `gradle --version` to check that Gradle was properly installed.

## Running-The-UML-Editor

**For Windows**

1. Download zip file from Github or clone the repository for GourmetJava.
2. Open cmd.
3. Go to the folder that has the GourmetJava File.
4. Compile the Java classes. Enter `gradlew.bat compileJava`
5. Generate a jar file. Enter `gradlew.bat jar`
6. Run the Java project. Enter `java -jar build/libs/GourmetJava.jar`.

**For Linux**

1. Download zip file from Github or clone the repository for GourmetJava.
2. Open terminal.
3. Go to the folder that has the GourmetJava File.
4. Compile the Java classes. Enter `./gradlew compileJava`
5. Generate a jar file. Enter `./gradlew jar`
6. Run the Java project. Enter `java -jar build/libs/GourmetJava.jar`.

## Programmers 
- Rachael D 
- Karen C
- David P
- Ben K
- Tim N 
- Michael S