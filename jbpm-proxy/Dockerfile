FROM lwieske/java-8
VOLUME /tmp
ADD target/spring-boot-docker-demo-1.0.jar spring-boot-docker-demo.jar
RUN sh -c 'touch /spring-boot-docker-demo.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-boot-docker-demo.jar"]
