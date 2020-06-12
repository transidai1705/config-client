# The Dockerfile build assumes that the application already built using: mvn package -DskipTests=true
# Build image: docker build -t config-client .
# Run container: docker run -it -p 8080:8080 --name config-client config-client

FROM openjdk:8
COPY . /code/
RUN echo "Remove old build artifact & rebuild the application" && \
	cd /code/ && \
	mv target/config-client-*.jar /config-client.jar

FROM openjdk:8-jre-alpine
ENV JAVA_OPTS=""
EXPOSE 8080
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /config-client.jar
COPY --from=0 /config-client.jar .