# ZKConfig Server
ZKConfig is a zookeeper config system, it includes two module: zkconfig-server and zkconfig-client.
You can also see the [zkconfig-client-node](https://github.com/shushanfx/zkconfig-client-node)

## How run
It is based on spring boot, you can run it in jar module, such as :
```bash
java -jar zkconfig-server.${version}.jar 
```
More info please refer to [spring-boot](https://projects.spring.io/spring-boot/)

## Config
You can create a new file named application.yaml.
```yaml
zookeeper:
  # servers, separate by ,
  servers: 10.110.28.204:2181
  # the path to store the config data.
  path: /zkconfig/config
  # connect timeout
  connectTimeout: 30000
  # the connection path to store the connection information for client. 
  connectionPath: /zkconfig/connection
  scheme: digest
  username: zkconfig
  password: zkconfig
# default config.
spring:
  freemarker:
    settings:
      dateFormat: yyyy-MM-dd
      timeFormat: HH:mm:ss
      datetimeFormat: yyyy-MM-dd HH:mm:ss
    request-context-attribute: request
```

## Get it
```bash
git clone https://github.com/shushanfx/zkconfig-server.git
```
Then you can run the server with command `mvn clean spring-boot:run`, view with url [http://localhost:8080](http://localhost:8080)

## TODO
* Add history for former edit.

## LICENCE
MIT