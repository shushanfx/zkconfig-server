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
zkconfig:
  # type of config format.
  typeList:
    - properties
    - json
    - yaml
  # default value for different config format.
  contentDefault:
    properties: "#java properties format, such as:\n#key=value"
    json: "{\n\t\"key\":\"value\"\n}"
    yaml: "# yaml format config, such as: \n#root:\n#  key: value"
  # admin user name.
  adminUserName: shushanfx
  # admin user email, only one email can be recognized.
  adminEmail: shushanfx@gmail.com
# default config.
spring:
  freemarker:
    settings:
      dateFormat: yyyy-MM-dd
      timeFormat: HH:mm:ss
      datetimeFormat: yyyy-MM-dd HH:mm:ss
    request-context-attribute: request
```

## Client
There are two clients:
* [zkconfig-client](https://github.com/shushanfx/zkconfig-client): a client based on java.
* [zkconfig-client-node](https://github.com/shushanfx/zkconfig-client-node): a client based on NodeJS.

## Wiki
Please refer to [Wiki](https://github.com/shushanfx/zkconfig-server/wiki)

## Get it
```bash
git clone https://github.com/shushanfx/zkconfig-server.git
```
Then you can run the server with command `mvn clean spring-boot:run`, view with url [http://localhost:8080](http://localhost:8080)

## LICENCE
MIT
