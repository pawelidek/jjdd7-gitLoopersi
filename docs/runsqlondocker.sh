#!/bin/sh
docker run -p 0.0.0.0:3308:3306 --name mysql-db2 -e MYSQL_ROOT_PASSWORD=rootpasswordgiven -e MYSQL_DATABASE=calendar -d mysql