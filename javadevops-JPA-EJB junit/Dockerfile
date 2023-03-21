FROM maven:alpine as javadevops
COPY . $HOME

RUN mvn dependency:resolve
RUN mvn package install
