# Blog Backend API

RESTful API backend for blog management built with Spring Boot and MySQL.

## Tech Stack

**Java 21** • **Spring Boot 3.4.5** • **Spring Data JPA** • **MySQL 8.0** • **Maven**

## Database Tables

**Post:** id, title, content, author, created_at, updated_at  
**User:** id, username (unique), email (unique), password, created_at, updated_at

## API Endpoints

**Base URL:** `http://localhost:8085`

### Posts (`/api/posts`)

- `GET /hello` - Test endpoint
- `GET /` - Get all posts
- `GET /id/{id}` - Get post by ID
- `GET /title/{title}` - Get by title
- `GET /author/{author}` - Get by author
- `GET /date/{date}` - Get by date
- `GET /content/{content}` - Get by content
- `POST /` - Create post
- `PUT /{id}` - Update post
- `DELETE /{id}` - Delete post

### Users (`/api/users`)

- `GET /` - Get all users
- `GET /id/{id}` - Get user by ID
- `GET /username/{username}` - Get by username
- `GET /email/{email}` - Get by email
- `POST /` - Create user
- `PUT /{id}` - Update user
- `DELETE /{id}` - Delete user

## Features

✅ Full CRUD for Posts & Users  
✅ RESTful API design  
✅ Input validation  
✅ Custom exception handling  
✅ MySQL database with JPA/Hibernate  
✅ Postman collection included

## Configuration

**Server:** `localhost:8085`  
**Database:** `blogdb` on MySQL port 3306
