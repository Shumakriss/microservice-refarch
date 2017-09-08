# JBPM KIE Server & Workbench Integration

This project integrates the jBPM Workbench and KIE Execution Servers using Docker-compose.

## Notes
* Currently, the Workbench and KIE server are integrated via database. This is great for dashboards but problematic for developing workflows and hosting production apps at the same time. Specifically, the workbench will generate different container ID's automatically on deployment while KIE server container ID's are arbitrarily specified by the user/client. A new process-developer workbench may be needed in the future.