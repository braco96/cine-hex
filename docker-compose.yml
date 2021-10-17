version: '3.8'
services:
  mysql:
    image: mysql:8.3
    container_name: mysql-cine
    environment:
      - MYSQL_ROOT_PASSWORD=collado
      - MYSQL_DATABASE=cine
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
volumes:
  mysql_data: {}
