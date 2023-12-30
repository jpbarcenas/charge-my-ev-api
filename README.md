<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/jpbarcenas/charge-my-ev-api">
    <img src="src/main/resources/static/charge-my-ev-api.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Charge My EV</h3>

  <p align="center">
    REST API for managing data about EV charging stations.
    <br />
    <a href="https://github.com/jpbarcenas/charge-my-ev-api"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/jpbarcenas/charge-my-ev-api">View Demo</a>
    ·
    <a href="https://github.com/jpbarcenas/charge-my-ev-api/issues">Report Bug</a>
    ·
    <a href="https://github.com/jpbarcenas/charge-my-ev-api/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#project-requirements">Project Requirements</a></li>
        <ul>
            <li><a href="#backend">Backend</a></li>
            <li><a href="#bonus">Bonus</a></li>
        </ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

<p>This sample project is about the design and implement a REST API for managing data about EV charging stations. It is essential to understand the operation of the charging station and the data required for its management. </p>
<p>
The API allow for the creation, retrieval, update, and deletion of charging station data, as well as provide real-time information about the status and availability of charging stations to EV drivers and charging network operators. By providing this data, the API can help EV drivers locate available charging stations, plan charging routes, and manage their charging sessions efficiently, while also helping charging station operators manage their charging station network and optimize their operations.</p>


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Project Requirements
#### Backend:
- [x] Design and implement a REST API.
- [x] Use Java 8+ and the Spring Boot framework
- [x] Persist data to a MySQL database using Hibernate/JPA
- [ ] Implement caching where applicable (e.g. for availability status check)
- [ ] Achieve at least 85% test coverage using JUnit and Mockito
- [x] Follow best practices for REST API design and scalability
- [x] Implement TDD/BDD during development
- [x] Use atomic commits, follow Git best practices and publish your code to a public GitHub repository
- [x] Provide instructions for running/testing the API locally
- [x] Build a Docker image for the API and provide the image tag

#### Bonus:
- [ ] Deploy the API to a cloud platform and provide a live URL 
- [ ] Implement monitoring/alerting for the API 
- [ ] Implement authentication/authorization
- [ ] Implement an architecture based in microservices using Spring.
- [ ] Create a frontend application to consume the backend API, ideally using Angular.
    - [ ] Build components to:
        - [ ] List all charging stations 
        - [ ] View details for a single charging station
        - [ ] Check availability status of a charging station
    - [ ] Follow Angular best practices for:
        - [ ] Project structure 
        - [ ] Component reusability
        - [ ] Dependency management 
        - [ ] Routing
        - [ ] Testing
    - [ ] Containerize the Angular application in a separate Docker image


### Built With

