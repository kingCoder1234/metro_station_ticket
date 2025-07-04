# 🚇 Metro Ticket Booking System
(Backend part)
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
| Frontend     | React.js          |
| Database     | MySQL             |
| ORM          | Spring Data JPA   |
| Build Tool   | Maven             |
| IDE          | IntelliJ /Eclipse |


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

```For api testing
http://localhost:8080/api/stations
GET
response
[
    {
        "name": "Aero City",
        "startStation": false,
        "lastStation": false,
        "price": 48
    },
    {
        "name": "Central",
        "startStation": true,
        "lastStation": false,
        "price": 0
    }
]


http://localhost:8080/api/tickets/buy?start=Central&end=Greenwood
POST
Response
{
    "ticketId": "3c92c900-e56d-4838-9d4e-83880e74975e",
    "startStation": "Central",
    "endStation": "Greenwood",
    "createdTime": "2025-07-04T15:17:02.1891912",
    "expiryTime": "2025-07-05T09:17:02.1891912",
    "usageCount": 0,
    "entryUsed": false,
    "exitUsed": false,
    "price": 17
}



http://localhost:8080/api/tickets/use?ticketId=3c92c900-e56d-4838-9d4e-83880e74975e&type=exit
POST
Response
Cannot use exit before entry.


http://localhost:8080/api/tickets/status?ticketId=3c92c900-e56d-4838-9d4e-83880e74975e
GET
Response
Ticket from Central to Greenwood. Entry used: false, Exit used: false, Usage count: 0, Expires: 2025-07-05T09:17:02.189191, Price: ₹17

```


#  Forntend part
Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

