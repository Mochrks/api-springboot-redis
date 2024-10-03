<h1 align="center">Spring Boot CRUD API - Redis & PostgreSQL Integration</h1>

<p align="center">
  <img src="https://img.shields.io/badge/SpringBoot-2.5.4-brightgreen" alt="Spring Boot Version" />
  <img src="https://img.shields.io/badge/Redis-Integrated-red" alt="Redis Integration" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue" alt="PostgreSQL Integration" />
</p>

<p align="center">
  <img src="https://skillicons.dev/icons?i=spring,postgres,redis" alt="Tech Stack Icons" />
</p>

## Overview

This is a **CRUD API** built with **Spring Boot** that integrates **Redis** for caching and **PostgreSQL** as the primary database. The API allows creating, reading, updating, and deleting records with caching enabled to enhance performance. Redis is used to cache frequently accessed data, reducing load on the PostgreSQL database.

## Features

- CRUD Operations with **Redis caching** for enhanced performance
- Integration with **PostgreSQL** for persistent storage
- **Spring Data JPA** for database operations
- **Spring Cache** abstraction for Redis integration
- RESTful API design with **clean architecture**
- Input validation and error handling
- **Swagger UI** for API documentation and testing

## Tech Stack

- **Spring Boot 2.5.4**
- **Redis** for caching
- **PostgreSQL** as the primary database
- **Spring Data JPA** for database interaction
- **Lombok** for reducing boilerplate code
- **Swagger** for API documentation

## Project Structure

```bash
api-springboot-redis/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/redis/  # Source code and main application
│   ├── resources/
│   │   ├── application.properties  # Configuration for PostgreSQL, Redis
│   └── test/
│       └── java/                   # Unit tests for services and controllers
└── pom.xml                         # Project dependencies

```
## Setup & Installation
--------------------

-   **Clone the repository**

    ```bash
    git clone https://github.com/mochrks/api-springboot-redis.git
    cd api-springboot-redis
    ```

-   **Install dependencies**

    ```bash
    mvn clean install
    ```

-   **Set up PostgreSQL and Redis**

    -   Ensure **PostgreSQL** and **Redis** are running locally or provide your connection strings in `application.properties`.
    -   For **PostgreSQL**, update the following properties in `src/main/resources/application.properties`:
    
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
        spring.datasource.username=yourusername
        spring.datasource.password=yourpassword
        ```

    -   For **Redis**, update the following properties:
    
        ```properties
        spring.redis.host=localhost
        spring.redis.port=6379
        ```

-   **Run the application**

    ```bash
    mvn spring-boot:run
    ```



## Connect with me:
[![GitHub](https://img.shields.io/badge/GitHub-333?style=for-the-badge&logo=github&logoColor=white)](https://github.com/mochrks)
[![YouTube](https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtube.com/@Gdvisuel)
[![Instagram](https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white)](https://instagram.com/mochrks)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/mochrks)
[![Behance](https://img.shields.io/badge/Behance-1769FF?style=for-the-badge&logo=behance&logoColor=white)](https://behance.net/mochrks)
[![Dribbble](https://img.shields.io/badge/Dribbble-EA4C89?style=for-the-badge&logo=dribbble&logoColor=white)](https://dribbble.com/mochrks)

