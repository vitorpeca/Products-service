FROM openjdk:11-jre-slim-sid

EXPOSE 8080

COPY ./target/Products-0.1.jar products.jar

ENTRYPOINT [ "java" , "-Xms128m",  "-Xmx400m", "-jar", "-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", "products.jar" , "-Djava.net.preferIPv4Stack = true"]