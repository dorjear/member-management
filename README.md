
# Student coding task

Take small Spring Boot microservices and modify them to add some functionality.

Along the way please note any improvements you would suggest that could be made to improve the 
reliability, maintainability, testability, architecture, or ease of understanding, 

## Task summary

Read all the steps below **before** committing and pushing to your feature 
branch – you will need to submit via a pull request back to master.

* Clone this Git repository.
* Create and checkout a feature branch to commit and push all changes to.
* Build using the provided Maven pom file in the root directory.
* Execute Spring Boot application to discover current service endpoints.
* Create unit tests for new functionality.
* Make changes in the IDE or editor of your choice.
* Format your code via [google style guide](https://github.com/google/styleguide)
* Submit your modified application via a **pull-request**.

## Task details

### Application background

There are two microservices split into a client interface module and the actual 
service implementation:

* togo-member-application – manages member and program information for frequent flyer loyalty members 
    * togo-member-client – the API interfaces and models
    * togo-member-service – the actual microservice application
* togo-offers-application – manages offers that loyalty member may be interested in
    * togo-offers-client – the API interfaces and models
    * togo-offers-service – the actual microservice application

Members are frequent flyer customers who have a loyalty card, and they collect points for
flights-taken with our airline as well as for purchases made with partner retailers.

A member can be enrolled in one or more programs that provide different kinds of benefits
or rewards. 

A member can also have a preference set for offers. Offers 
are deals or specials that a member may be interested in.

### Task requirements

Please remember that there are no right or wrong implementations – 
each is just different from another.

We use this task as an entry point to conversations around coding.

#### Task 1 – member enrollment into a program

Create a new service endpoint that enables us to enrol a member 
into a program. A member can be enrolled into more than one program.

#### Task 2 – retrieve offers specific to member preference

Currently, the retrieve member API retrieves all available offers for the
member. 

Update the application/s so that only the offers that the member
has a preference for are returned with the member.

This will require providing an offers API that retrieves offers by offer-category.

#### Task 3 (optional) – add address information to member  

We need to mail information packs to members and to do this we require 
member address information. 

Update or create a service that allows for address/es to be 
stored against a member.

Address information should contain:

* address line 1
* address line 2
* city/town/locality
* postcode/zipcode
* state/province/county
* country

## Other information

* Please feel free to refactor the code as you see fit
* If you see things that you believe are incorrect in the existing code then please fix
* We'd like the code you check in to be reflective of your software quality principles

### Running the Applications

You can run the applications individually or together using **docker-compose**.

Once running the applications can be reached at:

* togo-member-application – http://localhost:8080
* togo-offers-application – http://localhost:8081

#### Running Individually

Start each application separately with the `local` Spring Profile set.

#### Running Together

You will need to have the **docker-compose** command line tool, which is available
either by installing Docker Desktop or Rancher Desktop.

You will also need to set an **M2_PATH** environment variable to the location of your
maven `.m2` folder which contains your maven local repositoty. This is to speed things 
so that the docker images need not download all dependencies afresh.

At a command prompt from the root directory of the project repository run:

```shell
export M2_PATH="~/.m2"
docker-compose up
```

### OpenAPI / Swagger

These applications provide a Swagger UI view of the APIs when they are running:

* togo-member-application – http://localhost:8080
* togo-offers-application – http://localhost:8081

You can also call the APIs using this Swagger UI.

### H2 Database

These applications use an H2 in-memory database. You can use
the H2 console to browse what schema and data is in the database.

### Browse the database contents

With the Spring Boot application running, browse to:

* togo-member-application – http://localhost:8080/h2-member-console
* togo-offers-application – http://localhost:8081/h2-offers-console

Then connect using the following:

```
Saved Settings: Generic H2 (Embedded)
Setting Name:   Generic H2 (Embedded)
-------------------------------------
Driver Class:   org.h2.Driver
JDBC URL:       jdbc:h2:mem:test
User Name:      guest
Password:       guest
```

### Database initialisation

Each application has its own database.

The Spring Boot application initialises the database with the data found in 
the following resource files found in the same location in each application:

```
src/main/resources/schema.sql
src/main/resources/data.sql
```

### Existing service endpoints

#### Get member by id

Gets member information by the member id.

```
HTTP GET /member/{memberId} 
```

#### Create a new member

Create a new member using a HTTP POST. Example payload shown below:

```
HTTP POST /member/
{
    "firstName": "Charles", 
    "lastName":  "Dickens"
}
```

#### Get all programs

Gets all program.

```
HTTP GET /program 
```

#### Get program by id

Gets program information by the program id.

```
HTTP GET /program/{programId} 
```

#### Get all offers

Gets all offers.

```
HTTP GET /offer 
```

#### Get offer by id

Gets offer information by the offer id.

```
HTTP GET /offer/{offerId} 
```
