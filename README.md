# Library Catalog

The Library Catalog project is a web application built using Spring Boot and Spring MVC that allows users to manage and search for books in a library catalog. This project was developed as a coursework for programming at KPI, FICE. 

## Features

- **Book Management:** Administrators can create, edit, and delete books from the catalog. Each book can have an author, a title, a cover image, a summary, and keywords associated with it.
- **User Authentication:** Users can register an account and log in to the application to access restricted features.
- **Book Search:** Users can search for books by title, author, or keyword, and view the search results.
- **Role-based Authorization:** Different user roles (e.g., administrators, regular users) have different levels of access and permissions within the application.

## Route Table

Route | Description
--- | ---
/ | Home page with a list of all books, a search field, and the number of books in the library
/login | Login page
/registration | Registration page
/authors | Page with a list of all authors and the ability to find a book by a particular author 
/{bookId}/delete | Page for deleting a book (Admin)
/{bookId}/edit | Page for editing a book (Admin)
/create | Page for creating a book (Admin)

## Technology Stack

The project is built using the following technologies:

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf (as the templating engine)
- PostgreSQL (as the database)
- Hibernate (as the ORM framework)
