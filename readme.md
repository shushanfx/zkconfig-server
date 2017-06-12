# ZKConfig Server
ZKConfig is a zookeeper config system, it includes two module: zkconfig-server and zkconfig-client.

## How run
It is based on spring boot, you can run it in jar module, such as :
```bash
java -jar zkconfig-server.${version}.jar 
```
More info please refer to [spring-boot](https://projects.spring.io/spring-boot/)

## Get it
```bash
git clone https://github.com/shushanfx/zkconfig-server.git
```
Then you can run the server with command `mvn clean spring-boot:run`, view with url [http://localhost:8080](http://localhost:8080)

## LICENCE
MIT