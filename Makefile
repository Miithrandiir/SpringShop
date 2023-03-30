pwd=$(shell pwd)

do_java_image:=maven:3.9-amazoncorretto-19

do:=docker run --rm -it -v "$(pwd):/app" -w /app

do_java:=$(do) --net=host -w /app $(do_java_image)
do_java_port:=$(do)  -p 8080:9999 $(do_java_image)

up:
	docker-compose down -v
	docker-compose up -d
	sleep 4
	docker-compose exec database psql -U username -d default_database -f /app/db/schema.sql
	docker-compose exec database psql -U username -d default_database -f /app/db/data.sql
	$(do_java) mvn clean package -DskipTests
	java -jar target/SpringShop-0.0.1-SNAPSHOT.jar

test:
	$(do_java) mvn test

down:
	docker-compose down -v

front:
	if [ ! -d "./spring_shop_frontend" ]; then git clone git@github.com:Miithrandiir/spring_shop_frontend.git spring_shop_frontend; fi
	cd spring_shop_frontend && 	yarn install && yarn build && yarn preview --open --host

