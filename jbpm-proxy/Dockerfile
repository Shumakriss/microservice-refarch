FROM lwieske/java-8
VOLUME /tmp
ADD target/jbpm-proxy-1.0.jar jbpm-proxy.jar
RUN sh -c 'touch /jbpm-proxy.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/jbpm-proxy.jar"]
