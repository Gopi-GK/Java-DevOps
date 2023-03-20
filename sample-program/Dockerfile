FROM mysql:latest
COPY ./script.sql /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=mydb
EXPOSE 3306
CMD ["mysqld"]