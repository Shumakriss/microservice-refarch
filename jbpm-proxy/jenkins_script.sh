mvn clean package
docker stop demo-service | true
docker rm demo-service | true
docker build -t demo-service .
docker run -d --name demo-service -p 8080:8080 demo-service:latest
mvn -P container verify
