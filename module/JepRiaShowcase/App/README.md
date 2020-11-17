# JepRiaShowcase

  Example project based on [Jepria](https://github.com/Jepria/jepria) and [jfront-components](https://github.com/Jepria/jfront-components).

## Requirements
 - Java 8
 - Apache Maven
 - [Node.js](https://nodejs.org/en/download/package-manager/)
 - Download https://github.com/Jepria/bin-repo and set environment variable BIN_HOME(e.g. D:\Work\Jepria\bin-repo).
 
 ## Available Scripts
 
 In the parent project directory, you can run:
 
 ### `mvn package`
 
  Build the app.
 
 ### `mvn tomcat7:deploy -pl webapp`
 
 Rebuild only the child project 'webapp' and deploy the app.
 
 ### `mvn tomcat7:deploy-only -pl webapp`
 
 Deploy the app.
 
