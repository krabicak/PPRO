docker pull mysql
docker run -p 3306:3306 -p 33060:33060 --name mysql -e MYSQL_ROOT_PASSWORD=admin -d mysql:latest
pause