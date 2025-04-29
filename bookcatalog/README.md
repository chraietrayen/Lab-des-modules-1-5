# Book Catalog API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A RESTful API for managing a book catalog, built with Spring Boot and Spring Data JPA.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete books
- **Search Functionality**: Search books by title, author, or publication year
- **Pagination & Sorting**: Get paginated results with sorting options
- **Data Validation**: Comprehensive input validation
- **Database Support**:
    - H2 in-memory database (development)
    - PostgreSQL (production)
- **API Documentation**: Auto-generated OpenAPI/Swagger documentation
- **Database Migrations**: Flyway for schema version control

## Technologies

- **Backend**: Spring Boot 3.4.5
- **Database**:
    - H2 (Development)
    - PostgreSQL (Production)
- **ORM**: Spring Data JPA
- **API Docs**: SpringDoc OpenAPI 3
- **Database Migrations**: Flyway
- **Build Tool**: Maven

## Getting Started

### Prerequisites

- Java 21
- Maven 3.6+
- PostgreSQL (for production environment)
- Optional: Docker (for containerized deployment)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/book-catalog-api.git
   cd book-catalog-api