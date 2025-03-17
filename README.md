# Spring Boot multi-module application example  
This project shows you how can you modularize a Spring Boot application with Maven and how can you use some of the modules as libraries in other Spring Boot applications. Note that the project structure of this example is intended to be used to learn and play with these concepts. When you are building a real application you should think what makes sense for your project. For small projects, do not forget the KISS principle. For complex projects, a well-organized application is a must. In any approach, you need to always take into consideration the maintainability of your project.

**Overview about Spring Boot application context:**

As you should know the Spring application context contains all the beans that makes up the application (e.g. controllers, services, repositories, etc). You can register beans to your Spring Boot application context using the annotation `@Configuration`  in any class in order to act as a beans factory.  The annotated classes can contain factory methods annotated with  `@Bean`  whose return objects are automatically added to the application context by Spring. Another way to register  beans is using the annotation `@ComponentScan(basePackages="base.package.to.search")` along with `@Configuration` to specify the packages that you want to be scanned. It will look through all classes in the specified base package (if nothing is specified it will search the current package and all its sub packages) and load an instance of each class that is annotated with one of Spring’s stereotype annotations (e.g. `@Component`, `@Service`, etc) into the application context.

**Multi-module project structure:**

web
common

**System requirements**  
- JDK 17      
- Maven      
- Docker  
      
## How to build and run   
*With docker*: 

Execute the provided script [run-docker.sh](run-docker.sh) or run directly the following commands to build and run the docker image:  `docker build -t app-example-image . && docker run --name app-example-container -p 8080:8080 app-example-image`   

 *Without docker*:
 
 Build and generate the artifact file with the maven command `mvn clean package` and then run the maven spring boot plugin     
 `mvn spring-boot:run` (or the java command `java -jar <jar file location>`).  
   
The application will be accessible at [http:localhost:8080](http:localhost:8080) (note that the configured application context path is /api).

