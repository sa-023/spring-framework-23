#FROM openjdk:11
#LABEL maintainer="company.com"
#WORKDIR /usr/app
#COPY ./target/Spring-24-Docker-0.0.1-SNAPSHOT.jar  ./app.jar
##willprovideexacutableapplicationwhencontainerstartstherun
#ENTRYPOINT ["java","-jar", "app.jar"]

#FROM  openjdk:11
#COPY ./target/Spring-24-Docker-0.0.1-SNAPSHOT.jar   /usr/app/
#WORKDIR /usr/app
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","Spring-21-Docker-0.0.1-SNAPSHOT.jar"]

# · The FROM instruction specifies the parent image(Baseimage) from which you are building.
FROM openjdk:11-jdk
# · The COPY command allows us to copy a file (jar file) or folder from the host system into the docker image.
COPY ./target/Spring-24-Docker-0.0.1-SNAPSHOT.jar  /usr/app/
# · The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /usr/app
# · The ENTRYPOINT instruction is used to configure the executables that will always run after the container is initiated.
# · Note: The name of our jar file inside the image is Spring-24-Docker-0.0.1-SNAPSHOT.jar, so the ENTRYPOINT should be exactly the same.
ENTRYPOINT ["java","-jar","Spring-24-Docker-0.0.1-SNAPSHOT.jar"]


