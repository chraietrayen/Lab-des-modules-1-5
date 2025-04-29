# Hello World Spring Boot Application

A minimal Spring Boot web application that prints "Hello, World!" and demonstrates basic concepts like controllers, REST endpoints, model classes, and application packaging.

## 🎯 Objective

Understand the core annotations and bootstrap a basic Spring Boot application.

---

## 🧰 Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6+ or Gradle 7.0+
- A code editor or IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

---

## 🚀 Project Setup

### Option A: Using Spring Initializr (Web Interface)

1. Visit [Spring Initializr](https://start.spring.io/)
2. Configure:
    - **Project**: Maven
    - **Language**: Java
    - **Spring Boot**: 3.2.x
    - **Group**: `com.example`
    - **Artifact**: `hello-world`
    - **Name**: `hello-world`
    - **Description**: A simple Hello World Spring Boot application
    - **Package Name**: `com.example.helloworld`
    - **Packaging**: Jar
    - **Java**: 17
    - **Dependencies**: Spring Web
3. Click **Generate**, download and extract the project ZIP.

### Option B: Using cURL (Command Line)

```bash
curl https://start.spring.io/starter.zip \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.2.0 \
  -d baseDir=hello-world \
  -d groupId=com.example \
  -d artifactId=hello-world \
  -d name=hello-world \
  -d description="A simple Hello World Spring Boot application" \
  -d packageName=com.example.helloworld \
  -d packaging=jar \
  -d javaVersion=17 \
  -d dependencies=web \
  -o hello-world.zip

unzip hello-world.zip -d ./
