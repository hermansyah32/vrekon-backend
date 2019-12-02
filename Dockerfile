FROM 7.0.96-jdk8-openjdk
WORKDIR $CATALINA_HOME
COPY /target/myApp.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]