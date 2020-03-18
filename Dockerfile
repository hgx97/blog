FROM java:8
MAINTAINER xx 389680571@qq.com
VOLUME /tmp
ADD target/blog.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]