* [![Java][Java]][Java-url]
* [![SpringBoot][SpringBoot]][Angular-url]
* [![MySQL][MySQL]][MySQL-url]
* [![JWT][JWT]][JWT-url]
* [![Angular][Angular.io]][Angular-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

In order to use and test the code locally, you should download and install the following tools.
* [Java 17+](https://www.java.com/en/download/)
* [IntelliJ IDEA Community](https://www.jetbrains.com/idea/) or [VS Code](https://code.visualstudio.com/)
* [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) and [MySQL Workbench](https://dev.mysql.com/downloads/mysql/)
* [Postman](https://www.postman.com/downloads/)

### Installation

#### MySQL Database
1. Open MySQL Workbench and connect to your local MySQL Server
2. Create a new schema called `charge_my_ev`
3. Modify the file `application.yml` located in `src/main/resources` and update the following properties with your local MySQL Server information:
   ```yaml
   datasource:
     url: jdbc:mysql://localhost:3306/charge_my_ev
     username: your_username # default: root
     password: your_password # default: root
     driver-class-name: com.mysql.cj.jdbc.Driver
   ```

#### Backend

##### Running the project locally using your IDE

1. Clone the repo
   ```sh
   git clone https://github.com/jpbarcenas/charge-my-ev-api.git
   ```
2. Load the project in your IDE
3. Build the project
4. Run the project
5. Open Postman and import the collection of requests from the file `ChargeMyEV.postman_collection.json`
6. Test the API


##### Running the project locally with Docker using docker-compose
1. Clone the repo
   ```sh
   git clone https://github.com/jpbarcenas/charge-my-ev-api.git
    ```
2. Load the project in your IDE
3. Activate the Docker profile in by modifying the file `application.yml` located in `src/main/resources` and update the following properties:
   ```yaml
     profiles:
       active: docker
   ```
4. Build the project with Maven
   ```sh
   mvn clean install
   ```
5. Run the following command in the root folder of the project
   ```sh
   docker compose up --build
   ```
6. Open Postman and import the collection of requests from the file `ChargeMyEV.postman_collection.json`
7. Test the API


##### Running the project locally with Docker using an existing image from Docker Hub - [jpbarcenas89/chargemyevjpb](https://hub.docker.com/r/jpbarcenas89/chargemyevjpb)
1. Get the mysql image from Docker Hub
   ```sh
   docker pull mysql:latest
   ```
2. Get the image from Docker Hub
   ```sh
   docker pull jpbarcenas89/chargemyevjpb:latest
   ```  
3. Run the following command in the root folder of the project
   ```sh
    docker compose up --build
    ```
4. Open Postman and import the collection of requests from the file `ChargeMyEV.postman_collection.json`
5. Test the API

   
<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
# Usage

The API contains the following endpoints:

## Manage EV Charging Station

### Create Stations
```http
POST /api/charging/stations
```

#### Request Body
```json
{
  "location": {
    "address": "123 Pocito Street",
    "latitude": 30.7128,
    "longitude": -33.006
  },
  "numberOfChargingPoints": "1",
  "status": "AVAILABLE"
}
```

#### Response Body
```json
{
  "id": 2,
  "location": {
    "address": "123 Pocito Street",
    "latitude": 30.7128,
    "longitude": -33.006
  },
  "numberOfChargingPoints": 1,
  "chargingPoints": null,
  "status": "AVAILABLE"
}
```

#### Response Status for _"address = existing address"_
```http
406 Not Acceptable
{
    "timestamp": "2023-12-30T15:32:42.101+00:00",
    "message": "Charging Station already exist with address : '123 Pocito Street'",
    "details": "uri=/api/charging/stations"
}
```

#### Response Status for _"address = null or empty"_
```http
400 Bad Request
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Address must not be null",
    "details": "uri=/api/charging/stations"
}
```

#### Response Status for _"numberOfChargingPoints <= 0"_
```http
400 Bad Request
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Number of Charging Points must be greater than 0",
    "details": "uri=/api/charging/stations"
}
```

#### Response Status for _"location = null or empty"_
```http
400 Bad Request
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Location must not be null",
    "details": "uri=/api/charging/stations"
}
```

#### Response Status for _"status = null or empty"_
```http
400 Bad Request
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Status must not be null",
    "details": "uri=/api/charging/stations"
}
```

#### Response Status for _"latitud == null or empty"_
```http
400 Bad Request
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Latitude must not be null",
    "details": "uri=/api/charging/stations"
}
```

#### Response Status for _"longitud == null or empty"_
```http
400 Bad Request
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Longitude must not be null",
    "details": "uri=/api/charging/stations"
}
```

### Get List of Stations
```http
GET /api/charging/stations
```

#### Response Body
```json
{
  "content": [
    {
      "stationId": 1,
      "location": {
        "address": "122 Pocito Street",
        "latitude": 60.7128,
        "longitude": -36.006
      },
      "numberOfChargingPoints": 3,
      "chargingPoints": [
        {
          "pointId": 1,
          "stationId": 1,
          "chargerType": {
            "type": "AC",
            "speed": 12,
            "price": 15.0
          },
          "powerLevel": 100.0
        }
      ],
      "status": "AVAILABLE"
    },
    {
      "stationId": 2,
      "location": {
        "address": "123 Pocito Street",
        "latitude": 30.7128,
        "longitude": -33.006
      },
      "numberOfChargingPoints": 0,
      "chargingPoints": [],
      "status": "AVAILABLE"
    }
  ],
  "pageNo": 0,
  "pageSize": 10,
  "totalElements": 2,
  "totalPages": 1,
  "last": true
}
```

### Get a Station By Id
```http
GET /api/charging/stations/{id}
```

#### Response Body
```json
{
  "id": 1,
  "location": {
    "address": "122 Pocito Street",
    "latitude": 60.7128,
    "longitude": -36.006
  },
  "numberOfChargingPoints": 3,
  "chargingPoints": [
    {
      "id": 1,
      "chargingStation": null,
      "chargerType": {
        "type": "AC",
        "speed": 12,
        "price": 15.0
      },
      "powerLevel": 100.0
    }
  ],
  "status": "AVAILABLE"
}
```

#### Response Status for _"invalid stationId"_
```http
404 Not Found
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Charging Station not found with id : '4'",
    "details": "uri=/api/charging/stations/4"
}
```

### Update Stations
```http
PUT /api/charging/stations/{id}
```

#### Request Body
```json
{
  "location": {
    "address": "122 Pocito Street",
    "latitude": 60.7128,
    "longitude": -36.006
  },
  "numberOfChargingPoints": 3,
  "chargingPoints": [],
  "status": "AVAILABLE"
}
```

#### Response Body
```json
{
  "id": 1,
  "location": {
    "address": "122 Pocito Street",
    "latitude": 60.7128,
    "longitude": -36.006
  },
  "numberOfChargingPoints": 3,
  "chargingPoints": [],
  "status": "AVAILABLE"
}
```

### Delete Stations
```http
DELETE /api/charging/stations/{id}
```

#### Response Body
```string
Charging Station has been deleted!
```

#### Response Status - Not Found
```http
404 Not Found
{
    "timestamp": "2023-12-30T15:24:46.877+00:00",
    "message": "Charging Station not found with id : '4'",
    "details": "uri=/api/charging/stations/4"
}
```

## Manage Stations Charging Points

### Create Points
```http
POST /api/charging/stations/{stationId}/points
```

#### Request Body
```json
{
  "chargerType": {
    "type": "DC",
    "speed": 20,
    "price": 25.0
  },
  "powerLevel": 100.0
}
```

#### Response Body
```json
{
  "id": 4,
  "chargingStation": {
    "id": 1,
    "location": null,
    "numberOfChargingPoints": 5,
    "chargingPoints": null,
    "status": "AVAILABLE"
  },
  "chargerType": {
    "type": "DC",
    "speed": 20,
    "price": 25.0
  },
  "powerLevel": 100.0
}
```

#### Response Status for _"request without stationId - /api/charging/stations/points"_
```http
405 Method Not Allowed
{
    "type": "about:blank",
    "title": "Method Not Allowed",
    "status": 405,
    "detail": "Method 'POST' is not supported.",
    "instance": "/api/charging/stations/points"
}
```

#### Response Status for _"invalid stationId"_
```http
404 Not Found
{
    "timestamp": "2023-12-30T15:37:18.927+00:00",
    "message": "Charging Station not found with id : '5'",
    "details": "uri=/api/charging/stations/5/points"
}
```

### Get List of Points for a Station
```http
GET /api/charging/stations/{stationId}/points
```

#### Response Body
```json
{
  "content": [
    {
      "pointId": 1,
      "stationId": 1,
      "chargerType": {
        "type": "AC",
        "speed": 12,
        "price": 15.0
      },
      "powerLevel": 100.0
    },
    {
      "pointId": 2,
      "stationId": 1,
      "chargerType": {
        "type": "AC",
        "speed": 12,
        "price": 15.0
      },
      "powerLevel": 90.0
    }
  ],
  "pageNo": 0,
  "pageSize": 10,
  "totalElements": 4,
  "totalPages": 1,
  "last": true
}
```

### Get a Point Details for a Station
```http
GET /api/charging/stations/{stationId}/points/{pointId}
```

#### Response Body
```json
{
  "id": 3,
  "chargingStation": {
    "id": 1,
    "location": null,
    "numberOfChargingPoints": 5,
    "chargingPoints": null,
    "status": "AVAILABLE"
  },
  "chargerType": {
    "type": "DC",
    "speed": 20,
    "price": 25.0
  },
  "powerLevel": 100.0
}
```

#### Response Status for _"invalid stationId"_
```http
404 Not Found
{
    "timestamp": "2023-12-30T15:37:18.927+00:00",
    "message": "Charging Station not found with id : '5'",
    "details": "uri=/api/charging/stations/5/points/1"
}
```

#### Response Status for _"invalid pointId"_
```http
404 Not Found
{
    "timestamp": "2023-12-30T15:37:18.927+00:00",
    "message": "Charging Point not found with id : '5'",
    "details": "uri=/api/charging/stations/1/points/5"
}
```

### Update Points
```http
PUT /api/charging/stations/{stationId}/points/{pointId}
```

#### Request Body
```json
{
  "chargerType": {
    "type": "AC",
    "speed": 10,
    "price": 15.0
  },
  "powerLevel": 80.0
}
```

#### Response Body
```json
{
  "id": 3,
  "chargingStation": {
    "id": 1,
    "location": null,
    "numberOfChargingPoints": 5,
    "chargingPoints": null,
    "status": "AVAILABLE"
  },
  "chargerType": {
    "type": "AC",
    "speed": 10,
    "price": 15.0
  },
  "powerLevel": 80.0
}
```

### Delete Points
```http
DELETE /api/charging/stations/{stationId}/points/{pointId}
```

#### Response Body
```string
Charging point deleted successfully!
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- [ ] Manage EV Charging Station
    - [x] Create Stations
    - [x] Get List of Stations
    - [x] Get a Station By Id
    - [x] Update Stations
    - [x] Delete Stations
    - [ ] Get Station Status (AVAILABLE, IN USE)
- [ ] Manage Stations Charging Points
    - [x] Create Points
    - [x] Get List of Points for a Station
    - [x] Get a Point Details for a Station
    - [x] Update Points
    - [x] Delete Points
    - [ ] Get Points Status (AVAILABLE, IN USE)

See the [open issues](https://github.com/jpbarcenas/charge-my-ev-api/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Jorge Pérez Bárcenas - [@jpbarcenas](https://twitter.com/jpbarcenas) - jpbarcenas.contractor@gmail.com

Project Link: [https://github.com/jpbarcenas/charge-my-ev-api](https://github.com/jpbarcenas/charge-my-ev-api)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members

[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues

[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/jpbarcenas89

[product-screenshot]: src/main/resources/static/screenshot.png

[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/

[SpringBoot]: https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[Sprint-url]: https://spring.io/

[MySQL]: https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]: https://www.mysql.com/

[JWT]: https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens
[JWT-url]: https://jwt.io/

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
