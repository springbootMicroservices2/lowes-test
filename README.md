# lowes-test

This is an API for Url shortener service like tiny url. 

Url shortener is service that converts long urls into short aliases to save space when sharing urls in messages, twitter, presentations etc.
When user opens short url, it will be automatically redirected to original (long) url.


Design:-

The Code is logically devided into  controller, entity, service, repository, dto, and config.
Provided swager 2.0 for test the api and as a documentation for the service
We have a git action ci enabled for build pase on push to master branch of the project

Entity layer:-

Inside the entity folder,we have Url.java class with four attributes: id, longUrl, createdDate, expiresDate. 
The LongUrl attribute is the URL we should redirect to once a user accesses a short link. 
The created date is just to see when the longUrl is saved (it is not important), and expiresDate is there if a user wants to make a short link unavailable after some time. 

Repository layer :- 
inside the repository folder, we have UrlRepository.java file, which is just an extension of JpaRepository. 
It gives us a lot of methods, like findById, save, etc. 

Service Layer:- 
 BaseService.java in the service folder. BaseService contains methods to convert from base 10 to base 62 and vice versa. 

Controller Layer:-
We have  UrlController.java file in the controller folder. 
The controller should have one POST method for creating short links and one GET method for redirecting to the original URL. 

# How to use 
+ With Docker and docker-compose: 

```sh
$ git clone https://github.com/springbootMicroservices2/lowes-test.git -b master
$ cd  lowes-urlShortner
$ docker-compose up 
```

    - Open localhost:8080/swagger-ui.html to see endpoints. 
    
    
 - Without Docker: 
```sh
$ git clone https://github.com/springbootMicroservices2/lowes-test.git -b master
```
    - Make sure you have access to local or any MySQL server.
    - Open project in your favorite editor and change application.properties file to point to your MySQL database
    - Build Spring project 
    - Open localhost:8080/swagger-ui.html to see endpoints.

