# spring-boot-docker-demo

## Microservices

Docker and Spring Boot make developing microservices very easy. However, 
it can be a challenge to build and test your application in many 
 different environments. 
 
## Integration Tests
 This demo will show you how to make an 
 application that can be unit tested and integration tested in multiple
 environments. More importantly, there needs to be a way to run 
 integration tests locally but still verify container endpoints.

## Maven Profiles and Plugins
In addition to Docker and Spring Boot, we are also using Maven plugins
and profiles to control test runs. We use the Maven failsafe plugin
so that the post-integration (cleanup) happens during the Maven build 
even when the tests fail. Any tests including 'IT' will run during 
the integration-test phase. The spring-boot-maven-plugin is used to
start and stop the server when running locally. We let a build script or
Jenkins do the work when doing container tests. 

## Spring Boot
Spring Boot offers numerous guides for setting up your project. Please 
refer to them for more information. Here are a few interesting features
in this demo:
* Automatic repository method generation
* Actuator endpoints (/health, /metrics, /mappings, etc.)
* RestTemplates with Jackson data binding for JSON marshalling

## Vagrant
In this example, we will be using Vagrant to simulate a remote Linux
build/Docker server. Please refer to the Vagrant documentation for 
 installation instructions. We will be using a CentOS 7 Vagrant box.
 
## Tutorial
First we will show how to run tests locally then we will show how to 
run container tests remotely.

### Local tests
1. Clone the repository to your environment and navigate to it
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/clone.png "Clone and navigate")
2. Open the project in your IDE, here we are using IntelliJ community
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/ide.png "Open an IDE")
3. Run Maven with the local profile. First the unit tests run
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/unit.png "Run unit tests")
4. Then the server starts
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/bootmavenplugin.png "Spring Boot Integration")
5. Then the integration tests run
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/integrationtest.png "IT Tests")
6. Finally the test results are printed
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/testresults.png "Test Results")

### Remote container tests

7. If you are not already running Linux, start the Vagrant box
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/vagrantup.png "Vagrant up")
8. Wait for provisioning to complete
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/provision.png "Provisioning complete")
9. Log into the box
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/ssh.png "Vagrant ssh")
10. Navigate to the working directory and start the build
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/runscript.png "Build the jar")
11. After the jar is built, the docker image is built and run
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/buildimage.png "Build the image")
12. After the container is started, Maven will run under another profile
![alt text](https://raw.githubusercontent.com/Shumakriss/spring-boot-docker-demo/master/screenshots/containertest.png "Run container tests")

## Future Work
The local tests are run against an H2 database. Spring makes it easy to
setup and cleanup a database before and after the integration tests. We 
will add a remote database in the next demo and show how to swap the 
connection settings between profiles.
