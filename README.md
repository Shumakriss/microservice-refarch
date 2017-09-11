# JBPM KIE Server & Workbench Integration

This project integrates the jBPM Workbench and KIE Execution Servers using Docker-compose.

1. Clone the Git repo
2. Navigate to jbpm-proxy
3. mvn package
4. Navigate to top directory
5. docker-compose up -d
6. docker-compose restart kong-migration
7. docker-compose restart kong
8. Wait until kieserver is running
9. docker-compose restart jbpmproxy

## Notes
* Currently, the Workbench and KIE server are integrated via database. This is great for dashboards but problematic for developing workflows and hosting production apps at the same time. Specifically, the workbench will generate different container ID's automatically on deployment while KIE server container ID's are arbitrarily specified by the user/client. A new process-developer workbench may be needed in the future.
* Kong is currently not being used in this demo. However, if you want to use it you will need to run additional docker-compose commands "docker-compose restart kong-migration && docker-compose restart kong" should do the trick.
* The jbpm-proxy project tries to connect to the kieserver before the kieserver starts up and as a results, fails to run. Because jbpm-proxy is being proxied through the cors-proxy, it should also be restarted with "docker-compose restart jbpmproxy"
* Do not run "docker-compose up -d" repeatedly as this may result in broken container links.
