<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
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
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project implements a JPA Spring Boot application that connects to a local H2 database and make CRUD requests via REST API's to two tables: Animals and Farms


### Built With

* [Java 8](https://www.java.com/pt-BR/download/help/java8_pt-br.html)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Swagger](https://swagger.io/tools/swagger-ui/)



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* Java 8 or more recent
* Maven

### Installation

1. Clone the repo
  ```sh
   git clone https://github.com/joao3101/farm-jpa-spring-h2
   ```
2. Run Maven to install packages dependencies
   ```sh
   mvn clean install
   ```
2. Run the project using
   ```sh
   mvn spring-boot:run
   ```
3. Access Swagger-UI at http://localhost:8080/swagger-ui.html#/ 

<!-- USAGE EXAMPLES -->
## Usage

The API's can make all CRUD requests. All usage is well defined at Swagger.


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

João Victor de Carvalho Silva - [LinkedIn](https://www.linkedin.com/in/joao-victor-de-carvalho-silva/)

Project Link: [https://github.com/joao3101/farm-jpa-spring-h2](https://github.com/joao3101/node-url-shortener)
