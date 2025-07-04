# 🚇 Metro Ticket Booking System

A Spring Boot-based web application that allows users to book metro tickets, manage routes, and view booking history. The project follows a layered architecture and uses MySQL as the backend database.

## 📌 Features

- 📍 List Metro Stations and Routes
- 🎟️ Book Metro Tickets
- 🧾 View Booking History
- 🛠️ Admin Panel (Optional for route/station management)

## 🛠 Tech Stack

| Layer        | Technology        |
|--------------|-------------------|
| Backend      | Java, Spring Boot |
| Database     | MySQL             |
| ORM          | Spring Data JPA   |
| Build Tool   | Maven             |
| IDE          | IntelliJ / Eclipse |


## ⚙️ Configuration

Make sure your `application.properties` is set up properly in `src/main/resources/`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/metrodb
spring.datasource.username=yourusername --> Add here your data base username
spring.datasource.password=yourpassword  --> Add here your data base password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080

```
